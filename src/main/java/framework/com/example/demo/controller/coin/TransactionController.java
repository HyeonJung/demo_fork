package framework.com.example.demo.controller.coin;

import framework.com.example.demo.controller.CurdController;
import framework.com.example.demo.domain.lass.LassDataVO;
import framework.com.example.demo.domain.lass.LassVO;
import framework.com.example.demo.domain.token.tokenTransaction.TransactionVO;
import framework.com.example.demo.model.network.request.coin.TransactionApiRequest;
import framework.com.example.demo.model.network.response.coin.TransactionApiResponse;
import framework.com.example.demo.service.coin.Token.TokenLogicService;
import framework.com.example.demo.service.coin.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController extends CurdController<TransactionApiRequest, TransactionApiResponse, TransactionVO> {
    @Autowired
    TransactionService transactionService;

    @Autowired
    private TokenLogicService tokenLogicService;

    @GetMapping("/gettransaction")
    public void GetTransaction(@RequestParam String nftCode, @RequestParam String endDate) throws Exception {
        if(endDate.isEmpty()){
            LocalDateTime ldg = LocalDateTime.now().minusDays(3);
            endDate = ldg.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        tokenLogicService.InsertTransactionAmount(nftCode, endDate);
    }

    @GetMapping("/readecttransaction")
    public ArrayList<TransactionVO> ReadTransaction(@RequestParam String nftCode) throws Exception {
        ArrayList<TransactionVO> result = tokenLogicService.readTransactionAmount(nftCode);
        return result;
    }

}
