package framework.com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String email;

    private String phoneNumber;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    private String password;

    private String name;

}
