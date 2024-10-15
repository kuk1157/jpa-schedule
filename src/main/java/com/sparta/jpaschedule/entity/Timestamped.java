package com.sparta.jpaschedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
// 작성일, 수정일 전체 ENTITY 에 적용
public abstract class Timestamped {

    @CreatedDate
    @Column(name="reg_date", updatable = false)
    @Comment(value = "작성일 - 작성되는 동시 자동")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime reg_date;

    @LastModifiedDate
    @Column(name="edit_date")
    @Comment(value = "수정일 - 수정할때 UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime edit_date;
}

