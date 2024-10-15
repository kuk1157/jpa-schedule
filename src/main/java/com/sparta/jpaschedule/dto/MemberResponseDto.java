package com.sparta.jpaschedule.dto;

import com.sparta.jpaschedule.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponseDto {
    private Long id;
    private int authority_code;
    private String authority_name;
    private String name;
    private String email;
    private String pw;
    private LocalDateTime reg_date;
    private LocalDateTime edit_date;
    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.authority_code = member.getAuthority_code();
        this.authority_name = String.valueOf(member.getAuthority_name());
        this.name = member.getName();
        this.email = member.getEmail();
        this.pw = member.getPw();
        this.reg_date = member.getReg_date();
        this.edit_date = member.getEdit_date();

    }
}
