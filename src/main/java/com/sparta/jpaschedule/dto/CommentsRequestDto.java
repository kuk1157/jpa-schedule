package com.sparta.jpaschedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentsRequestDto {
    private Long id;
    private int m_id;
    private Long s_id;
    private String m_name;

    @NotBlank(message = "댓글 내용은 필수로 입력해주시길 바랍니다.")
    @Size(max=255, message = "255자 이하로 입력해주시길 바랍니다.")
    private String content;
}
