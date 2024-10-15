package com.sparta.jpaschedule.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {

    private Long id;
    private Long m_id;
    private String m_name;
    private String weather;

    @NotBlank(message = "할일 제목은 필수로 입력해주시길 바랍니다.")
    @Size(max=255, message = "255자 이하로 입력해주시길 바랍니다.")
    private String title;

    @NotBlank(message = "할일 내용은 필수로 입력해주시길 바랍니다.")
    @Size(max=255, message = "255자 이하로 입력해주시길 바랍니다.")
    private String content;


}
