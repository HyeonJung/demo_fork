package framework.com.example.demo.model.network.response;

import framework.com.example.demo.domain.metakongs.metakongsfp;
import framework.com.example.demo.domain.sunmi.ssunmi;
import lombok.Getter;

@Getter
public class MetaKongsApiResponseDto {
    private Long id;

    private String fp;

    public MetaKongsApiResponseDto(metakongsfp metakongsfp){
        this.id=metakongsfp.getId();
        this.fp=metakongsfp.getFp();
    }

}
