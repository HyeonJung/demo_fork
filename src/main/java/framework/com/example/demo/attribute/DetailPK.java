package framework.com.example.demo.attribute;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@EqualsAndHashCode
public class DetailPK implements Serializable {
    private static final long serialVersionID = 1L;

    private String nftCode;

    private String tokenId;

    private String header;

    private String detail;
}
