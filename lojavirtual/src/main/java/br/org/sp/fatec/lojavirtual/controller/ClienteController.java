package br.org.sp.fatec.lojavirtual.controller;

import br.org.sp.fatec.lojavirtual.dto.ClienteFormAtualizacaoDto;
import br.org.sp.fatec.lojavirtual.dto.ClienteFormCadastroDto;
import br.org.sp.fatec.lojavirtual.dto.ClienteListDto;
import br.org.sp.fatec.lojavirtual.dto.ClienteResultDto;
import br.org.sp.fatec.lojavirtual.model.Cliente;
import br.org.sp.fatec.lojavirtual.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/")
    @Operation(description = "Listagem paginada de clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorno correto da pagina de clientes")
    })
    public ResponseEntity<Page<ClienteListDto>> listagem (Pageable paginacao) {
        var pagina = clienteService.listarPaginado(paginacao);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    @Operation(description = "Retorno de cliente especifico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorno correto da pagina do cliente",
                    content = {@Content(mediaType = "application/json",
                    contentSchema = @Schema(implementation = ClienteResultDto.class))}),
            @ApiResponse(responseCode = "404", description = "Retorno de que cliente nao existe",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<ClienteResultDto> buscarPorId (@Parameter(description = "Id do cliente") @PathVariable Long id){
        var cliente = clienteService.buscaPorId(id);
        return ResponseEntity.ok(cliente);
    }


    @PostMapping("/")
    @Operation(description = "Retorno do cadastro do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Retorno dados do cliente",
                    headers = {@Header(name = "Location",
                            description = "Endereco(URI) do cliente cadastrado")},
                    content = {@Content(mediaType = "application/json",
                            contentSchema = @Schema(implementation = ClienteResultDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Retorno de erro de validacao",
                    content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<ClienteResultDto> cadastro (
            @Valid @RequestBody ClienteFormCadastroDto form,
            UriComponentsBuilder builder
    ){
        var cliente = clienteService.cadastrar(form);
        var location = builder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity
                .created(location)
                .body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResultDto> atualizar (
            @Valid @RequestBody ClienteFormAtualizacaoDto form,
            @PathVariable Long id
    ){
        var clienteAtualizado = clienteService.atualizar(id, form);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover (@PathVariable Long id){
        clienteService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
