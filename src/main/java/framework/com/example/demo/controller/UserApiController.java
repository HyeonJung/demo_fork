package framework.com.example.demo.controller;

import framework.com.example.demo.model.entity.User;
import framework.com.example.demo.model.network.request.UserApiRequest;
import framework.com.example.demo.model.network.response.UserApiResponse;
import framework.com.example.demo.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CurdController<UserApiRequest, UserApiResponse, User> {

}
