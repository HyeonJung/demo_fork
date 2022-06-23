package framework.com.example.demo.controller;

import com.google.gson.Gson;
import framework.com.example.demo.domain.lass.LassDataVO;
import framework.com.example.demo.domain.lass.ModalLassVO;
import framework.com.example.demo.domain.lass.reqModalLassVO;
import framework.com.example.demo.nft.dto.Attributes;
import framework.com.example.demo.nft.dto.IPFS;
import framework.com.example.demo.service.lass.LassLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
@RestController
public class NftApiController {
    private String nftName = "BigDragon NFT";
    private String description = "BigDragon, Test My Cat So Cute!";
    private String hiddenImgUrl = "ipfs://QmdCi8DddkoKvdB5RDeMFBr2AqJgySAooeVKsZ2cepWqYR";
    private String hiddenImgUrl2 = "ipfs:///QmeiwnjrhDjcQMK3XxDZpVmEAmLmnVL8ocDBiMoU4f9uCq";

    private int totalNum = 30;
    @GetMapping("/api/nft/makeImage")
    public void makeImage(){
        Gson gson =new Gson();

        for(int i=0; i<30; i++){
            IPFS ipfs = new IPFS();
            ipfs.setImage(hiddenImgUrl);
            ipfs.setDescription(description);
            ipfs.setName(nftName + " #" + (i + 1));
            Attributes attributes = new Attributes();
            attributes.setTrait_type("Unknown");
            attributes.setValue("Unknown");
            ipfs.setAttributes(attributes);

            String sample = gson.toJson(ipfs);
            System.out.println(sample);

            try {
                File file = new File("/Users/daeyong/nft/sample/" + String.valueOf(i) + ".json");
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fw);
                writer.write(sample);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
