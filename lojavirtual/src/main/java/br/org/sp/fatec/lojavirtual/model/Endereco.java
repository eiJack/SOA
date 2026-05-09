package br.org.sp.fatec.lojavirtual.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "Enderecos")
@Data
public class Endereco extends ModeloBaseAuditavel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ATIVIDADE PARA ENTREGAR */
    private String logradouro;

    private String bairro;

    private Integer numero; //integer é igual ao int mas permite valor nulo

    private String complemento;

    @Column(length = 9)
    private String cep;

    private String cidade;

    private String estado;

    //conectando com o cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true)
    private Cliente cliente;
}
