package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PedidoItemFormDto {

    @NotNull(message = "Id do produto não pode ser nulo")
    @JsonProperty("produto_id")
    @Schema(
            description = "Id do produto",
            example = "5",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long produtoId;

    @NotNull(message = "Quantidade não pode ser nula")
    @DecimalMin(value = "0.01", message = "Quantidade deve ser positiva e diferente de zero.")
    @Schema(
            description = "Quantidade do produto no pedido",
            example = "2",
            minimum = "0.01",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private BigDecimal quantidade;
}
