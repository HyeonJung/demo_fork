package framework.com.example.demo.domain.token;

import framework.com.example.demo.domain.BaseTimeEntity;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Entity(name = "token")
@AllArgsConstructor
@Data
public class Token extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tokenId;

    private String nftCode;

}
