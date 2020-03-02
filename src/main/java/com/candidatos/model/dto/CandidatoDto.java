package com.candidatos.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Classe utilizada no transporte dos dados de candidato entre os diferentes componentes do sistema.")
public class CandidatoDto {

	@ApiModelProperty(notes = "Identificador do candidato no sistema.", example = "1", dataType = "java.lang.Long", required = true, position = 0)
	private Long id;
	
	@ApiModelProperty(notes = "Nome do candidato", example = "David", dataType = "java.lang.String", required = true, position = 1)
	private String nome;
	
	@ApiModelProperty(notes = "Sobrenome do candidato", example = "Archanjo", dataType = "java.lang.String", required = true, position = 2)
	private String sobrenome;
	
	@ApiModelProperty(notes = "Email do candidato", example = "david.archanjo@hotmail.com", dataType = "java.lang.String", required = true, position = 3)
	private String email;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CandidatoDto) {
			CandidatoDto aux = (CandidatoDto)obj;
			return aux.getNome().equals(this.getNome()) && aux.getSobrenome().equals(this.getSobrenome()) && aux.getEmail().equals(this.getEmail());
		} else {
			return false;
		}
	}
}
