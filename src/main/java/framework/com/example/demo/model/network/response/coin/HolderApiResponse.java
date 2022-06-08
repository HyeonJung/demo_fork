package framework.com.example.demo.model.network.response.coin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HolderApiResponse {
    private Long id;

    private String address;

    private Long amount;

    private Long amountHeld;

    private String chkYN;
}
