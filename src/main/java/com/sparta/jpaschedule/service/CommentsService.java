package com.sparta.jpaschedule.service;
import com.sparta.jpaschedule.dto.CommentsRequestDto;
import com.sparta.jpaschedule.dto.CommentsResponseDto;
import com.sparta.jpaschedule.entity.Comments;
import com.sparta.jpaschedule.entity.Schedule;
import com.sparta.jpaschedule.repository.CommentsRepository;
import com.sparta.jpaschedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final ScheduleRepository scheduleRepository;
    private final CommentsRepository commentsRepository;
    public CommentsResponseDto createComments(Long id, CommentsRequestDto requestDto) {
        findSchedule(id);
        Comments comments = new Comments(requestDto);
        Comments saveComments = commentsRepository.save(comments);
        CommentsResponseDto commentsResponseDto = new CommentsResponseDto(saveComments);
        return commentsResponseDto;
    }

    public List<CommentsResponseDto> getComments() {
        return commentsRepository.findAllBy().stream().map(CommentsResponseDto::new).toList();
    }

    @Transactional
    public Long updateComments(Long id, CommentsRequestDto requestDto) {
        Comments comments = findComments(id);
        comments.update(requestDto);
        return id;
    }

    public Long deleteComments(Long id) {
        Comments comments = findComments(id);
        commentsRepository.delete(comments);
        return id;
    }


    private Comments findComments(Long id) {
        return commentsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.")
        );
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 ID의 일정이 존재하지 않아 댓글을 작성할 수 없습니다.")
        );
    }
}
