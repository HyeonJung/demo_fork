package framework.com.example.demo.token.service;

import framework.com.example.demo.token.FloorPriceInfo;
import framework.com.example.demo.token.dao.repository.FloorPriceInfoRepository;
import framework.com.example.demo.token.dto.FloorPriceInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FloorPriceInfoService {
    @Autowired
    FloorPriceInfoRepository floorPriceInfoRepository;

    public String getFP(String nftCode)
    {
        Optional<FloorPriceInfo> fp = floorPriceInfoRepository.findById(nftCode);
        return fp.isPresent() ? fp.get().getFp() : "0";
    }

    public void setFP(FloorPriceInfoDto dto){
        FloorPriceInfo floorPriceInfo = new FloorPriceInfo();
        floorPriceInfo.setNftCode(dto.getNftCode());
        floorPriceInfo.setFp(dto.getFp());
        floorPriceInfo.setCreatedDate(LocalDateTime.now());
        floorPriceInfo.setModifiedDate(LocalDateTime.now());
        floorPriceInfoRepository.save(floorPriceInfo);
    }
}
