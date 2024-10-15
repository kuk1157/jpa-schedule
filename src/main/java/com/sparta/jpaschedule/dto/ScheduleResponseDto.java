package com.sparta.jpaschedule.dto;
import com.sparta.jpaschedule.entity.Schedule;
import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private Long m_id;
    private String m_name;
    private String title;
    private String content;
    private String weather;
    private Date reg_date;
    private Date edit_date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.m_id = schedule.getM_id();
        this.m_name = schedule.getM_name();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.weather = schedule.getWeather();
        this.reg_date = schedule.getReg_date();
        this.edit_date = schedule.getEdit_date();
    }
}
