package com.sparta.jpaschedule.entity;
import com.sparta.jpaschedule.dto.MemberRequestDto;
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
@NoArgsConstructor
@Table(name="members")
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "유저 고유번호")
    private Long id;
    @OneToMany(mappedBy = "m_id")
    private List<Schedule> schedule = new ArrayList<>();

    @Column(name="authority_code", length=2)
    @Comment(value = "유저 권한코드")
    private int authority_code ;

    @Column(name="authority_name", nullable = false)
    @Comment(value = "유저 권한이름")
    @Enumerated(value = EnumType.STRING)
    private AuthorityEnum authority_name;

    @Column(name="name", nullable = false, length=45)
    @Comment(value = "이름")
    private String name;

    @Column(name="email", length=255)
    @Comment(value = "이메일")
    private String email;

    @Column(name="pw", nullable = false, updatable=false, length=255)
    @Comment(value = "비밀번호")
    private String pw;

    public Member(String name, String email, String pw, AuthorityEnum authority_name) {
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.authority_name = authority_name;
    }

    public void update(MemberRequestDto requestDto){
        this.name = requestDto.getName();
        this.email = requestDto.getEmail();
    }
}
