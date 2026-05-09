package br.org.sp.fatec.lojavirtual.controller;

import br.org.sp.fatec.lojavirtual.dto.*;
import br.org.sp.fatec.lojavirtual.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping("/")
    public ResponseEntity<Page<ProdutoListDto>> listagemComFiltro(
            Pageable paginacao,
            @RequestParam(required = false) String filtro
    ) {
        var paginaProdutos = produtoService.getProdutos(paginacao, filtro);
        return ResponseEntity.ok(paginaProdutos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> procurarPorId(@PathVariable Long id){
        var produto = produtoService.getById(id);
        return ResponseEntity.ok(produto);

    }

    @PostMapping("/")
    public ResponseEntity<ProdutoResultDto> criarProduto(@RequestBody @Valid ProdutoFormCadastroDto novo,
                                                         UriComponentsBuilder builder) {
        var produto = produtoService.criarProduto(novo);
        var uri = builder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id,
                                              @RequestBody @Valid ProdutoFormAtualizacaoDto modificado) {
        var produto = produtoService.atualizarProduto(id, modificado);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> apagarProduto(@PathVariable Long id) {
        produtoService.apagarProduto(id);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/{id}/preco")
    public ResponseEntity<?> atualizarPreco(
            @PathVariable Long id,
            @Valid @RequestBody PrecoFormDto preco
    ) {
        var produto = produtoService.atualizarPreco(id, preco);
        return ResponseEntity.ok(produto);
    }

    @PatchMapping("/{id}/ativo")
    public ResponseEntity<?> atualizarAtivo(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoAtivoDto ativo
    ) {
        var produto = produtoService.atualizarAtivo(id, ativo);
        return ResponseEntity.ok(produto);
    }


}
