package framework.com.example.demo.model.network.request.coin.FP;

import framework.com.example.demo.domain.grilla.grillafp;
import framework.com.example.demo.domain.metakongs.metakongsfp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GrillaApiRequestDto {
    private String fp;


    @Builder
    public GrillaApiRequestDto(String fp){
        this.fp=fp;
    }

    public grillafp toEntity(){
        return grillafp.builder()
                .fp(fp)
                .build();
    }
}
