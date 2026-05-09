package br.org.sp.fatec.lojavirtual.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EnderecoFormDto {

    @NotBlank
    @Schema(example = "Rua das Flores")
    private String logradouro;

    @NotBlank
    @Schema(example = "Centro")
    private String bairro;

    @Schema(example = "150")
    private Integer numero;

    @Schema(example = "Apartamento 12")
    private String complemento;

    @NotBlank
    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "CEP deve estar no formato 00000-000")
    @Schema(example = "18000-000")
    private String cep;

    @NotBlank
    @Schema(example = "Sorocaba")
    private String cidade;

    @NotBlank
    @Schema(example = "SP")
    private String estado;

    @NotNull(message = "Id do cliente deve ser informado")
    @Schema(example = "10")
    private Long clienteId;
}
