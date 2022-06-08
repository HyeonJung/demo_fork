package framework.com.example.demo.controller.coin;

import framework.com.example.demo.domain.holder.HolderInfoVO;
import framework.com.example.demo.domain.token.tokenVO;
import framework.com.example.demo.domain.token.tokenmapng.TokenMapngVO;
import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.model.network.response.coin.TokenApiResponse;
import framework.com.example.demo.model.network.response.coin.TokenMapngApiResponse;
import framework.com.example.demo.service.coin.TokenApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TokenApiController {
    @Autowired
    TokenApiService tokenApiService;

    @PostMapping("/api/token")
    public Header<TokenApiResponse> insert(@RequestBody Header<TokenMapngVO> request){
        return tokenApiService.insert(request.getData());
    }

    @PostMapping("/api/tokenmapng")
    public Header<TokenMapngApiResponse> update(@RequestBody Header<TokenMapngVO> request){
        return tokenApiService.update(request.getData());
    }

    @PostMapping("/api/gettokenmapng")
    public Header<ArrayList<TokenMapngVO>> GetTokenIDList(@RequestBody Header<TokenMapngVO> request){
        return tokenApiService.GetTokenIDList(request.getData());
    }

    @PostMapping("/api/getalltokeninfolist")
    public Header<ArrayList<TokenMapngVO>> GetAllTokenInfoList(@RequestBody Header<TokenMapngVO> request){
        return tokenApiService.GetAllTokenInfoList(request.getData());
    }

    @PutMapping("/api/tokenmapnginit")
    public Header<String> TokenMapngInit(@RequestBody Header<TokenMapngVO> request){
        return tokenApiService.updateTokenMapngInit(request.getData());
    }


    @PostMapping("/api/getholderlist")
    public Header<ArrayList<HolderInfoVO>> GetHolderList(@RequestBody Header<TokenMapngVO> request){
        return tokenApiService.getHolderInfoList(request.getData());
    }

    @PostMapping("/api/insert_tso_daily_end")
    public Header<String> InsertTSODailyEnd(@RequestBody Header<TokenMapngVO> request){
        return tokenApiService.InsertTSODailyEnd(request.getData());
    }
}
