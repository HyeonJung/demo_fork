package framework.com.example.demo.service.coin.FP;

import framework.com.example.demo.domain.soldierfp.SoldierFPApiRepository;
import framework.com.example.demo.domain.soldierfp.soldierfp;
import framework.com.example.demo.model.network.request.coin.FP.SoldierfpApiRequestDto;
import framework.com.example.demo.model.network.response.coin.FP.SoldierfpApiResponseDto;
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
