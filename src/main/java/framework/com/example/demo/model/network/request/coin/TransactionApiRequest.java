package framework.com.example.demo.model.network.request.coin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionApiRequest {
    private String nftCode;

    private String date;

    private Long amount;

}

