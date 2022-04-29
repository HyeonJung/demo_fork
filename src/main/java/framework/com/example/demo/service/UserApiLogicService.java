package framework.com.example.demo.service;

import framework.com.example.demo.model.entity.User;
import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.model.network.request.UserApiRequest;
import framework.com.example.demo.model.network.response.UserApiResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        UserApiRequest userApiRequest = request.getData();

        User user = User.builder()
                .userid(userApiRequest.getUserid())
                .password(userApiRequest.getPassword())
                .phoneNumber(userApiRequest.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .createdBy("admin")
                .build();

        User newUser = baseRepository.save(user);
        return response(newUser);
    }
    @Override
    public Header<UserApiResponse> create(UserApiRequest request) {

        UserApiRequest userApiRequest = request;

        User user = User.builder()
                .userid(userApiRequest.getUserid())
                .password(userApiRequest.getPassword())
                .phoneNumber(userApiRequest.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .createdBy("admin")
                .build();

        User newUser = baseRepository.save(user);
        return response(newUser);
    }
    @Override
    public Header<UserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(user-> response(user))
                .orElseGet(()-> Header.ERROR("데이터 없음"));

    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());

        return optional.map(user-> {
            user.setUserid(userApiRequest.getUserid())
                .setEmail(userApiRequest.getEmail())
                    .setUpdatedAt(LocalDateTime.now())
                    .setPhoneNumber(userApiRequest.getPhoneNumber());
            return user;
        })
                .map(user-> baseRepository.save(user))
                .map(user->response(user))
                .orElseGet(()->Header.ERROR("데이터 없음"));



    }

    @Override
    public Header delete(Long id) {
        //id -> repository -> user find

         Optional<User> optional = baseRepository.findById(id);

        // repository -> delete
        // return response
        return optional.map(user->{
                baseRepository.delete(user);
                return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));


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
