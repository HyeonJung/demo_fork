package framework.com.example.demo.controller;

import framework.com.example.demo.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameApiController {

    @Autowired
    private GameService gameService;
    @GetMapping("/api/randomDraw")
    public String randomDraw(@RequestParam String start, String end) throws InterruptedException {
        return gameService.randomDraw(start, end);
    }
}
