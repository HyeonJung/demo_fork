package framework.com.example.demo.model.dto;

import framework.com.example.demo.model.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private String userid;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Member toEntity(){
        return Member.builder()
                .userid(userid)
                .email(email)
                .password(password)
                .createdAt(LocalDateTime.now())
                .createdBy("admin")
                .build();
    }

    @Builder
    public MemberDto(String userid, String email, String password) {
        this.userid = userid;
        this.email = email;
        this.password = password;
    }
}
