package br.org.sp.fatec.lojavirtual.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "Pedido_Itens")
@Data
public class PedidoItem extends ModeloBaseAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    private BigDecimal quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal preco;
}
