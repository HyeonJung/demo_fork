package framework.com.example.demo.service.coin.FP;

import framework.com.example.demo.domain.metakongs.MetakongsApiRepository;
import framework.com.example.demo.domain.metakongs.metakongsfp;
import framework.com.example.demo.model.network.request.coin.FP.MetaKongsApiRequestDto;
import framework.com.example.demo.model.network.response.coin.FP.MetaKongsApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MetaKongfpApiService {
    
    private final MetakongsApiRepository metakongsApiRepository;

    @Transactional
    public Long save(MetaKongsApiRequestDto requestDto){
        return metakongsApiRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void update(String fp){
        metakongsfp metakongsfp = metakongsApiRepository.findAll().get(0);
        metakongsfp.update(fp);
    }

    public MetaKongsApiResponseDto findByAll(){

        List<metakongsfp> metakongsfp = metakongsApiRepository.findAll();

        return new MetaKongsApiResponseDto(metakongsfp.get(0));
    }

}
