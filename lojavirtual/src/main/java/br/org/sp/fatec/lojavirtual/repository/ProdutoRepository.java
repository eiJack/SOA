package br.org.sp.fatec.lojavirtual.repository;

import br.org.sp.fatec.lojavirtual.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Page<Produto> findAllByNomeIgnoreCaseContaining(Pageable paginacao, String nome);
    Optional<Produto> findOneBySkuIgnoreCase(String sku);
}
