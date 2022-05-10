package framework.com.example.demo.Interface;

import framework.com.example.demo.model.entity.Unit;
import framework.com.example.demo.model.entity.sunmi;
import xyz.hexile.cloudflarescraper.ScraperException;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;

public interface CoinItemInterface<coin>
{
    Unit<coin> getCoin() throws IOException, URISyntaxException, ScriptException, ScraperException, InterruptedException;
}
