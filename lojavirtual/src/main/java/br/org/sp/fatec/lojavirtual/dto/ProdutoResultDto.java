package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoResultDto {

    @Schema(description = "Identificador do produto", example = "1")
    private Long id;

    @Schema(description = "SKU do produto", example = "CAMI-001")
    private String sku;

    @Schema(description = "Nome do produto", example = "Camiseta básica")
    private String nome;

    @Schema(description = "Descrição detalhada do produto", example = "Camiseta 100% algodão")
    private String descricao;

    @JsonProperty("unidade_medida")
    @Schema(
            name = "unidade_medida",
            description = "Unidade de medida do produto",
            example = "UN"
    )
    private String unidadeMedida;

    @Schema(description = "Indica se o produto está ativo", example = "true")
    private Boolean ativo;

    @JsonProperty("foto_url")
    @Schema(
            name = "foto_url",
            description = "URL da imagem do produto",
            example = "https://meusite.com/imagens/camiseta.jpg"
    )
    private String fotoUrl;

    @Schema(description = "Preço do produto", example = "89.90")
    private BigDecimal preco;

    @Schema(description = "Indica se o produto está em promoção", example = "false")
    private Boolean promocao;

    @Schema(description = "Quantidade em estoque", example = "25")
    private BigDecimal estoque;
}
