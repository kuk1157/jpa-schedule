package com.sparta.jpaschedule.controller;

import com.sparta.jpaschedule.dto.SchedulePagingDto;
import com.sparta.jpaschedule.dto.ScheduleRequestDto;
import com.sparta.jpaschedule.dto.ScheduleResponseDto;
import com.sparta.jpaschedule.jwt.JwtUtil;
import com.sparta.jpaschedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final JwtUtil jwtUtil;
    @PostMapping("/schedule/{id}")
    public ScheduleResponseDto createSchedule(@PathVariable Long id, @RequestBody @Valid ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(id,requestDto);
    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedule() {
        return scheduleService.getSchedule();
    }

    @GetMapping("/schedule/page")
    public List<SchedulePagingDto> getSchedulePaging(@PageableDefault(size = 10, sort = "edit_date", direction = Sort.Direction.DESC) Pageable pageable) {
        return scheduleService.getSchedulePaging(pageable);
    }

    @PutMapping("/schedule/{id}")
    public Long updateSchedule(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue, @PathVariable Long id, @RequestBody @Valid ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(tokenValue,id,requestDto);
    }

    @DeleteMapping("/schedule/{id}")
    public Long deleteSchedule(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue, @PathVariable Long id) {
        return scheduleService.deleteSchedule(tokenValue,id);
    }

//    @GetMapping("/schedule/test")
//    public String getApi() {
//        return scheduleService.getApi();
//    }

}
