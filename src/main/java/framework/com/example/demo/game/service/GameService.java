package framework.com.example.demo.game.service;

import org.springframework.stereotype.Service;

@Service
public class GameService {
    public String randomDraw(String start, String end) throws InterruptedException {
        Thread.sleep(2000);
        int result = (int) (Math.random() * (Integer.valueOf(end)- Integer.valueOf(start) + 1)) + Integer.valueOf(start);
        return String.valueOf(result);
    }
}
