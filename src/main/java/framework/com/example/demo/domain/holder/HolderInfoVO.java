package framework.com.example.demo.domain.holder;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HolderInfoVO {

    private String rownum;

    private String address;

    private String nftCode;

    private String amount;

    private String amountHeld;

    private String tsgAmountHeld;

}
