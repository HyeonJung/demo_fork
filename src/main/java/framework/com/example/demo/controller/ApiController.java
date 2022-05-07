package framework.com.example.demo.controller;

import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.model.network.response.HomeApiResponse;
import framework.com.example.demo.service.HomeApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Component
public abstract class ApiController<Req, Res> {
    @Autowired(required = false)
    protected HomeApiLogicService homeApiLogicService;

    @GetMapping("")
    public Header<HomeApiResponse> read() throws IOException {
        return homeApiLogicService.read();
    }

}
