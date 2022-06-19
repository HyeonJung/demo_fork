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
@Entity(name = "tb_tokenmapping")
@AllArgsConstructor
@Data
@IdClass(TokenInfoPK.class)
public class TokenMapping extends BaseTimeEntity implements Serializable {
    private static final long serialVersionID = 1L;

    @Id
    private String tokenId;

    @Id
    private String nftCode;

    private String address;

    private String getStatus;

    private String stakingStatus;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

}
