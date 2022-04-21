package framework.com.example.demo.general.auth.service;

import java.util.ArrayList;
import java.util.List;

public class MemberServiceImpl implements MemberService{
    @Override
    public userVO getUser(String loginUserId){
        return new userVO("carmel","dasdaasda");
    }

    @Override
    public List<userRoleVO> getUserRoles(String loginUserId){
        List<userRoleVO> list = new ArrayList<>();
        list.add(new userRoleVO("carami", "ROLE_USER"));
        return list;
    }
}
