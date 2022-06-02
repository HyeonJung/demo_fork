package framework.com.example.demo.model.network.response.coin;

import framework.com.example.demo.domain.sunmi.ssunmi;
import lombok.Getter;

@Getter
public class SunmiApiResponseDto {
    private Long id;

    private String fp;

    public SunmiApiResponseDto(ssunmi ssunmi){
        this.id=ssunmi.getId();
        this.fp=ssunmi.getFp();
    }

}
