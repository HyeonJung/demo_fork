package framework.com.example.demo.model.entity;

import framework.com.example.demo.Interface.CoinItemInterface;
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

    private List<Soldier> soldiers;


}
