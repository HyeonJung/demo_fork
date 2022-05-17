package framework.com.example.demo.service.coin;

import framework.com.example.demo.domain.grilla.GrillaApiRepository;
import framework.com.example.demo.domain.grilla.grillafp;
import framework.com.example.demo.domain.metakongs.MetakongsApiRepository;
import framework.com.example.demo.domain.metakongs.metakongsfp;
import framework.com.example.demo.model.network.request.GrillaApiRequestDto;
import framework.com.example.demo.model.network.request.MetaKongsApiRequestDto;
import framework.com.example.demo.model.network.response.GrillaApiResponseDto;
import framework.com.example.demo.model.network.response.MetaKongsApiResponseDto;
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
