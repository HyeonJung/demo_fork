package framework.com.example.demo.token.dto;


import framework.com.example.demo.domain.BaseTimeEntity;
import lombok.Data;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class FloorPriceInfoDto {

    private String nftCode;

    private String fp;

}
