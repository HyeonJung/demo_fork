package framework.com.example.demo.token;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class TokenInfoPK implements Serializable {
    private static final long serialVersionID = 1L;

    private String tokenId;

    private String nftCode;
}
