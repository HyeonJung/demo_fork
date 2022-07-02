package framework.com.example.demo.nft.service;

import com.google.gson.Gson;
import framework.com.example.demo.nft.dto.Attributes;
import framework.com.example.demo.nft.dto.IPFS;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class BigDGNftServiceImpl implements NftService{

    @Override
    public void makeNotEggImage(String nftName, String description, String imgaeUrl, String outputPath) {
        Gson gson =new Gson();

        for(int i=0; i<30; i++){
            IPFS ipfs = new IPFS();
            ipfs.setImage(imgaeUrl);
            ipfs.setDescription(description);
            ipfs.setName(nftName + " #" + (i + 1));
            Attributes attributes = new Attributes();
            attributes.setTrait_type("Unknown");
            attributes.setValue("Unknown");
            ipfs.setAttributes(attributes);

            String sample = gson.toJson(ipfs);
            System.out.println(sample);

            try {
                File file = new File(outputPath + String.valueOf(i) + ".json");
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
