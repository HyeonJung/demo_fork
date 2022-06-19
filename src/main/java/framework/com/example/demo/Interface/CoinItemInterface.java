package framework.com.example.demo.Interface;

import framework.com.example.demo.model.coin.Unit;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;

public interface CoinItemInterface
{
    /**
     * 코인수집
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws ScriptException
     * @throws InterruptedException
     */
    Unit getCoin() throws IOException, URISyntaxException, ScriptException, InterruptedException;
}
