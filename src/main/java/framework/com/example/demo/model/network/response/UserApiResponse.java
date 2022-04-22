package framework.com.example.demo.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiResponse {
    private Long id;

    private String account;

    private String password;

    private  String status;

    private String email;

    private String phoneNumber;

    private String createdAt;

    private String createdBy;
}
