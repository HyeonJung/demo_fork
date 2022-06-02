package framework.com.example.demo.domain.token;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class tokenVO {

    private String tokenId;

    private String nftCode;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private String rTokenId;
}
