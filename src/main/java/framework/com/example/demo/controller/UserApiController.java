package framework.com.example.demo.controller;

import framework.com.example.demo.ifs.CrudInterface;
import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.model.network.UserApiRequest;
import framework.com.example.demo.model.network.response.UserApiResponse;
import framework.com.example.demo.service.UserApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("")
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<UserApiResponse>  read(@PathVariable(name="id") Long id) {
        return null;
    }


    @Override
    @PutMapping("")
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest>  userApiRequest) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
