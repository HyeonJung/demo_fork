package framework.com.example.demo.attribute.service;

import framework.com.example.demo.attribute.AttrDetail;
import framework.com.example.demo.attribute.AttrHeader;
import framework.com.example.demo.attribute.dao.repository.AttrDetailRepository;
import framework.com.example.demo.attribute.dao.repository.AttrHeaderRepository;
import framework.com.example.demo.attribute.dto.DetailVO;
import framework.com.example.demo.attribute.dto.HeaderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AttrDetailService {

    @Autowired
    private AttrDetailRepository attrDetailRepository;
    public void insert(DetailVO dto){

        AttrDetail detail = AttrDetail.builder()
                .header(dto.getHeader())
                .nftCode(dto.getNftCode())
                .tokenId(dto.getTokenId())
                .detail(dto.getDetail())
                .idx(dto.getIdx())
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        attrDetailRepository.save(detail);
    }

}
