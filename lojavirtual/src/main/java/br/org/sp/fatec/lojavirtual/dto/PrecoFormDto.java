package br.org.sp.fatec.lojavirtual.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrecoFormDto {

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço não pode ser negativo ou gratuito")
    @Schema(
            description = "Valor do preço do produto",
            example = "99.90",
            minimum = "0.01",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private BigDecimal valor;

    @Schema(
            description = "Indica se o produto está em promoção",
            example = "false"
    )
    private Boolean promocao;
}
