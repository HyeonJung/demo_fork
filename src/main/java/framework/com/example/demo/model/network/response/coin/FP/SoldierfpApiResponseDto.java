package framework.com.example.demo.model.network.response.coin.FP;

import framework.com.example.demo.domain.soldierfp.soldierfp;
import framework.com.example.demo.domain.sunmi.ssunmi;
import lombok.Getter;

@Getter
public class SoldierfpApiResponseDto {
    private Long id;

    private String fp;

    public SoldierfpApiResponseDto(soldierfp soldierfp){
        this.id=soldierfp.getId();
        this.fp=soldierfp.getFp();
    }

}
