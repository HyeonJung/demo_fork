package framework.com.example.demo.service;

import framework.com.example.demo.domain.sunmi.SunmiApiRepository;
import framework.com.example.demo.domain.sunmi.ssunmi;
import framework.com.example.demo.model.network.request.SunmiApiRequestDto;
import framework.com.example.demo.model.network.response.SunmiApiResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SunmiApiService {
    
    private final SunmiApiRepository sunmiApiRepository;

    @Transactional
    public Long save(SunmiApiRequestDto requestDto){
        return sunmiApiRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void update(String fp){
        ssunmi ssunmi = sunmiApiRepository.findAll().get(0);
        ssunmi.update(fp);
    }

    public SunmiApiResponseDto findByAll(){

        List<ssunmi> ssunmi = sunmiApiRepository.findAll();

        return new SunmiApiResponseDto(ssunmi.get(0));
    }

}
