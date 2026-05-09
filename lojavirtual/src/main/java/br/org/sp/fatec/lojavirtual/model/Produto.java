package br.org.sp.fatec.lojavirtual.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Produtos")
public class Produto extends ModeloBaseAuditavel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;

    private String nome;

    private String descricao;

    @Column(name = "unidade_medida")
    private String unidadeMedida;

    private Boolean ativo;

    @Column(name = "foto_url")
    private String fotoUrl;
}
