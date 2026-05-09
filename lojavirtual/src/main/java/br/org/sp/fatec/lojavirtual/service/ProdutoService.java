package br.org.sp.fatec.lojavirtual.service;


import br.org.sp.fatec.lojavirtual.dto.*;
import br.org.sp.fatec.lojavirtual.exceptions.ProdutoNaoEncontradoException;
import br.org.sp.fatec.lojavirtual.model.MovimentacaoEstoque;
import br.org.sp.fatec.lojavirtual.model.Preco;
import br.org.sp.fatec.lojavirtual.model.Produto;
import br.org.sp.fatec.lojavirtual.repository.MovimentacaoEstoqueRepository;
import br.org.sp.fatec.lojavirtual.repository.PrecoRepository;
import br.org.sp.fatec.lojavirtual.repository.ProdutoRepository;
import br.org.sp.fatec.lojavirtual.wrapper.ProdutoWrapper;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final PrecoRepository precoRepository;
    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          PrecoRepository precoRepository,
                          MovimentacaoEstoqueRepository movimentacaoEstoqueRepository) {
        this.produtoRepository = produtoRepository;
        this.precoRepository = precoRepository;
        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
    }

    public Optional<ProdutoResultDto> getById(Long id) {
        return produtoRepository.findById(id).map((produto) -> {
            var preco = precoRepository.findFirstPrecoByProdutoIdOrderByCreatedAtDesc(produto.getId());
            var posicaoEstoque = movimentacaoEstoqueRepository.getPosicaoEstoqueDoProduto(produto.getId());
            return ProdutoWrapper.converterDaEntidateParaResultadoUnico(produto, preco.orElse(null), posicaoEstoque.orElse(BigDecimal.ZERO));
        });
    }

    public Page<ProdutoListDto> getProdutos(Pageable paginacao, String filtro) {
        if (StringUtils.isEmpty(filtro)) {
            return produtoRepository.findAll(paginacao).map((produto) -> {
                var preco = precoRepository.findFirstPrecoByProdutoIdOrderByCreatedAtDesc(produto.getId());
                return ProdutoWrapper.converterDaEntidadeParaItemDeLista(produto, preco.orElse(null));
            });
        }
        return produtoRepository.findAllByNomeIgnoreCaseContaining(paginacao, filtro)
                .map((produto -> {
                    var preco = precoRepository.findFirstPrecoByProdutoIdOrderByCreatedAtDesc(produto.getId());
                    return ProdutoWrapper.converterDaEntidadeParaItemDeLista(produto, preco.orElse(null));
        }));
    }

    public ProdutoResultDto criarProduto(ProdutoFormCadastroDto formulario){
        Produto novo = new Produto();
        ProdutoWrapper.converterFormParaEntidade(formulario, novo);
        novo.setAtivo(false);

        // Verificar se o SKU já existe
        var produtoOptional = produtoRepository.findOneBySkuIgnoreCase(formulario.getSku());
        if (produtoOptional.isPresent()) {
            throw new ValidationException("SKU deve ser único");
        }
        novo = produtoRepository.save(novo);
        // Criar o preço
        var preco = new Preco();
        preco.setProduto(novo);
        preco.setValor(formulario.getPreco());
        preco.setPromocao(false);
        preco = precoRepository.save(preco);

        // Criar o estoque inicial
        var movimentacaoEstoque = new MovimentacaoEstoque();
        movimentacaoEstoque.setDataMovimentacao(LocalDateTime.now());
        movimentacaoEstoque.setProduto(novo);
        movimentacaoEstoque.setQuantidade(formulario.getEstoqueInicial());
        movimentacaoEstoqueRepository.save(movimentacaoEstoque);

        var posicaoEstoque = formulario.getEstoqueInicial();

        var dto = ProdutoWrapper.converterDaEntidateParaResultadoUnico(novo, preco, posicaoEstoque);
        return dto;
    }

    public ProdutoResultDto atualizarProduto(Long id, ProdutoFormAtualizacaoDto formulario){
        // Recupera o Produto original do banco de dados
        var produto = produtoRepository.findById(id).orElseThrow(ProdutoNaoEncontradoException::new);


        // Utiliza o wrapper para converter os dados do formulario para o produto
        // Atualizando apenas o que o formulário permite
        ProdutoWrapper.converterFormParaEntidade(formulario, produto);
        // Atualiza no banco de dados
        produto = produtoRepository.save(produto);
        // Pegar o preço atual do produto
        var preco = precoRepository.findFirstPrecoByProdutoIdOrderByCreatedAtDesc(produto.getId());
        //Pegar posicao de estoque atual
        var posicaoEstoque = movimentacaoEstoqueRepository.getPosicaoEstoqueDoProduto(produto.getId()).orElse(BigDecimal.ZERO);
        // Converter para o dto
        var dto = ProdutoWrapper.converterDaEntidateParaResultadoUnico(produto, preco.orElse(null), posicaoEstoque);
        // Retorna o dto
        return dto;
    }

    public void apagarProduto(Long id){
        var produto = produtoRepository.findById(id).orElseThrow(ProdutoNaoEncontradoException::new);
        produtoRepository.delete(produto);
    }

    public ProdutoResultDto atualizarPreco(Long id, PrecoFormDto preco) {
        // Procurar o produto no banco
        var produto = produtoRepository.findById(id).orElseThrow(ProdutoNaoEncontradoException::new);

        // Cria o preco para o produto
        var novoPreco = new Preco();
        novoPreco.setProduto(produto);
        novoPreco.setValor(preco.getValor());
        if (preco.getPromocao() == null) {
            novoPreco.setPromocao(false);
        } else {
            novoPreco.setPromocao(preco.getPromocao());
        }
        novoPreco = precoRepository.save(novoPreco);

        //Pegar posicao de estoque atual
        var posicaoEstoque = movimentacaoEstoqueRepository.getPosicaoEstoqueDoProduto(produto.getId()).orElse(BigDecimal.ZERO);

        // Converte para dto para devolver para a API
        var dto = ProdutoWrapper.converterDaEntidateParaResultadoUnico(produto, novoPreco, posicaoEstoque);
        return dto;

    }

    public ProdutoResultDto atualizarAtivo(Long id, ProdutoAtivoDto ativo) {
        // Procurar o produto no banco
        var produto = produtoRepository.findById(id).orElseThrow(ProdutoNaoEncontradoException::new);


        // Recuperar o ultimo preco
        var preco = precoRepository.findFirstPrecoByProdutoIdOrderByCreatedAtDesc(id);

        //Atualizar o produto
        produto.setAtivo(ativo.getAtivo());
        produto = produtoRepository.save(produto);

        //Pegar posicao de estoque atual
        var posicaoEstoque = movimentacaoEstoqueRepository.getPosicaoEstoqueDoProduto(produto.getId()).orElse(BigDecimal.ZERO);

        //Cria o dto
        var dto = ProdutoWrapper.converterDaEntidateParaResultadoUnico(produto, preco.orElse(null), posicaoEstoque);
        return dto;
    }

}











