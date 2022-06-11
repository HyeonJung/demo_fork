package framework.com.example.demo.service.coin.FP;

import framework.com.example.demo.domain.grilla.GrillaApiRepository;
import framework.com.example.demo.domain.grilla.grillafp;
import framework.com.example.demo.model.network.request.coin.FP.GrillaApiRequestDto;
import framework.com.example.demo.model.network.response.coin.FP.GrillaApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GrillafpApiService {
    
    private final GrillaApiRepository grillaApiRepository;

    @Transactional
    public Long save(GrillaApiRequestDto requestDto){
        return grillaApiRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void update(String fp){
        grillafp grillafp = grillaApiRepository.findAll().get(0);
        grillafp.update(fp);
    }

    public GrillaApiResponseDto findByAll(){

        List<grillafp> grillafps = grillaApiRepository.findAll();

        return new GrillaApiResponseDto(grillafps.get(0));
    }

}
