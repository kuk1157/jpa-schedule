package com.sparta.jpaschedule.service;
import com.sparta.jpaschedule.dto.LoginRequestDto;
import com.sparta.jpaschedule.dto.MemberRequestDto;
import com.sparta.jpaschedule.dto.MemberResponseDto;
import com.sparta.jpaschedule.entity.AuthorityEnum;
import com.sparta.jpaschedule.entity.Member;
import com.sparta.jpaschedule.jwt.JwtUtil;
import com.sparta.jpaschedule.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final String ADMIN_TOKEN = "AAABnwioslwoekHHHTBcddeeWWSliL"; // 회원가입 관리자 권한 토큰값

    // 유저 등록(회원가입)
    public MemberResponseDto createMember(MemberRequestDto requestDto) {
        String name = requestDto.getName();
        String email = requestDto.getEmail();
        String pw = passwordEncoder.encode(requestDto.getPw());

        String pws = requestDto.getPw();
        if(pws == null || pws.isEmpty()){
            throw new IllegalArgumentException("비밀번호는 필수로 입력해주시길 바랍니다.");
        }

        if(pws.length() < 8){
            throw new IllegalArgumentException("비밀번호는 최소 8자리 이상 입력해주시길 바랍니다.");
        }

        Optional<Member> checkName = memberRepository.findByName(name);
        if (checkName.isPresent()) {
            throw new IllegalArgumentException("중복된 이름(아이디)입니다. 다른 이름(아이디)를 입력해주시기 바랍니다.");
        }

        Optional<Member> checkEmail = memberRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 이메일입니다. 다른 이메일을 입력해주시기 바랍니다.");
        }

        AuthorityEnum authority_name = AuthorityEnum.MEMBER;
        String admin_token = requestDto.getAdminToken();
        if(admin_token != null && !admin_token.isEmpty()){
            if(!ADMIN_TOKEN.equals(admin_token)){
                throw new IllegalArgumentException("관리자 암호가 일치하지 않아 등록을 할 수 없습니다. 확인바랍니다.");
            }
            authority_name  = AuthorityEnum.ADMIN;
        }

        Member member = new Member(name,email,pw,authority_name);
        Member saveMember = memberRepository.save(member);
        MemberResponseDto memberResponseDto = new MemberResponseDto(saveMember);
        return memberResponseDto;
    }

    // 유저 조회
    public List<MemberResponseDto> getMember() {
        return memberRepository.findAllBy().stream().map(MemberResponseDto::new).toList();
    }

    // 유저 수정
    @Transactional
    public Long updateMember(Long id, MemberRequestDto requestDto) {
        Member member = findMember(id);
        member.update(requestDto);
        return id;
    }

    // 유저 삭제
    public Long deleteMember(Long id) {
        Member member = findMember(id);
        memberRepository.delete(member);
        return id;
    }

    // 유저 ID 조회
    private Member findMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 유저는 존재하지 않습니다.")
        );
    }

    // 로그인 기능
    public String login(LoginRequestDto loginRequestDto, HttpServletResponse res) {
        String email = loginRequestDto.getEmail();
        String pw = loginRequestDto.getPw();

        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("등록된 이메일이 없습니다.")
        );

        if (!passwordEncoder.matches(pw, member.getPw())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 토큰 생성
        String token = jwtUtil.createToken(member.getEmail(), member.getAuthority_name());

        // 토큰 저장
        jwtUtil.addJwtToCookie(token,res);

        // 토큰 가공
        String tokenValue = jwtUtil.substringToken(token);

        // 토큰 예외처리
        jwtUtil.validateToken(tokenValue);

        return "회원정보 : "+jwtUtil.getUserInfoFromToken(tokenValue)+"\n로그인 성공";
    }
}
