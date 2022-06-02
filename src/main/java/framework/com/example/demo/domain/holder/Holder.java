package framework.com.example.demo.domain.holder;

import framework.com.example.demo.domain.BaseTimeEntity;
import framework.com.example.demo.model.entity.User;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Entity(name = "holder")
@AllArgsConstructor
@Data
public class Holder extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private Long amount;

    private Long amountHeld;

    private String chkYN;

    public Holder toEntity(){
        return Holder.builder()
                .id(id)
                .address(address)
                .amount(amount)
                .amountHeld(amountHeld)
                .chkYN(chkYN)
                .build();
    }
    public void update(String address){
        this.address = address;
    }
}
