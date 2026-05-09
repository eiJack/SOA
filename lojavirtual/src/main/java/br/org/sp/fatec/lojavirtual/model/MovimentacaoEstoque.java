package br.org.sp.fatec.lojavirtual.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao_estoque")
@Data
public class MovimentacaoEstoque extends ModeloBaseAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private BigDecimal quantidade;

    @Column(name = "data_movimentacao")
    private LocalDateTime dataMovimentacao;

}
