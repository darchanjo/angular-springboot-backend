package com.candidatos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "candidatos")
@ApiModel(description = "Entidade que representa um candidato no sitema.")
public class Candidato {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "Identificador do candidato no sistema.", example = "1", dataType = "java.lang.Long", required = true, position = 0)
	private Long id;

	@NotNull
	@Column(name = "nome")
	@ApiModelProperty(notes = "Nome do candidato", example = "David", dataType = "java.lang.String", required = true, position = 1)
	private String nome;

	@NotNull
	@Column(name = "sobrenome")
	@ApiModelProperty(notes = "Sobrenome do candidato", example = "Archanjo", dataType = "java.lang.String", required = true, position = 2)
	private String sobrenome;

	@NotNull
	@Column(name = "email")
	@ApiModelProperty(notes = "Email do candidato", example = "david.archanjo@hotmail.com", dataType = "java.lang.String", required = true, position = 3)
	private String email;
}
