package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoFormAtualizacaoDto {

    @NotBlank(message = "Sku não pode ser em branco ou vazio")
    @Schema(
            description = "Código SKU do produto",
            example = "PROD-123"
    )
    private String sku;

    @NotBlank(message = "Nome do produto não pode ser branco ou vazio")
    @Schema(
            description = "Nome do produto",
            example = "Camiseta básica"
    )
    private String nome;

    @Schema(
            description = "Descrição detalhada do produto",
            example = "Camiseta 100% algodão"
    )
    private String descricao;

    @NotBlank(message = "unidade_medida não pode ser em branco ou nulo")
    @Size(min = 2, max = 2, message = "unidade_medida deve ter exatamente 2 caracteres")
    @JsonProperty("unidade_medida")
    @Schema(
            description = "Unidade de medida do produto (ex: UN, KG)",
            example = "UN",
            minLength = 2,
            maxLength = 2
    )
    private String unidadeMedida;

    @JsonProperty("foto_url")
    @Pattern(
            regexp = "^https?:\\/\\/[^\\s/$.?#].[^\\s]*$",
            message = "Deve ser um endereço de internet válido"
    )
    @Schema(
            description = "URL da imagem do produto",
            example = "https://site.com/imagem.jpg"
    )
    private String fotoUrl;
}
