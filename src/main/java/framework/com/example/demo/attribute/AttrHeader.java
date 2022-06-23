package framework.com.example.demo.attribute;


import framework.com.example.demo.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Entity(name = "attr_header")
@AllArgsConstructor
@IdClass(HeaderPK.class)
public class AttrHeader extends BaseTimeEntity implements Serializable {
    @Id
    private String nftCode;

    @Id
    private String header;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

}
