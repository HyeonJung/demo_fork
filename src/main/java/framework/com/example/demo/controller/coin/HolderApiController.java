package framework.com.example.demo.controller.coin;

import framework.com.example.demo.controller.CurdController;
import framework.com.example.demo.domain.holder.Holder;
import framework.com.example.demo.domain.holder.HolderMapper;
import framework.com.example.demo.model.dao.holder;
import framework.com.example.demo.model.network.request.coin.HolderApiRequest;
import framework.com.example.demo.model.network.response.coin.HolderApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api/holder")
public class HolderApiController extends CurdController<HolderApiRequest, HolderApiResponse, Holder> {

    @Autowired
    HolderMapper holderMapper;

    @GetMapping
    public ArrayList<holder> read(){

        ArrayList<holder> result = holderMapper.selectHolder();
        return result;
    }
}
