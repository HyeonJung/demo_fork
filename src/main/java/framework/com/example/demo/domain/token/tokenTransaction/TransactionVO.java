package framework.com.example.demo.domain.token.tokenTransaction;

import framework.com.example.demo.domain.BaseTimeEntity;
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
@Entity(name = "tb_transaction")
@AllArgsConstructor
@Data
@IdClass(TransactionVO.class)
public class TransactionVO extends BaseTimeEntity implements Serializable {

    @Id
    private String nftCode;

    @Id
    private String date;

    private Long amount;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
