package framework.com.example.demo.controller;

import framework.com.example.demo.Interface.CrudInterface;
import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public abstract class ApiController<Req, Res> {

    @GetMapping("")
    public Header<Res> read() {
        return null;
    }

}
