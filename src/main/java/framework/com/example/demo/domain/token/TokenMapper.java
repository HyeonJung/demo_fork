package framework.com.example.demo.domain.token;

import framework.com.example.demo.domain.holder.HolderInfoVO;
import framework.com.example.demo.domain.token.tokenmapng.TokenMapng;
import framework.com.example.demo.domain.token.tokenmapng.TokenMapngVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TokenMapper {
        void insertToken(tokenVO vo);

        tokenVO findByTokenId(tokenVO vo);
        TokenMapngVO findByTokenMapngId(TokenMapngVO vo);

        void updateTokenMapng(TokenMapngVO vo);

        ArrayList<TokenMapngVO> findByAllTokenID(TokenMapngVO vo);

        ArrayList<TokenMapngVO> getAllTokenInfoList(TokenMapngVO vo);

        void updateTokenMapngInit(TokenMapngVO vo);

        ArrayList<HolderInfoVO> getHolderInfoList(TokenMapngVO vo);


        void insertDailyInfo(TokenMapngVO vo);
        void updateDailyInfoForAmount(TokenMapngVO vo);
}
