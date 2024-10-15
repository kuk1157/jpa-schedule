package com.sparta.jpaschedule.controller;
import com.sparta.jpaschedule.dto.LoginRequestDto;
import com.sparta.jpaschedule.dto.MemberRequestDto;
import com.sparta.jpaschedule.dto.MemberResponseDto;
import com.sparta.jpaschedule.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member")
    public MemberResponseDto createMember(@RequestBody @Valid MemberRequestDto requestDto) {
        return memberService.createMember(requestDto);
    }

    @GetMapping("/member")
    public List<MemberResponseDto> getMember() {
        return memberService.getMember();
    }

    @PutMapping("/member/{id}")
    public Long updateMember(@PathVariable Long id, @RequestBody @Valid MemberRequestDto requestDto) {
        return memberService.updateMember(id,requestDto);
    }

    @DeleteMapping("/member/{id}")
    public Long deleteMember(@PathVariable Long id) {
        return memberService.deleteMember(id);
    }
    
    @PostMapping("/member/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res) {
        return memberService.login(loginRequestDto,res);
    }
}
