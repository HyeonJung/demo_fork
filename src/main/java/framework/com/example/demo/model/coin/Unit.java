package framework.com.example.demo.model.coin;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Unit<coin>{
    private String name;

    private String price;

    private String percent;

    private String sunmiOnePrice;

    private String sunmiFP;

    private String SoldiersFP;

    private String metaKongsOnePrice;

    private String metakongsFP;

    private String grillaOnePrice;

    private String grillaFP;

    private List<Soldier> soldiers;


}
