package framework.com.example.demo.token.service;

import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.token.TokenInfo;
import framework.com.example.demo.token.TokenInfoPK;
import framework.com.example.demo.token.TokenMapping;
import framework.com.example.demo.token.dao.TokenInfoAndTokenMapping;
import framework.com.example.demo.token.dao.mapper.TokenInfoMapper;
import framework.com.example.demo.token.dao.repository.TokenInfoRepository;
import framework.com.example.demo.token.dao.repository.TokenMappingRepository;
import framework.com.example.demo.token.dto.TokenInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TokenInfoService {
    @Autowired
    TokenInfoMapper tokenInfoMapper;

    @Autowired
    TokenInfoRepository tokenInfoRepository;

    @Autowired
    TokenMappingRepository tokenMappingRepository;

    public Header<TokenInfoDto> insert(TokenInfoDto dto){
        TokenInfo tokenInfo = TokenInfo.builder()
                .tokenId(dto.getTokenId())
                .nftCode(dto.getNftCode())
                .imageUrl(dto.getImageUrl())
                .description(dto.getDescription())
                .createDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        TokenMapping tokenMapping = TokenMapping.builder()
                .tokenId(dto.getTokenId())
                .nftCode(dto.getNftCode())
                .getStatus("Satus")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        TokenInfoPK tokenInfoPK = new TokenInfoPK();
        tokenInfoPK.setTokenId(dto.getTokenId());
        tokenInfoPK.setNftCode(dto.getNftCode());

        Optional<TokenInfo> token = tokenInfoRepository.findById(tokenInfoPK);

        if(token == null){
            return Header.ERROR("이미 입력된 토큰정보입니다");
        }else{
            tokenInfoRepository.save(tokenInfo);
            tokenMappingRepository.save(tokenMapping);
            return Header.OK(dto);
        }
    }

    public Header<TokenMapping> update(TokenMapping dto){
        if(dto.getNftCode() == null || dto.getTokenId() == null){
            return Header.ERROR("token_id와 nft_code는 필수입니다.");
        }

        dto.setCreatedDate(LocalDateTime.now());
        dto.setModifiedDate(LocalDateTime.now());

        TokenInfoPK tokenInfoPK = new TokenInfoPK();
        tokenInfoPK.setTokenId(dto.getTokenId());
        tokenInfoPK.setNftCode(dto.getNftCode());

        Optional<TokenMapping> optionalTokenMapping = tokenMappingRepository.findById(tokenInfoPK);
        TokenMapping result = optionalTokenMapping.isPresent()?optionalTokenMapping.get():null;

        TokenMapping tokenMapping = TokenMapping.builder()
                .tokenId(dto.getTokenId())
                .nftCode(dto.getNftCode())
                .address(dto.getAddress())
                .stakingStatus(dto.getStakingStatus())
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .getStatus("None")
                .build();

        if(result != null) {
            tokenMapping.setCreatedDate(result.getCreatedDate());
            tokenMappingRepository.save(tokenMapping);
            return Header.OK(dto);
        }else{

            TokenInfo tokenInfo = TokenInfo.builder()
                    .tokenId(dto.getTokenId())
                    .nftCode(dto.getNftCode())
                    .imageUrl("")
                    .description("")
                    .createDate(LocalDateTime.now())
                    .modifiedDate(LocalDateTime.now())
                    .build();

            tokenInfoRepository.save(tokenInfo);
            tokenMappingRepository.save(tokenMapping);
            return Header.OK(dto);

        }
    }

    public Header<ArrayList<TokenInfoAndTokenMapping>> GetAllTokenInfoList(TokenInfoAndTokenMapping vo){
        try {
            ArrayList<TokenInfoAndTokenMapping> result =  tokenInfoMapper.getAllTokenInfoList(vo);
            return Header.OK(result);
        } catch (Exception ex) {
            return Header.ERROR("데이터가 없습니다.");
        }
    }

    /**
     * 홀더정보 조회
     * @param vo
     * @return
     */
    public Header<List<Map<String, Object>> > getHolderInfoList(TokenMapping vo){
        try {
            List<Map<String, Object>> result =  tokenInfoMapper.getHolderInfoList(vo);

            return Header.OK(result);
        } catch (Exception ex) {
            return Header.ERROR("데이터가 없습니다.");
        }
    }

    public Header<String> InsertTSODailyEnd(TokenInfoDto vo){
        try {
            tokenInfoMapper.insertDailyInfo();
            tokenInfoMapper.updateDailyInfoForAmount(vo);

            return Header.OK("OK");

        } catch (Exception ex) {
            return Header.ERROR("데일리 리포트 오류 발생");

        }
    }
}
