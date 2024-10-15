package com.sparta.jpaschedule.repository;
import java.time.LocalDateTime;

// 페이징관련 6가지 데이터 인터페이스
public interface PagingColumn {
    String getTitle();
    String getContent();
    LocalDateTime getReg_date();
    LocalDateTime getEdit_date();
    String getMemberName(); // 작성자 유저명 (서브쿼리)
    int getCount(); // 댓글 개수 (서브쿼리)
}
