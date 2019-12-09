package com.candidatos.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(description = "Classe utilizada para encapsular resposta de API")
public class ApiResponse<T> {

    @ApiModelProperty(notes = "O código de status da solicitação HTTP.", example = "200", dataType = "java.lang.Integer", required = true, position = 0)
    private int status;

    @ApiModelProperty(notes = "A mensagem indicativa da solicitação HTTP.", example = "200", dataType = "java.lang.Integer", required = true, position = 0)
    private String message;

    @ApiModelProperty(notes = "O objeto de reposta da solicitação HTTP.", example = "200", dataType = "java.lang.Integer", required = false, position = 0)
    private Object result;
}
