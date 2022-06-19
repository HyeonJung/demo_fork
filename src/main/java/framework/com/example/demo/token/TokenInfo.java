package framework.com.example.demo.token;


import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Entity(name = "token_info")
@AllArgsConstructor
@Data
@IdClass(TokenInfoPK.class)
public class TokenInfo implements Serializable {
    private static final long serialVersionID = 1L;

    @Id
    private String tokenId;

    @Id
    private String nftCode;

    private String fp;

}
