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
public class HeaderPK implements Serializable {
    private static final long serialVersionID = 1L;

    private String nftCode;

    private String header;
}
