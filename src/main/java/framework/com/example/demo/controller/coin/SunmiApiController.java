package framework.com.example.demo.controller.coin;


import framework.com.example.demo.model.network.request.coin.SunmiApiRequestDto;
import framework.com.example.demo.model.network.response.coin.SunmiApiResponseDto;
import framework.com.example.demo.service.coin.SunmiApiService;
import framework.com.example.demo.service.coin.SunmiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/sun-mi")
public class SunmiApiController {
    @Autowired
    private SunmiApiService sunmiApiService;

    @Autowired
    private SunmiLogicService sunmiLogicService;

    @PostMapping("/sun-mi")
    public Long save(@RequestBody SunmiApiRequestDto requestDto) {
        return sunmiApiService.save(requestDto);
    }

    @ResponseBody
    @PutMapping("/sun-mi/{fp}")
    public String update(@PathVariable  String fp) {
        sunmiApiService.update(fp);
        return "";
    }

    @GetMapping("/sun-mi")
    public SunmiApiResponseDto findByAll(){
        return sunmiApiService.findByAll();
    }


    //나중에 옮기기 다른컨트롤러로
    //더 솔져스 하루 이동물량 수집
    @GetMapping("/gettsodayamount")
    public void getTSODayAmount() throws IOException, InterruptedException {
            sunmiLogicService.GetTsoDayAmount();
    }

    //베이비몽즈 하루 이동물량 수집
    @GetMapping("/getbmzdayamount")
    public void getBmzDayAmount() throws IOException, InterruptedException {
        sunmiLogicService.GetTsoDayAmount();
    }
}
