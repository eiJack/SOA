package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoFormCadastroDto extends ProdutoFormAtualizacaoDto {

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço não pode ser negativo ou gratuito")
    @Schema(
            description = "Preço do produto",
            example = "99.90"
    )
    private BigDecimal preco;

    @NotNull(message = "Estoque Inicial")
    @DecimalMin(value = "0.01", message = "Estoque não pode ser negativo ou zero")
    @JsonProperty("estoque_inicial")
    @Schema(
            name = "estoque_inicial",
            description = "Quantidade inicial em estoque do produto",
            example = "10"
    )
    private BigDecimal estoqueInicial;

}
