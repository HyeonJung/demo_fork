package framework.com.example.demo.domain.metakongs;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity(name = "metakongs")
public class metakongsfp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fp;


    @Builder
    public metakongsfp(String fp){
        this.fp = fp;
    }

    public void update(String fp){
        this.fp = fp;
    }
}
