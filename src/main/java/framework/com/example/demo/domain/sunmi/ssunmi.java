package framework.com.example.demo.domain.sunmi;

import framework.com.example.demo.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class ssunmi extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fp;


    @Builder
    public ssunmi(String fp){
        this.fp = fp;
    }

    public void update(String fp){
        this.fp = fp;
    }
}
