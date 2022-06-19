package framework.com.example.demo.token.dto;


import framework.com.example.demo.domain.BaseTimeEntity;
import framework.com.example.demo.token.TokenInfoPK;
import jdk.vm.ci.meta.Local;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TokenInfoDto {

    private String tokenId;

    private String nftCode;

    private String imageUrl;

    private String description;

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;

}
