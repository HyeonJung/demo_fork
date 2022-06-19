package framework.com.example.demo.controller.coin;

import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.service.coin.CoinService;
import framework.com.example.demo.token.TokenMapping;
import framework.com.example.demo.token.dao.TokenInfoAndTokenMapping;
import framework.com.example.demo.token.dto.TokenInfoDto;
import framework.com.example.demo.token.service.TokenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TokenInfoAPIController {
    @Autowired
    TokenInfoService tokenInfoService;

    @Autowired
    private CoinService coinService;

    @PostMapping("/api/tokeninfo/save")
    public Header<TokenInfoDto> insert(@RequestBody TokenInfoDto request){
        return tokenInfoService.insert(request);
    }


    @PostMapping("/api/tokeninfo/update")
    public Header<TokenMapping> update(@RequestBody TokenMapping request){
        return tokenInfoService.update(request);
    }

    @PostMapping("/api/tokeninfo/getalltokeninfolist")
    public Header<ArrayList<TokenInfoAndTokenMapping>> GetAllTokenInfoList(@RequestBody Header<TokenInfoAndTokenMapping> request){
        return tokenInfoService.GetAllTokenInfoList(request.getData());
    }
    @PostMapping("/api/tokeninfo/getholderlist")
    public Header<List<Map<String, Object>>> GetHolderList(@RequestBody Header<TokenMapping> request){
        return tokenInfoService.getHolderInfoList(request.getData());
    }

    @PostMapping("/api/tokeninfo/insert_tso_daily_end")
    public Header<String> InsertTSODailyEnd(@RequestBody Header<TokenInfoDto> request){
        return tokenInfoService.InsertTSODailyEnd(request.getData());
    }
}
