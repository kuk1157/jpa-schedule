package com.sparta.jpaschedule.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {
    private Long id;
    private int authority_code;
    private String authority_name;
    private String adminToken = "";

    @NotBlank(message = "이름은 필수로 입력해주시길 바랍니다.")
    @Size(max=45, message = "45자 이하로 입력해주시길 바랍니다.")
    private String name;

    @Size(max=255, message = "255자 이하로 입력해주시길 바랍니다.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "이메일 형식을 맞춰서 입력해주시길 바랍니다.")
    private String email;

    private String pw;

}
