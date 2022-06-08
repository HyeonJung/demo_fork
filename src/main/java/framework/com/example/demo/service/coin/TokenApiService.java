package framework.com.example.demo.service.coin;

import framework.com.example.demo.domain.holder.HolderInfoVO;
import framework.com.example.demo.domain.token.TokenMapper;
import framework.com.example.demo.domain.token.tokenVO;
import framework.com.example.demo.domain.token.tokenmapng.TokenMapngVO;
import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.model.network.response.coin.TokenApiResponse;
import framework.com.example.demo.model.network.response.coin.TokenMapngApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class TokenApiService {
    @Autowired
    TokenMapper tokenMapper;

    public Header<TokenApiResponse> insert(TokenMapngVO vo){
        vo.setCreatedDate(LocalDateTime.now());
        vo.setModifiedDate(LocalDateTime.now());

        tokenVO tokenDAO = tokenMapper.findByTokenId(vo);
        if(tokenDAO != null){
            return Header.ERROR("이미 입력");
        }
        try {
            tokenMapper.insertToken(vo);

            return responseByTokenMapng(vo);
        }catch (Exception ex) {
            return Header.ERROR("토큰 생성중 오류 발생");
        }
    }

    public Header<TokenMapngApiResponse> update(TokenMapngVO vo){
        if(vo.getNftCode() == null || vo.getTokenId() == null){
            return Header.ERROR("token_id와 nft_code는 필수입니다.");
        }

        vo.setCreatedDate(LocalDateTime.now());
        vo.setModifiedDate(LocalDateTime.now());

        TokenMapngVO tokenMapngVO = tokenMapper.findByTokenMapngId(vo);
        if(tokenMapngVO != null) {
            tokenMapper.updateTokenMapng(vo);

            return response(vo);
        }else{
            tokenMapper.insertToken(vo);
            return response(vo);

        }
    }

    public Header<ArrayList<TokenMapngVO>> GetTokenIDList(TokenMapngVO vo){
        try {
            ArrayList<TokenMapngVO> result =  tokenMapper.findByAllTokenID(vo);
            return Header.OK(result);
        } catch (Exception ex) {
            return Header.ERROR("데이터가 없습니다.");
        }
    }

    public Header<ArrayList<TokenMapngVO>> GetAllTokenInfoList(TokenMapngVO vo){
        try {
            ArrayList<TokenMapngVO> result =  tokenMapper.getAllTokenInfoList(vo);
            return Header.OK(result);
        } catch (Exception ex) {
            return Header.ERROR("데이터가 없습니다.");
        }
    }

    public Header<String> updateTokenMapngInit(TokenMapngVO vo){
        try {
            tokenMapper.updateTokenMapngInit(vo);
            return Header.OK("성공");
        } catch (Exception ex) {
            return Header.ERROR("데이터가 없습니다.");
        }
    }

    //홀더 정보 조회
    public Header<ArrayList<HolderInfoVO>> getHolderInfoList(TokenMapngVO vo){
        try {
            ArrayList<HolderInfoVO> result =  tokenMapper.getHolderInfoList(vo);

            return Header.OK(result);
        } catch (Exception ex) {
            return Header.ERROR("데이터가 없습니다.");
        }
    }

    //데일리 레포트 마감
    public Header<String> InsertTSODailyEnd(TokenMapngVO vo){
        try {
            tokenMapper.insertDailyInfo(vo);
            tokenMapper.updateDailyInfoForAmount(vo);
            return Header.OK("OK");
        } catch (Exception ex) {
            return Header.ERROR("데일리 리포트 오류 발생");
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
    private Header<TokenApiResponse> responseByTokenMapng(TokenMapngVO vo) {
        //user -> userApiResponse
        TokenApiResponse tokenApiResponse = TokenApiResponse.builder()
                .tokenId(vo.getTokenId())
                .nftCode(vo.getNftCode())
                .build();

        return Header.OK(tokenApiResponse);
    }
    private Header<TokenMapngApiResponse> response(TokenMapngVO vo) {
        //user -> userApiResponse
        TokenMapngApiResponse tokenApiResponse = TokenMapngApiResponse.builder()
                .tokenId(vo.getTokenId())
                .address(vo.getRAddress())
                .getStatus(vo.getRGetStatus())
                .statkingStatus(vo.getStakingStatus())
                .nftCode(vo.getNftCode())
                .build();

        return Header.OK(tokenApiResponse);
    }


}
