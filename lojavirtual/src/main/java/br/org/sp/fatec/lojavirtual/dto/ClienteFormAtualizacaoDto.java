package br.org.sp.fatec.lojavirtual.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClienteFormAtualizacaoDto {

    @NotBlank(message = "Nome do cliente não pode ser vazio")
    @Schema(
            description = "Nome completo do cliente",
            example = "Jackeline de Paula",
            minLength = 5,
            maxLength = 255,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String nome;

    @Pattern(regexp = "\\(?\\d{2}\\)?\\s?\\d{4,5}(-|\\s)?\\d{4}", message = "Formato de telefone inválido")
    @Schema(
            description = "Telefone do cliente com DDD",
            example = "(11) 98765-4321",
            pattern = "\\(?\\d{2}\\)?\\s?\\d{4,5}(-|\\s)?\\d{4}",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String telefone;

    @Email(message = "Deve ser informado um email válido")
    @Size(max = 150, message = "E-mail deve ter até 150 caracteres")
    @NotNull(message = "E-mail deve ser informado")
    @Schema(
            description = "E-mail do cliente",
            example = "jackeline@email.com",
            format = "email",
            maxLength = 150,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;
}
