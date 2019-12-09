package com.candidatos.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.candidatos.model.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
 
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
 
    @Value("${app.realm.name}")
    private String realmName;

    @Override
    public void commence(final HttpServletRequest request, 
            final HttpServletResponse response, 
            final AuthenticationException exception) throws IOException {
         
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();         
        String error = mapper.writeValueAsString(new ApiResponse<>(401, exception.getMessage(), null)); 
        
        PrintWriter writer = response.getWriter();        
        writer.println(error);
        writer.flush();
    }
     
    @Override
    public void afterPropertiesSet() {
        setRealmName(realmName);
        super.afterPropertiesSet();
    }
}