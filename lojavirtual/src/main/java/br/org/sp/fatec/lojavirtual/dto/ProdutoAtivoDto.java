package br.org.sp.fatec.lojavirtual.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoAtivoDto {

    @NotNull(message = "O campo ativo é obrigatório")
    @Schema(
            description = "Indica se o produto está ativo no sistema",
            example = "true",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Boolean ativo;
}
