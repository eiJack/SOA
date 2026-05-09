package br.org.sp.fatec.lojavirtual.repository;

import br.org.sp.fatec.lojavirtual.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("""
        select p from Pedido p
        join p.cliente c
        where (:clienteId is null or c.id = :clienteId)
    """)
    Page<Pedido> buscaPaginada(Pageable paginacao, @Param("clienteId") Long clienteId);
}
