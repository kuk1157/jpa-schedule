package com.sparta.jpaschedule.repository;


import java.time.LocalDateTime;

public interface PagingColumn {
    String getTitle();
    String getContent();
    LocalDateTime getReg_date();
    LocalDateTime getEdit_date();
    String getMemberName();
    int getCount();
}
