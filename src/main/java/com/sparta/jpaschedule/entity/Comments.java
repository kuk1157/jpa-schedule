package com.sparta.jpaschedule.entity;
import com.sparta.jpaschedule.dto.CommentsRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@Table(name="comments")
@NoArgsConstructor
public class Comments extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "댓글 고유번호")
    private Long id;

    @Column(name="m_id", length=7)
    @Comment(value = "유저 고유번호 - 유저 테이블과 연동")
    private int m_id;

    @JoinColumn(name="s_id" , referencedColumnName = "id")
    @Comment(value = "일정 고유번호 - 일정 테이블과 연동")
    private Long s_id;
    @ManyToOne // 일정테이블과 연관관계 설정
    private Schedule schedule;

    @Column(name="m_name", length=45)
    @Comment(value = "작성 유저명")
    private String m_name;

    @Column(name="content", nullable = false, length=255)
    @Comment(value = "댓글 내용")
    private String content;


    public Comments(CommentsRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.s_id = requestDto.getS_id();
    }

    public void update(CommentsRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}
