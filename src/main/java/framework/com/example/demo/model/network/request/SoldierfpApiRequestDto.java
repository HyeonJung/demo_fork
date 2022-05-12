package framework.com.example.demo.model.network.request;

import framework.com.example.demo.domain.soldierfp.soldierfp;
import framework.com.example.demo.domain.sunmi.ssunmi;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SoldierfpApiRequestDto {
    private String fp;


    @Builder
    public SoldierfpApiRequestDto(String fp){
        this.fp=fp;
    }

    public soldierfp toEntity(){
        return soldierfp.builder()
                .fp(fp)
                .build();
    }
}
