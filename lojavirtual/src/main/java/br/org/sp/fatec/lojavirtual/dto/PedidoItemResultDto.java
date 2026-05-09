package br.org.sp.fatec.lojavirtual.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PedidoItemResultDto {

    @Schema(
            description = "Dados resumidos do produto"
    )
    private ProdutoListDto produto;

    @Schema(
            description = "Quantidade do item",
            example = "1",
            minimum = "0.01"
    )
    private BigDecimal quantidade;

    @Schema(
            description = "Preco unitario do produto",
            example = "10.00"
    )
    private BigDecimal preco;

    @Schema(
            description = "Total final do pedido (preço × quantidade)",
            example = "10.00"
    )
    private BigDecimal total;

}
