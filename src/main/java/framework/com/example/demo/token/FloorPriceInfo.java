package framework.com.example.demo.token;


import framework.com.example.demo.domain.BaseTimeEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDateTime;

@Entity(name = "floorPriceInfo")
@Data
public class FloorPriceInfo extends BaseTimeEntity {

    @Id
    @Column(name = "nft_code")
    private String nftCode;

    @Column(name = "fp")
    private String fp;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
