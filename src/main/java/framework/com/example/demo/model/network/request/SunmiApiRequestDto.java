package framework.com.example.demo.model.network.request;

import framework.com.example.demo.domain.sunmi.ssunmi;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SunmiApiRequestDto {
    private String fp;


    @Builder
    public SunmiApiRequestDto(String fp){
        this.fp=fp;
    }

    public ssunmi toEntity(){
        return ssunmi.builder()
                .fp(fp)
                .build();
    }
}
