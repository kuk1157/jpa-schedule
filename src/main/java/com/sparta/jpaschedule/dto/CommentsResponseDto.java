package com.sparta.jpaschedule.dto;
import com.sparta.jpaschedule.entity.Comments;

import lombok.Getter;


import java.util.Date;

@Getter
public class CommentsResponseDto {
    private Long id;
    private int m_id;
    private Long s_id;
    private String m_name;
    private String content;
    private Date reg_date;
    private Date edit_date;

    public CommentsResponseDto(Comments comments) {
        this.id = comments.getId();
        this.m_id = comments.getM_id();
        this.s_id = comments.getS_id();
        this.m_name = comments.getM_name();
        this.content = comments.getContent();
        this.reg_date = comments.getReg_date();
        this.edit_date = comments.getEdit_date();
    }
}
