package framework.com.example.demo.model.network.request;

import framework.com.example.demo.domain.metakongs.metakongsfp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MetaKongsApiRequestDto {
    private String fp;


    @Builder
    public MetaKongsApiRequestDto(String fp){
        this.fp=fp;
    }

    public metakongsfp toEntity(){
        return metakongsfp.builder()
                .fp(fp)
                .build();
    }
}
