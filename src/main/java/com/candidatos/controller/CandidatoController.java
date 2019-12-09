package com.candidatos.controller;

import java.util.List;

import com.candidatos.model.ApiResponse;
import com.candidatos.model.Candidato;
import com.candidatos.model.dto.CandidatoDto;
import com.candidatos.service.impl.CandidatoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/candidatos")
@Api(value = "Candidatos", description = "Grupo de endpoints para criação, busca, atualização and exclusão de Candidatos.")
public class CandidatoController {

	@Autowired
	private CandidatoServiceImpl candidatoService;

	@ApiOperation(value = "Retorna lista de todos os candidatos cadastrados no sistema")
	@ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Lista de candidato carregada com sucesso"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "Acesso não autorizado ao recurso"),
        @io.swagger.annotations.ApiResponse(code = 403, message = "O acesso a este recurso está proibido"),
        @io.swagger.annotations.ApiResponse(code = 404, message = "O recurso solicitado não foi encontrado")
    })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse<List<Candidato>> getAllCandidatos() {
		return new ApiResponse<>(HttpStatus.OK.value(), "Lista de Candidatos carregada com sucesso", candidatoService.findAll());
	}

	@ApiOperation(value = "Retorna o candidato correspondente ao identificador fornecido. Retorna 404 se o identificador não for encontrado no sistema.")
	@ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Candidato carregado com sucesso"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "Acesso não autorizado ao recurso"),
        @io.swagger.annotations.ApiResponse(code = 403, message = "O acesso a este recurso está proibido"),
        @io.swagger.annotations.ApiResponse(code = 404, message = "O recurso solicitado não foi encontrado")
    })
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse<Candidato> getOne(
		@ApiParam("Id do candidado a ser obtido. Não pode ser nulo.") @PathVariable(value = "id") Long id) {
		return new ApiResponse<>(HttpStatus.OK.value(), "Candidato carregado com sucesso",candidatoService.findById(id));
	}

	@ApiOperation(value = "Cadastra um novo candidato no sistema")
	@ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 201, message = "Candidato criado com sucesso"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "Acesso não autorizado ao recurso"),
        @io.swagger.annotations.ApiResponse(code = 403, message = "O acesso a este recurso está proibido"),
        @io.swagger.annotations.ApiResponse(code = 404, message = "O recurso solicitado não foi encontrado")
    })
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse<Candidato> createCandidato(
		@ApiParam("Dados do candidato a ser cadastrado no sistema.") @RequestBody CandidatoDto candidato) {
		return new ApiResponse<>(HttpStatus.CREATED.value(), "Candidato criado com sucesso", candidatoService.create(candidato));
	}

	@ApiOperation(value = "Atualiza os dados do candidato")
	@ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Candidato atualizado com sucesso"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "Acesso não autorizado ao recurso"),
        @io.swagger.annotations.ApiResponse(code = 403, message = "O acesso a este recurso está proibido"),
        @io.swagger.annotations.ApiResponse(code = 404, message = "O recurso solicitado não foi encontrado")
    })
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse<Candidato> updateCandidato(
		@ApiParam("Dados do candidato a ser atualizado no sistema.") @RequestBody CandidatoDto candidatoDto) {
		return new ApiResponse<>(HttpStatus.OK.value(), "Candidato atualizado com sucesso",candidatoService.update(candidatoDto));
	}

	@ApiOperation(value = "Exclui o registro do candidato do sistema")
	@ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Candidato deletado com sucesso"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "Acesso não autorizado ao recurso"),
        @io.swagger.annotations.ApiResponse(code = 403, message = "O acesso a este recurso está proibido"),
        @io.swagger.annotations.ApiResponse(code = 404, message = "O recurso solicitado não foi encontrado")
    })
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse<Boolean> deleteCandidato(
		@ApiParam("Id do candidado a ser excluido. Não pode ser nulo.") @PathVariable(value = "id") Long id) {
		candidatoService.delete(id);
		return new ApiResponse<>(HttpStatus.OK.value(), "Candidato deletado com sucesso", true);
	}
}
