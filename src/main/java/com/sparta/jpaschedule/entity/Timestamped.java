package com.sparta.jpaschedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Timestamped {

    @CreatedDate
    @Column(updatable = false)
    @Comment(value = "작성일 - 작성되는 동시 자동")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reg_date;

    @LastModifiedDate
    @Column
    @Comment(value = "수정일 - 수정할때 UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date edit_date;
}
