package framework.com.example.demo.controller.coin;

import framework.com.example.demo.domain.token.Token;
import framework.com.example.demo.domain.token.TokenMapper;
import framework.com.example.demo.domain.token.tokenVO;
import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.model.network.response.coin.TokenApiResponse;
import framework.com.example.demo.service.coin.TokenApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TokenApiController {
    @Autowired
    TokenApiService tokenApiService;

    @PostMapping("/api/token")
    public Header<TokenApiResponse> insert(@RequestBody Header<tokenVO> request){
        return tokenApiService.insert(request.getData());
    }
}
