package framework.com.example.demo.controller.coin;


import framework.com.example.demo.model.network.request.coin.SoldierfpApiRequestDto;
import framework.com.example.demo.model.network.response.coin.SoldierfpApiResponseDto;
import framework.com.example.demo.service.coin.SoldierfpApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SoldierfpApiController {
    @Autowired
    private SoldierfpApiService soldierfpApiService;


    @PostMapping("/api/soldierfp")
    public Long save(@RequestBody SoldierfpApiRequestDto requestDto) {
        return soldierfpApiService.save(requestDto);
    }

    @ResponseBody
    @PutMapping("/api/soldierfp/{fp}")
    public String update(@PathVariable  String fp) {
        soldierfpApiService.update(fp);
        return "";
    }

    @GetMapping("/api/soldierfp")
    public SoldierfpApiResponseDto findByAll(){
        return soldierfpApiService.findByAll();
    }
}