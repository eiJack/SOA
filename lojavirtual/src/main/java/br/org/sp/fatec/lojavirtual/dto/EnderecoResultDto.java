package br.org.sp.fatec.lojavirtual.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EnderecoResultDto {

    @Schema(example = "1", description = "Id do endereço")
    private Long id;

    @Schema(example = "Rua das Flores")
    private String logradouro;

    @Schema(example = "Centro")
    private String bairro;

    @Schema(example = "150")
    private Integer numero;

    @Schema(example = "Apartamento 12")
    private String complemento;

    @Schema(example = "18000-000")
    private String cep;

    @Schema(example = "Sorocaba")
    private String cidade;

    @Schema(example = "SP")
    private String estado;

    @Schema(example = "10")
    private Long clienteId;
}
