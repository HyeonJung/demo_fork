package framework.com.example.demo.model.entity;

import framework.com.example.demo.Interface.CoinItemInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Soldier {

        private String name;

        private String qty;

        private double getQty;

        private double day_item;

        private double month_item;

        private double sunmi;
}
