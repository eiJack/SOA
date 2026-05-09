package br.org.sp.fatec.lojavirtual.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClienteResultDto {

    @Schema(
            description = "id unico do cliente",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Nome completo do cliente",
            example = "Jackeline",
            minLength = 5, maxLength = 255
    )
    private String nome;

    @Schema(
            description = "CPF do cliente",
            example = "123456789-89",
            pattern = "(\\\\d{3}\\\\.?){3}-?\\\\d{2}"
    )
    private String cpf;

    @Schema(
            description = "Telefone do cliente com DDD",
            example = "(11) 98765-4321"
    )
    private String telefone;

    @Schema(
            description = "E-mail do cliente",
            example = "jackeline@email.com",
            format = "email",
            maxLength = 150
    )
    private String email;
}
