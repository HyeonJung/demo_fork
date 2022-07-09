package framework.com.example.demo.snapshot;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TokenSnapshotPK implements Serializable {
    private static final long serialVersionID = 1L;

    private String tokenId;

    private String nftCode;
}
