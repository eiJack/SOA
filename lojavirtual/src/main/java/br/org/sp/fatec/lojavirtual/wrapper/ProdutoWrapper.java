package br.org.sp.fatec.lojavirtual.wrapper;

import br.org.sp.fatec.lojavirtual.dto.ProdutoFormAtualizacaoDto;
import br.org.sp.fatec.lojavirtual.dto.ProdutoListDto;
import br.org.sp.fatec.lojavirtual.dto.ProdutoResultDto;
import br.org.sp.fatec.lojavirtual.model.Preco;
import br.org.sp.fatec.lojavirtual.model.Produto;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class ProdutoWrapper {

    public static void converterFormParaEntidade(ProdutoFormAtualizacaoDto form, Produto produto){
        // Jeito manual
        produto.setSku(form.getSku());
        // etc ...
        // Jeito automatizado
        // Só funciona se o source e target tiverem os campos com mesmo nome,
        // caso contrario tem que fazer do jeito manual
        BeanUtils.copyProperties(form, produto);
    }

    public static ProdutoResultDto converterDaEntidateParaResultadoUnico(
            Produto produto,
            Preco preco,
            BigDecimal estoque
    ){
        ProdutoResultDto dto = new ProdutoResultDto();
        BeanUtils.copyProperties(produto, dto);
        if ( preco != null) {
           dto.setPreco(preco.getValor());
           dto.setPromocao(preco.getPromocao());
        }
        return dto;
    }

    public static ProdutoListDto converterDaEntidadeParaItemDeLista(Produto produto, Preco preco) {
        var dto = new ProdutoListDto();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setFotoUrl(produto.getFotoUrl());
        if (preco != null) {
            dto.setPreco(preco.getValor());
            dto.setPromocao(preco.getPromocao());
        }
        return dto;
    }


}
