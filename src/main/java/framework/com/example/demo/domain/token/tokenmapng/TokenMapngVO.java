package framework.com.example.demo.domain.token.tokenmapng;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TokenMapngVO {

    private String tokenId;

    private String nftCode;

    private String address;

    private String getStatus;

    private String rownum;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private String rTokenId;

    private String rAddress;

    private String rGetStatus;

    private String tmpMsg;

    private String level;

}
