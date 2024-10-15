package com.sparta.jpaschedule.repository;

import com.sparta.jpaschedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllBy();

    // 페이징 쿼리
    @Query(value=
            "select" +
                 "    title," +
                 "    content," +
                 "    reg_date," +
                 "    edit_date," +
                 "    (SELECT name FROM members WHERE id = schedules.m_id) as memberName, "+
                 "    (SELECT COUNT(id) FROM comments WHERE s_id = schedules.id) as count "+
                 " from schedules",
            nativeQuery = true
    )
    List<PagingColumn> findAllBy(Pageable pageable);
}
