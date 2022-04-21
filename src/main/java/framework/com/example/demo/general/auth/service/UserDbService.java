package framework.com.example.demo.general.auth.service;

import java.util.List;

public interface UserDbService {
    public userVO getUser(String loginUserId);
    public List<userRoleVO> getUserRoles(String loginUserId);
}
