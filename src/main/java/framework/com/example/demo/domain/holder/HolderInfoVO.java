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

    private Long amount; //늪 수량

    private Long amountHeld; //실버코인

    private String tsgAmountHeld; //골드코인

    private Long calcTssCoin; //전일 수량과 비교 한 값

    private Long calcAmount;

}
