package framework.com.example.demo.snapshot;

import framework.com.example.demo.domain.BaseTimeEntity;
import framework.com.example.demo.token.TokenInfoPK;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@Data
@IdClass(TokenSnapshotPK.class)
@Entity(name = "snapshot")
public class Snapshot extends BaseTimeEntity implements Serializable {
    @Id
    private String tokenId;

    @Id
    private String nftCode;

    private String address;

    private String date;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
