package framework.com.example.demo.controller;

import framework.com.example.demo.model.entity.User;
import framework.com.example.demo.model.network.request.HomeApiRequest;
import framework.com.example.demo.model.network.request.UserApiRequest;
import framework.com.example.demo.model.network.response.HomeApiResponse;
import framework.com.example.demo.model.network.response.UserApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/soldiers")
public class HomeApiController extends ApiController <HomeApiRequest, HomeApiResponse> {

}
