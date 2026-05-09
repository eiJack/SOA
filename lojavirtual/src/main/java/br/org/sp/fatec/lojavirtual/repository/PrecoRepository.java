package br.org.sp.fatec.lojavirtual.repository;

import br.org.sp.fatec.lojavirtual.model.Preco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrecoRepository extends JpaRepository<Preco, Long> {

    Optional<Preco> findFirstPrecoByProdutoIdOrderByCreatedAtDesc(Long produtoId);
}
