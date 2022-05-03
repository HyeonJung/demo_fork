package framework.com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Accessors(chain = true)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userid;

    private String email;

    private String phoneNumber;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    private String password;

    private String name;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }
}
