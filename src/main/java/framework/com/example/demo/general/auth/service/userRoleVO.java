package framework.com.example.demo.general.auth.service;

import lombok.Data;

@Data
public class userRoleVO {
    private String userLoginId;
    private String roleName;

    public userRoleVO(String userLoginId, String roleName) {
        this.userLoginId = userLoginId;
        this.roleName = roleName;
    }
}
