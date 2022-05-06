package framework.com.example.demo.service;

import framework.com.example.demo.model.entity.User;
import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.model.network.request.UserApiRequest;
import framework.com.example.demo.model.network.response.HomeApiResponse;
import framework.com.example.demo.model.network.response.UserApiResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class HomeApiLogicService {


    public Header<HomeApiResponse> read(Long id) {

        return  new Header<HomeApiResponse>();

    }


    private Header<UserApiResponse> response(User user){
        //user -> userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .userid(user.getUserid())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .build();

        return Header.OK(userApiResponse);
    }
}
