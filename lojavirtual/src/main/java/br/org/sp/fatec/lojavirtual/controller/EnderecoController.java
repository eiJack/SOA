package br.org.sp.fatec.lojavirtual.controller;

import br.org.sp.fatec.lojavirtual.dto.EnderecoFormDto;
import br.org.sp.fatec.lojavirtual.dto.EnderecoResultDto;
import br.org.sp.fatec.lojavirtual.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar endereço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoResultDto salvar(@Valid @RequestBody EnderecoFormDto dto) {
        return service.salvarEndereco(dto);
    }

    @Operation(summary = "Buscar endereço por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    @GetMapping("/{id}")
    public EnderecoResultDto buscar(@PathVariable Long id) {
        return service.buscarEnderecoPorId(id);
    }

    @Operation(summary = "Atualizar endereço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    @PutMapping("/{id}")
    public EnderecoResultDto atualizar(
            @PathVariable Long id,
            @RequestBody @Valid EnderecoFormDto dto
    ) {
        return service.atualizarEndereco(id, dto);
    }

    @Operation(summary = "Deletar endereço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço deletado"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletarEndereco(id);
    }

    @Operation(summary = "Listar endereços")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de endereços retornada")
    })
    @GetMapping
    public Page<EnderecoResultDto> listar(Pageable paginacao) {
        return service.listarEnderecoPaginado(paginacao);
    }
}