package br.org.sp.fatec.lojavirtual.service;

import br.org.sp.fatec.lojavirtual.dto.PedidoFormDto;
import br.org.sp.fatec.lojavirtual.dto.PedidoListDto;
import br.org.sp.fatec.lojavirtual.dto.PedidoResultDto;
import br.org.sp.fatec.lojavirtual.exceptions.PedidoNaoEncontradoException;
import br.org.sp.fatec.lojavirtual.model.MovimentacaoEstoque;
import br.org.sp.fatec.lojavirtual.model.Pedido;
import br.org.sp.fatec.lojavirtual.model.PedidoItem;
import br.org.sp.fatec.lojavirtual.model.Situacao;
import br.org.sp.fatec.lojavirtual.repository.*;
import br.org.sp.fatec.lojavirtual.wrapper.PedidoWrapper;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class PedidoService {

    public static final BigDecimal NEGATIVO = new BigDecimal(-1);

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final ProdutoRepository produtoRepository;
    private final PrecoRepository precoRepository;
    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    public PedidoService(
            PedidoRepository pedidoRepository,
            ClienteRepository clienteRepository,
            EnderecoRepository enderecoRepository,
            ProdutoRepository produtoRepository,
            PrecoRepository precoRepository,
            MovimentacaoEstoqueRepository movimentacaoEstoqueRepository
    ) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.produtoRepository = produtoRepository;
        this.precoRepository = precoRepository;
        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
    }

    @Transactional
    public PedidoResultDto cadastrarPedido(PedidoFormDto formulario){
        // Recupera o cliente
        var cliente = clienteRepository.findById(formulario.getClienteId())
                .orElseThrow(() -> new ValidationException("Cliente do pedido não encontrado"));
        // Futuro: Verificar se o cliente é do usuário logado

        // Recuperar o endereço
        var endereco = enderecoRepository.findById(formulario.getEnderecoEntregaId())
                .orElseThrow(() -> new ValidationException("Endereço de entrega não cadastrado"));

        if (!endereco.getCliente().getId().equals(cliente.getId())) {
            throw new ValidationException("Endereço não pertence ao cliente");
        }

        // Criar o pedido
        var pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEndereco(endereco);
        pedido.setData(LocalDateTime.now());
        pedido.setSituacao(Situacao.EFETUADO);
        pedido.setNumeroPedido(System.currentTimeMillis());
        pedido.setItens(new ArrayList<>());
        pedido.setTotal(BigDecimal.ZERO);

        var movimentacoesEstoque = new ArrayList<MovimentacaoEstoque>();

        for (var item : formulario.getItens()) {
            // Validar o produto
            var produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(
                            () -> new ValidationException("Produto "+ item.getProdutoId()+" não cadastrado")
                    );
            var estoque = movimentacaoEstoqueRepository.getPosicaoEstoqueDoProduto(item.getProdutoId());
            if( estoque.isEmpty() || estoque.get().compareTo(item.getQuantidade()) < 0 ){
                throw new ValidationException("Quantidade de estoque insuficiente para produto: " +
                        item.getProdutoId());
            }

            // Criar os itens do pedido
            var pedidoItem = new PedidoItem();
            pedidoItem.setProduto(produto);
            pedidoItem.setQuantidade(item.getQuantidade());
            pedidoItem.setPedido(pedido);

            //Recuperar o Preço
            var preco = precoRepository.findFirstPrecoByProdutoIdOrderByCreatedAtDesc(item.getProdutoId())
                    .orElseThrow(() -> new ValidationException("Produto sem preço cadastrado " + item.getProdutoId()));
            pedido.setTotal(pedido.getTotal().add(preco.getValor().multiply(item.getQuantidade())));
            pedidoItem.setPreco(preco.getValor());
            pedido.getItens().add(pedidoItem);

            // Cria a movimentação de estoque de retirada
            var movimentacao = new MovimentacaoEstoque();
            movimentacao.setProduto(produto);
            movimentacao.setQuantidade(item.getQuantidade().multiply(NEGATIVO));
            movimentacao.setDataMovimentacao(LocalDateTime.now());
            movimentacoesEstoque.add(movimentacao);
        }

        // Salvar o Pedido, os itens e suas movimentações de estoque
        pedido = pedidoRepository.save(pedido);
        movimentacaoEstoqueRepository.saveAll(movimentacoesEstoque);

        var dto = PedidoWrapper.converterDaEntidadeParaResultadoUnico(pedido);
        return dto;
    }

    public Page<PedidoListDto> buscaPaginada(Pageable paginacao, Long clienteId) {
        var pedidos = pedidoRepository.buscaPaginada(paginacao, clienteId).map(
                (pedido) -> {
                    return PedidoWrapper.converterdaEntidadeParaLista(pedido);
                }
        );
        return pedidos;
    }

    public PedidoResultDto buscarPorId(Long id) {
        var pedido = pedidoRepository.findById(id).orElseThrow(PedidoNaoEncontradoException::new);
        var dto = PedidoWrapper.converterDaEntidadeParaResultadoUnico(pedido);
        return dto;
    }
}
