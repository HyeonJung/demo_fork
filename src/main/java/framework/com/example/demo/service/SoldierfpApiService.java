package framework.com.example.demo.service;

import framework.com.example.demo.domain.soldierfp.SoldierFPApiRepository;
import framework.com.example.demo.domain.soldierfp.soldierfp;
import framework.com.example.demo.domain.sunmi.SunmiApiRepository;
import framework.com.example.demo.domain.sunmi.ssunmi;
import framework.com.example.demo.model.network.request.SoldierfpApiRequestDto;
import framework.com.example.demo.model.network.request.SunmiApiRequestDto;
import framework.com.example.demo.model.network.response.SoldierfpApiResponseDto;
import framework.com.example.demo.model.network.response.SunmiApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SoldierfpApiService {
    
    private final SoldierFPApiRepository soldierFPApiRepository;

    @Transactional
    public Long save(SoldierfpApiRequestDto requestDto){
        return soldierFPApiRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void update(String fp){
        soldierfp soldierfp = soldierFPApiRepository.findAll().get(0);
        soldierfp.update(fp);
    }

    public SoldierfpApiResponseDto findByAll(){

        List<soldierfp> soldierfp = soldierFPApiRepository.findAll();

        return new SoldierfpApiResponseDto(soldierfp.get(0));
    }

}
