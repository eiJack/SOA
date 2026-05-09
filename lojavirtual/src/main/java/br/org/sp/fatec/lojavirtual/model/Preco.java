package br.org.sp.fatec.lojavirtual.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Precos")
public class Preco extends ModeloBaseAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="produto_id")
    private Produto produto;

    private BigDecimal valor;

    private Boolean promocao;
}
