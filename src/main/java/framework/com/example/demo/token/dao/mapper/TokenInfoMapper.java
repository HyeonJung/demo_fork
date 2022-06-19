package framework.com.example.demo.token.dao.mapper;

import framework.com.example.demo.token.TokenMapping;
import framework.com.example.demo.token.dao.TokenInfoAndTokenMapping;
import framework.com.example.demo.token.dto.TokenInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface TokenInfoMapper {
    ArrayList<TokenInfoAndTokenMapping> getAllTokenInfoList(TokenInfoAndTokenMapping vo);

    List<Map<String, Object>> getHolderInfoList(TokenMapping vo);

    void insertDailyInfo();
    void updateDailyInfoForAmount(TokenInfoDto vo);
}
