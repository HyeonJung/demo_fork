package framework.com.example.demo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import framework.com.example.demo.wallet.service.WalletService;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/wallet")
public class WalletApiController {
    @Autowired
    private WalletService walletService;
    @RequestMapping("/getContract")
    public String getContract(@RequestParam String address) throws Exception {

        walletService.getContract(address);
        return "";
    }

    @RequestMapping("/getMtdzFloorFP")
    public String getMtdzFloorFP() throws Exception {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("fp", walletService.getFloorFP("https://opensea.io/collection/mtdz-1"));
        Gson gson = new Gson();
        return gson.toJson(jsonObject);
    }


}
