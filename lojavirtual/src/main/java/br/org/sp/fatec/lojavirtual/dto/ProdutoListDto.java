package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoListDto {

    @Schema(description = "Identificador do produto", example = "1")
    private Long id;

    @Schema(description = "Nome do produto", example = "Camiseta básica")
    private String nome;

    @JsonProperty("foto_url")
    @Schema(
            name = "foto_url",
            description = "URL da imagem do produto",
            example = "https://meusite.com/imagens/produto.jpg"
    )
    private String fotoUrl;

    @Schema(description = "Preço do produto", example = "79.90")
    private BigDecimal preco;

    @Schema(
            description = "Indica se o produto está em promoção",
            example = "false"
    )
    private Boolean promocao;
}
