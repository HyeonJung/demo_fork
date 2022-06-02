package framework.com.example.demo.controller.coin;

import framework.com.example.demo.Interface.CoinItemInterface;
import framework.com.example.demo.model.coin.Unit;
import framework.com.example.demo.service.coin.CoinBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;

public class CoinController<Item> implements CoinItemInterface<Item> {
    @Autowired(required = false)
    protected CoinBaseService<Item> coinBaseService;

    @Override
    public Unit<Item> getCoin() throws IOException, URISyntaxException, ScriptException,  InterruptedException {
        return coinBaseService.getCoin();
    }
}
