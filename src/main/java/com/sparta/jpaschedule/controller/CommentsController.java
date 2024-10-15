package com.sparta.jpaschedule.controller;


import com.sparta.jpaschedule.dto.CommentsRequestDto;
import com.sparta.jpaschedule.dto.CommentsResponseDto;
import com.sparta.jpaschedule.service.CommentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentsController {
    private final CommentsService commentsService;


    // 댓글 등록
    @PostMapping("/comment/{id}")
    public CommentsResponseDto createComments(@PathVariable Long id, @RequestBody @Valid CommentsRequestDto requestDto) {
        return commentsService.createComments(id, requestDto);
    }

    // 댓글 조회
    @GetMapping("/comment")
    public List<CommentsResponseDto> getComments(){
        return commentsService.getComments();
    }

    // 댓글 수정
    @PutMapping("/comment/{id}")
    public Long updateComments(@PathVariable Long id, @RequestBody @Valid CommentsRequestDto requestDto) {
        return commentsService.updateComments(id, requestDto);
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{id}")
    public Long deleteComments(@PathVariable Long id) {
        return commentsService.deleteComments(id);
    }
}
