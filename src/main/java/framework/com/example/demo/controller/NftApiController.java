package framework.com.example.demo.controller;

import com.google.gson.Gson;
import com.klaytn.caver.Caver;
import com.klaytn.caver.transaction.TxPropertyBuilder;
import com.klaytn.caver.transaction.type.ValueTransfer;
import com.klaytn.caver.wallet.keyring.SingleKeyring;
import framework.com.example.demo.domain.lass.LassDataVO;
import framework.com.example.demo.domain.lass.ModalLassVO;
import framework.com.example.demo.domain.lass.reqModalLassVO;
import framework.com.example.demo.nft.dto.Attributes;
import framework.com.example.demo.nft.dto.IPFS;
import framework.com.example.demo.nft.service.BigDGNftServiceImpl;
import framework.com.example.demo.service.lass.LassLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

@Slf4j
@RestController
public class NftApiController {
    // You can directly input values for the variables below, or you can enter values in the caver-java-examples/.env file.
    private static String nodeApiUrl = ""; // e.g. "https://node-api.klaytnapi.com/v1/klaytn";
    private static String accessKeyId = ""; // e.g. "KASK1LVNO498YT6KJQFUPY8S";
    private static String secretAccessKey = ""; // e.g. "aP/reVYHXqjw3EtQrMuJP4A3/hOb69TjnBT3ePKG";
    private static String chainId = ""; // e.g. "1001" or "8217";
    private static String recipientAddress = ""; // e.g. "0xeb709d59954f4cdc6b6f3bfcd8d531887b7bd199"


    /////////////////////
    @Autowired
    private BigDGNftServiceImpl bigDGNftService;
    private String nftName = "BigDragon NFT";
    private String description = "BigDragon, Test My Cat So Cute!";
    private String hiddenImgUrl = "ipfs://QmdCi8DddkoKvdB5RDeMFBr2AqJgySAooeVKsZ2cepWqYR";

    private int totalNum = 30;
    @GetMapping("/api/nft/makeImage")
    public void makeImage(){
        bigDGNftService.makeNotEggImage(nftName, description, hiddenImgUrl, "/Users/daeyong/nft/sample/");
    }

    public void sendTransfer() throws Exception{
        Caver caver = new Caver(Caver.DEFAULT_URL);

        SingleKeyring keyring = (SingleKeyring) caver.wallet.newKeyring("0x4c04f8F23B91785aba7B75c32EbB32618DE25565","0xcd10e21136347a13d4ae7be309df5f02afef7ea422ad821ebffb95a3e954e315");
        caver.wallet.add(keyring);

// 자산 이동 트랜잭션 생성
        ValueTransfer valueTransfer = caver.transaction.valueTransfer.create(
                TxPropertyBuilder.valueTransfer()
                        .setFrom(keyring.getAddress())
                        .setTo("0x176ff0344de49c04be577a3512b6991507647f72")
                        .setValue(BigInteger.valueOf(1))
                        .setGas(BigInteger.valueOf(30000))
        );

// caver.wallet.sign으로 트랜잭션 서명
        caver.wallet.sign(keyring.getAddress(), valueTransfer);
        String rlpEncoded = valueTransfer.getRLPEncoding();
        System.out.println("RLP-encoded string: " + rlpEncoded);
    }

}
