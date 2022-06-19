package framework.com.example.demo.controller.coin.fp;

import framework.com.example.demo.token.dto.FloorPriceInfoDto;
import framework.com.example.demo.token.service.FloorPriceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fp")
public class FloorFPController {
    @Autowired
    private FloorPriceInfoService floorPriceInfoService;

    @PostMapping("/save")
    public void save(@RequestBody FloorPriceInfoDto requestDto) {
        floorPriceInfoService.setFP(requestDto);
    }

}
