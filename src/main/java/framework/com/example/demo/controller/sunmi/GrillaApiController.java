package framework.com.example.demo.controller.sunmi;


import framework.com.example.demo.model.network.request.GrillaApiRequestDto;
import framework.com.example.demo.model.network.response.GrillaApiResponseDto;
import framework.com.example.demo.service.coin.GrillafpApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/g-rilla")
public class GrillaApiController {
    @Autowired
    private GrillafpApiService grillafpApiService;


    @PostMapping("/g-rilla")
    public Long save(@RequestBody GrillaApiRequestDto requestDto) {
        return grillafpApiService.save(requestDto);
    }

    @ResponseBody
    @PutMapping("/g-rilla/{fp}")
    public String update(@PathVariable  String fp) {
        grillafpApiService.update(fp);
        return "";
    }

    @GetMapping("/g-rilla")
    public GrillaApiResponseDto findByAll(){
        return grillafpApiService.findByAll();
    }
}
