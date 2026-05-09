package br.org.sp.fatec.lojavirtual.repository;

import br.org.sp.fatec.lojavirtual.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {


    /**
     * Pega a posição atual do estoque
     */
    @Query("""
        select sum(me.quantidade)
        from MovimentacaoEstoque me
        join me.produto p
        where p.id = :produtoId
    """)
    Optional<BigDecimal> getPosicaoEstoqueDoProduto(@Param("produtoId") Long produtoId);
}
