package com.sparta.jpaschedule.controller;
import com.sparta.jpaschedule.dto.LoginRequestDto;
import com.sparta.jpaschedule.dto.MemberRequestDto;
import com.sparta.jpaschedule.dto.MemberResponseDto;
import com.sparta.jpaschedule.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    // 유저 등록(회원가입)
    @PostMapping("/member")
    public MemberResponseDto createMember(@RequestBody @Valid MemberRequestDto requestDto) {
        return memberService.createMember(requestDto);
    }

    // 유저 조회
    @GetMapping("/member")
    public List<MemberResponseDto> getMember() {
        return memberService.getMember();
    }

    // 유저 수정
    @PutMapping("/member/{id}")
    public Long updateMember(@PathVariable Long id, @RequestBody @Valid MemberRequestDto requestDto) {
        return memberService.updateMember(id,requestDto);
    }

    // 유저 삭제
    @DeleteMapping("/member/{id}")
    public Long deleteMember(@PathVariable Long id) {
        return memberService.deleteMember(id);
    }

    // 로그인
    @PostMapping("/member/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res) {
        return memberService.login(loginRequestDto,res);
    }
}
