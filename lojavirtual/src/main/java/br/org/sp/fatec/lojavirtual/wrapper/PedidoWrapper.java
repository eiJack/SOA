package br.org.sp.fatec.lojavirtual.wrapper;

import br.org.sp.fatec.lojavirtual.dto.PedidoFormDto;
import br.org.sp.fatec.lojavirtual.dto.PedidoItemResultDto;
import br.org.sp.fatec.lojavirtual.dto.PedidoListDto;
import br.org.sp.fatec.lojavirtual.dto.PedidoResultDto;
import br.org.sp.fatec.lojavirtual.model.Cliente;
import br.org.sp.fatec.lojavirtual.model.Pedido;

import java.util.ArrayList;

public class PedidoWrapper {

    public static PedidoResultDto converterDaEntidadeParaResultadoUnico(Pedido pedido) {
        PedidoResultDto pedidoResultDto = new PedidoResultDto();
        pedidoResultDto.setId(pedido.getId());
        pedidoResultDto.setData(pedido.getData());
        pedidoResultDto.setNumeroPedido(pedido.getNumeroPedido());
        pedidoResultDto.setTotal(pedido.getTotal());
        pedidoResultDto.setCliente(ClienteWrapper.converterParaResultadoUnico(pedido.getCliente()));
        pedidoResultDto.setEnderecoId(pedido.getEndereco().getId());
        pedidoResultDto.setSituacao(pedido.getSituacao().name());
        pedidoResultDto.setItens(new ArrayList<>());
        for (var item : pedido.getItens()) {
            var itemDto = new PedidoItemResultDto();
            itemDto.setProduto(
                    ProdutoWrapper.converterDaEntidadeParaItemDeLista(item.getProduto(), null)
            );
            itemDto.setQuantidade(item.getQuantidade());
            itemDto.setPreco(item.getPreco());
            itemDto.setTotal(item.getQuantidade().multiply(item.getPreco()));
            pedidoResultDto.getItens().add(itemDto);
        }
        return pedidoResultDto;
    }

    public static PedidoListDto converterdaEntidadeParaLista(Pedido pedido) {
        PedidoListDto pedidoListDto = new PedidoListDto();

        pedidoListDto.setId(pedido.getId());
        pedidoListDto.setData(pedido.getData());
        pedidoListDto.setNumeroPedido(pedido.getNumeroPedido());
        pedidoListDto.setTotal(pedido.getTotal());
        pedidoListDto.setSituacao(pedido.getSituacao().name());

        return pedidoListDto;
    }
}
