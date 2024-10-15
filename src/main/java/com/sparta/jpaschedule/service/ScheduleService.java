package com.sparta.jpaschedule.service;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.gson.*;
import com.google.gson.JsonArray;
import com.sparta.jpaschedule.dto.SchedulePagingDto;
import com.sparta.jpaschedule.dto.ScheduleResponseDto;
import com.sparta.jpaschedule.dto.ScheduleRequestDto;
import com.sparta.jpaschedule.entity.Member;
import com.sparta.jpaschedule.entity.Schedule;
import com.sparta.jpaschedule.jwt.JwtUtil;
import com.sparta.jpaschedule.repository.MemberRepository;
import com.sparta.jpaschedule.repository.ScheduleRepository;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestTemplate;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate;

    public ScheduleService(ScheduleRepository scheduleRepository, JwtUtil jwtUtil, RestTemplateBuilder builder, ParameterNamesModule parameterNamesModule, MemberRepository memberRepository) {
        this.scheduleRepository = scheduleRepository;
        this.jwtUtil = jwtUtil;
        this.restTemplate = builder.build();
        this.memberRepository = memberRepository;
    }

    public ScheduleResponseDto createSchedule(Long id, ScheduleRequestDto requestDto) {
        findMember(id);
        String weather = getWeatherFromApi();
        requestDto.setWeather(weather);
        Schedule schedule = new Schedule(requestDto);
        Schedule saveSchedule = scheduleRepository.save(schedule);
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);
        return scheduleResponseDto;
    }

    //
    public List<ScheduleResponseDto> getSchedule() {
        return scheduleRepository.findAllBy().stream().map(ScheduleResponseDto::new).toList();
    }

    public List<SchedulePagingDto> getSchedulePaging(Pageable pageable) {
        return scheduleRepository.findAllBy(pageable).stream().map(SchedulePagingDto::new).toList();
    }

    @Transactional
    public Long updateSchedule(String tokenValue, Long id, ScheduleRequestDto requestDto) {
        String token = jwtUtil.substringToken(tokenValue);
        if(!jwtUtil.validateToken(token)){
            throw new IllegalArgumentException("Token Error");
        }
        Claims info = jwtUtil.getUserInfoFromToken(token);
        String authority = (String) info.get(JwtUtil.AUTHORIZATION_KEY);
        if(Objects.equals(authority, "ADMIN")){
            Schedule schedule = findSchedule(id);
            schedule.update(requestDto);
        }else{
            throw new IllegalArgumentException("일정 수정을 할 수 없는 권한입니다.");
        }
        return id;
    }

    public Long deleteSchedule(String tokenValue, Long id) {
        String token = jwtUtil.substringToken(tokenValue);
        if(!jwtUtil.validateToken(token)){
            throw new IllegalArgumentException("Token Error");
        }
        Claims info = jwtUtil.getUserInfoFromToken(token);
        String authority = (String) info.get(JwtUtil.AUTHORIZATION_KEY);

        if(Objects.equals(authority, "ADMIN")){
            Schedule schedule = findSchedule(id);
            scheduleRepository.delete(schedule);
        }else{
            throw new IllegalArgumentException("일정 삭제를 할 수 없는 권한입니다.");
        }
        return id;
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }

    private Member findMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("유저가 존재하지 않아 일정을 등록할 수 없습니다.")
        );
    }

    public String getWeatherFromApi() {
        String url = "https://f-api.github.io/f-api/weather.json";
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd");
        Date now = new Date();
        String nowTime = sdf2.format(now);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String str = responseEntity.getBody();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonElements = gson.fromJson(str, JsonArray.class);
        for(JsonElement obj: jsonElements) {
            if(obj.getAsJsonObject().get("date").getAsString().equals(nowTime)){
                return obj.getAsJsonObject().get("weather").getAsString();
            }
        }
        return "nothing";
    }
}



