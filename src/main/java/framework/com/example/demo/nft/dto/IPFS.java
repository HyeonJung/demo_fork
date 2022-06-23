package framework.com.example.demo.nft.dto;

import lombok.Data;

@Data
public class IPFS {
    private String name;

    private String description;

    private String image;

    private Attributes attributes;
}
