package br.org.sp.fatec.lojavirtual.repository;

import br.org.sp.fatec.lojavirtual.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findOneByCpf(String cpf);
}
