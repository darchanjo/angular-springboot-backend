package com.candidatos.controller;

import com.candidatos.model.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/authenticate")
@Api(value = "Autenticador", description = "Endpoint usado para validar credencial de acesso de usuário ao sistema.")
public class AuthenticationController {    

    @PostMapping
    @ApiOperation(value = "Autentica se a credencia do usuário é valida.")
    @ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Credencial de acesso é válida"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "Credencial de acesso inválida")
    })
    public ApiResponse<Void> authenticate() throws AuthenticationException {
        return new ApiResponse<Void>(HttpStatus.OK, "Usuário logado com sucesso", null);
    }

}
