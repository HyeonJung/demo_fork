package framework.com.example.demo.service.coin;

import framework.com.example.demo.domain.holder.Holder;
import framework.com.example.demo.domain.token.Token;
import framework.com.example.demo.domain.token.TokenMapper;
import framework.com.example.demo.domain.token.tokenVO;
import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.model.network.response.coin.HolderApiResponse;
import framework.com.example.demo.model.network.response.coin.TokenApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenApiService {
    @Autowired
    TokenMapper tokenMapper;

    public Header<TokenApiResponse> insert(tokenVO vo){
        vo.setCreatedDate(LocalDateTime.now());
        vo.setModifiedDate(LocalDateTime.now());

        tokenVO tokenDAO = tokenMapper.findByTokenId(vo);
        if(tokenDAO != null){
            return Header.ERROR("이미 입력");
        }
        try {
            tokenMapper.insertToken(vo);

            return response(vo);
        }catch (Exception ex) {
            return Header.ERROR("토큰 생성중 오류 발생");
        }
    }

    private Header<TokenApiResponse> response(tokenVO vo) {
        //user -> userApiResponse
        TokenApiResponse tokenApiResponse = TokenApiResponse.builder()
                .tokenId(vo.getTokenId())
                .nftCode(vo.getNftCode())
                .build();

        return Header.OK(tokenApiResponse);
    }

}
