package br.org.sp.fatec.lojavirtual.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(name = "ClienteFormAtualizacaoDto", description = "DTO utilizado para atualização de dados do cliente")
public class ClienteFormAtualizacaoDtp {

    @NotBlank(message = "Nome do cliente deve ser preenchido")
    @Size(min = 5, max = 255, message = "O nome deve ter no minimo 5 letras e max 255")
    @Schema(
            description = "Nome completo do cliente",
            example = "Jackeline de Paula",
            minLength = 5,
            maxLength = 255,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String nome;

    @Pattern(
            regexp = "\\(?\\d{2}\\)?\\s?\\d{4,5}(-|\\s)?\\d{4}",
            message = "Formato de telefone invalido"
    )
    @Schema(
            description = "Telefone do cliente com DDD",
            example = "(11) 98765-4321",
            pattern = "\\(?\\d{2}\\)?\\s?\\d{4,5}(-|\\s)?\\d{4}"
    )
    private String telefone;

    @Email(message = "Deve ser informado um email valido")
    @Size(max = 150, message = "Email deve conter ate 150 caracteres")
    @NotNull(message = "Email deve ser informado")
    @Schema(
            description = "E-mail do cliente",
            example = "jackeline@email.com",
            format = "email",
            maxLength = 150,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;
}