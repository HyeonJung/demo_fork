package framework.com.example.demo.model.network.request.coin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HolderApiRequest {
    private Long id;

    private String address;

    private Long amount;

    private Long amountHeld;

    private String chkYN;
}
