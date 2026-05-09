package br.org.sp.fatec.lojavirtual.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class ModeloBaseAuditavel {

    @Column(name="created_at", updatable = false)
    @CreationTimestamp // Hibernate preenche na criação
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp // Hibernate preenche em cada alteração
    private LocalDateTime updatedAt;
}
