package br.org.sp.fatec.lojavirtual.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClienteListDto {

    @Schema(
            description = "Identificador único do cliente",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Nome completo do cliente",
            example = "Jackeline de Paula"
    )
    private String nome;

    @Schema(
            description = "CPF do cliente",
            example = "123.456.789-00"
    )
    private String cpf;
}
