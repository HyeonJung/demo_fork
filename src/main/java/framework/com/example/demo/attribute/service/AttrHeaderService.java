package framework.com.example.demo.attribute.service;

import framework.com.example.demo.attribute.AttrHeader;
import framework.com.example.demo.attribute.dao.repository.AttrHeaderRepository;
import framework.com.example.demo.attribute.dto.HeaderVO;
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
public class AttrHeaderService {

    @Autowired
    private AttrHeaderRepository attrHeaderRepository;
    public void insert(HeaderVO dto){

        AttrHeader header = AttrHeader.builder()
                .header(dto.getHeader())
                .nftCode(dto.getNftCode())
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        attrHeaderRepository.save(header);
    }

}
