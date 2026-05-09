package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PedidoListDto {

    @Schema(
            description = "Identificador do pedido",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Data de criação do pedido",
            example = "2026-05-05T14:30:00"
    )
    private LocalDateTime data;

    @JsonProperty("numero_pedido")
    @Schema(
            description = "Número sequencial do pedido",
            example = "1001"
    )
    private Long numeroPedido;

    @Schema(
            description = "Valor total do pedido",
            example = "150.75"
    )
    private BigDecimal total;

    @Schema(
            description = "Situação atual do pedido (ex: ABERTO, PAGO, CANCELADO)",
            example = "PAGO"
    )
    private String situacao;
}
