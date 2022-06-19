package framework.com.example.demo.token.dao;


import framework.com.example.demo.domain.BaseTimeEntity;
import framework.com.example.demo.token.TokenInfoPK;
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
@AllArgsConstructor
@IdClass(TokenInfoPK.class)
public class TokenInfoAndTokenMapping  {
    private Long rownum;

    private String tokenId;

    private String nftCode;

    private String address;

    private String level;

    private String getStatus;

    private String stakingStatus;


}
