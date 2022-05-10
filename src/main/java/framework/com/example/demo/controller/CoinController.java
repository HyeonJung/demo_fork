package framework.com.example.demo.controller;

import framework.com.example.demo.Interface.CoinItemInterface;
import framework.com.example.demo.Interface.CrudInterface;
import framework.com.example.demo.model.entity.Unit;
import framework.com.example.demo.service.BaseService;
import framework.com.example.demo.service.CoinBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.hexile.cloudflarescraper.ScraperException;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;

public class CoinController<Item> implements CoinItemInterface<Item> {
    @Autowired(required = false)
    protected CoinBaseService<Item> coinBaseService;

    @Override
    public Unit<Item> getCoin() throws IOException, URISyntaxException, ScriptException, ScraperException, InterruptedException {
        return coinBaseService.getCoin();
    }
}
