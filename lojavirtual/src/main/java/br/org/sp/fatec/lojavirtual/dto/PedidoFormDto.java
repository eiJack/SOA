package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PedidoFormDto {

    @NotNull(message = "Id do cliente é obrigatório")
    @JsonProperty("cliente_id")
    @Schema(
            description = "Identificador do cliente que está realizando o pedido",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long clienteId;

    @NotNull(message = "Id do endereço é obrigatório")
    @JsonProperty("endereco_entrega_id")
    @Schema(
            description = "Identificador do endereço de entrega",
            example = "10",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long enderecoEntregaId;

    @NotNull(message = "A lista de itens não pode ser nula")
    @Size(min = 1, message = "Deve ter pelo menos um item")
    @Schema(
            description = "Lista de itens do pedido",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<PedidoItemFormDto> itens;
}
