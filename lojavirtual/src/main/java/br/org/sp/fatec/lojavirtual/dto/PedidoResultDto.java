package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoResultDto {

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

    /*
        No futuro o interessante seria não trazer os dados do cliente,
        mas sim do endereço, está ao contrario para ter exemplo
     */

    @Schema(
            description = "Dados do cliente que realizou o pedido"
    )
    private ClienteResultDto cliente;

    @JsonProperty("endereco_id")
    @Schema(
            description = "Identificador do endereço de entrega",
            example = "10"
    )
    private Long enderecoId;

    @Schema(
            description = "Situação atual do pedido (ex: ABERTO, PAGO, CANCELADO)",
            example = "PAGO"
    )
    private String situacao;

    @Schema(
            description = "Lista de itens do pedido"
    )
    private List<PedidoItemResultDto> itens;
}
