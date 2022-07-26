package framework.com.example.demo.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class UserApiRequest {
        private Long id;

        private String userid;

        private String password;

        private String status;

        private String phoneNumber;

        private String email;
    }

