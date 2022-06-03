package framework.com.example.demo.domain.token.tokenmapng;

import framework.com.example.demo.domain.BaseTimeEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Entity(name = "tb_tokenmapping")
@AllArgsConstructor
@Data
@IdClass(TokenMapng.class)
public class TokenMapng extends BaseTimeEntity implements Serializable {
    @Id
    private String tokenId;

    @Id
    private String nftCode;

    private String address;

    private String getStatus;


}
