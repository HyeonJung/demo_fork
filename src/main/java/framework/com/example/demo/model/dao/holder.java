package framework.com.example.demo.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class holder {
    private String address;

    private Long amountHeld;

    private Long id;

    private String nftCode;
}
