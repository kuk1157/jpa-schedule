package com.sparta.jpaschedule.dto;
import com.sparta.jpaschedule.repository.PagingColumn;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class SchedulePagingDto {
    private String title;
    private String content;
    private LocalDateTime reg_date;
    private LocalDateTime edit_date;
    private String memberName; // 작성자 유저명 (서브쿼리)
    private int count; // 댓글 개수 (서브쿼리)

    public SchedulePagingDto(PagingColumn pagingColumn) {
        this.title = pagingColumn.getTitle();
        this.content = pagingColumn.getContent();
        this.reg_date = pagingColumn.getReg_date();
        this.edit_date = pagingColumn.getEdit_date();
        this.memberName = pagingColumn.getMemberName();
        this.count = pagingColumn.getCount();
    }
}
