package com.study.boardback.entity.baseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDateTimeEntity {

    @CreatedDate
    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;

    @LastModifiedDate
    @Column(name = "upd_dt", nullable = true)
    private LocalDateTime updDt;

}
