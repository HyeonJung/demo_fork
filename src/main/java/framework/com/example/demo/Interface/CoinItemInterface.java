package framework.com.example.demo.Interface;

import framework.com.example.demo.model.coin.Unit;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;

public interface CoinItemInterface<coin>
{
    Unit<coin> getCoin() throws IOException, URISyntaxException, ScriptException, InterruptedException;
}
