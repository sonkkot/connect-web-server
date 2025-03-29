package site.copi.common.infrastructure.database.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import site.copi.common.util.TimeUtil;

import java.time.Instant;
import java.time.ZonedDateTime;

import static java.lang.Boolean.TRUE;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CopiBaseJpaEntity {
    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @Column(nullable = false)
    private Boolean isDeleted;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.isDeleted = Boolean.FALSE;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }

    public ZonedDateTime getCreatedAt() {
        return TimeUtil.convert(this.createdAt);
    }

    public ZonedDateTime getUpdatedAt() {
        return TimeUtil.convert(this.updatedAt);
    }

    public boolean isDeleted() {
        return TRUE.equals(this.isDeleted);
    }
}