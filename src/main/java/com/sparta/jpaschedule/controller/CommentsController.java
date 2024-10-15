package com.sparta.jpaschedule.controller;


import com.sparta.jpaschedule.dto.CommentsRequestDto;
import com.sparta.jpaschedule.dto.CommentsResponseDto;
import com.sparta.jpaschedule.service.CommentsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentsController {
    private final CommentsService commentsService;
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/comment/{id}")
    public CommentsResponseDto createComments(@PathVariable Long id, @RequestBody @Valid CommentsRequestDto requestDto) {
        return commentsService.createComments(id, requestDto);
    }

    @GetMapping("/comment")
    public List<CommentsResponseDto> getComments(){
        return commentsService.getComments();
    }

    @PutMapping("/comment/{id}")
    public Long updateComments(@PathVariable Long id, @RequestBody @Valid CommentsRequestDto requestDto) {
        return commentsService.updateComments(id, requestDto);
    }

    @DeleteMapping("/comment/{id}")
    public Long deleteComments(@PathVariable Long id) {
        return commentsService.deleteComments(id);
    }
}
