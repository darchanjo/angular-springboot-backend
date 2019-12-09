package com.candidatos;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import com.candidatos.model.dto.CandidatoDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class CandidatoServiceControllerTest extends AbstractServiceControllerTest {

    private static final String API_URL = "/api/v1/candidatos";    
    
    @Before
    @Override
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createCandidato() throws Exception {
        final CandidatoDto candidato = new CandidatoDto();        
        candidato.setNome("Foo");
        candidato.setSobrenome("Bar");
        candidato.setEmail("foo.bar@gmail.com");

        final String inputJson = super.objToJson(candidato);
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(API_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        final String content = mvcResult.getResponse().getContentAsString();

        final Map<String, Object> map = super.objFromJson(content, Map.class);
        assertEquals(201, (int)map.get("status"));
                
        final CandidatoDto newCandidato = super.objFromJson(content, "result", CandidatoDto.class);
        assertEquals(newCandidato, candidato);
    }

    @Test
    public void getCandidatoList() throws Exception {
        final MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.get(API_URL).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        final String content = mvcResult.getResponse().getContentAsString();
        final Map<String, Object> map = super.objFromJson(content, Map.class);
        assertEquals(map.get("message").toString(), "Lista de Candidatos carregada com sucesso");
    }

    @Test
    public void updateCandidato() throws Exception {
        final CandidatoDto candidato = new CandidatoDto();
        candidato.setId(1L);
        candidato.setNome("Bar");
        candidato.setSobrenome("Foo");
        candidato.setEmail("bar.foo@gmail.com");

        final String inputJson = super.objToJson(candidato);
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(API_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        final String content = mvcResult.getResponse().getContentAsString();
        final Map<String, Object> map = super.objFromJson(content, Map.class);
        assertEquals(map.get("message").toString(), "Candidato atualizado com sucesso");
    }

    @Test
    public void deleteCandidato() throws Exception {
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(API_URL + "/1")).andReturn();

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        final String content = mvcResult.getResponse().getContentAsString();
        final Map<String, Object> map = super.objFromJson(content, Map.class);
        assertEquals(map.get("message").toString(), "Candidato deletado com sucesso");
    }
}
