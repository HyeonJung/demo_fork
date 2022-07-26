package framework.com.example.demo.token;


import framework.com.example.demo.domain.BaseTimeEntity;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Entity(name = "token_info")
@AllArgsConstructor
@Data
@IdClass(TokenInfoPK.class)
public class TokenInfo extends BaseTimeEntity implements Serializable {
    private static final long serialVersionID = 1L;

    @Id
    private String tokenId;

    @Id
    private String nftCode;

    private String imageUrl;

    private String description;

    private String grade;

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;

}
