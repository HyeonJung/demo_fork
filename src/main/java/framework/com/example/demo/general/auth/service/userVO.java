package framework.com.example.demo.general.auth.service;

import lombok.Data;


@Data
public class userVO {
    private  String id;
    private  String password;
    public userVO(String id, String password){
        this.id = id;
        this.password = password;
    }
}
