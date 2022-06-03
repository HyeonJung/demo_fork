package framework.com.example.demo.model.network.response.coin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenMapngApiResponse {
    private String tokenId;

    private String address;

    private String getStatus;
}
