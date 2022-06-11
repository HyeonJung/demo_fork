package framework.com.example.demo.model.network.response.coin.FP;

import framework.com.example.demo.domain.grilla.grillafp;
import framework.com.example.demo.domain.metakongs.metakongsfp;
import lombok.Getter;

@Getter
public class GrillaApiResponseDto {
    private Long id;

    private String fp;

    public GrillaApiResponseDto(grillafp grillafp){
        this.id=grillafp.getId();
        this.fp=grillafp.getFp();
    }

}
