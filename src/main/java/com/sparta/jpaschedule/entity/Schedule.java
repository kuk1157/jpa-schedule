package com.sparta.jpaschedule.entity;
import com.sparta.jpaschedule.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="schedules")
@NoArgsConstructor
public class Schedule extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "일정 고유번호")
    private Long id;
    @OneToMany(mappedBy = "s_id", cascade = CascadeType.REMOVE)
    private List<Comments> comments = new ArrayList<>();

    @JoinColumn(name="m_id" , referencedColumnName = "id")
    @Comment(value = "유저 고유번호 - 유저 테이블과 연동")
    private Long m_id;
    @ManyToOne // 유저테이블과 연관관계 설정
    private Member member;

    @Column(name="m_name", length=45)
    @Comment(value = "작성 유저명")
    private String m_name;

    @Column(name="title", nullable = false, length=255)
    @Comment(value = "할일 제목")
    private String title;

    @Column(name="content", nullable = false, length=255)
    @Comment(value = "할일 내용")
    private String content;

    @Column(name="weather", length=50)
    @Comment(value = "날씨")
    private String weather;

    public Schedule(ScheduleRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.m_id = requestDto.getM_id();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
