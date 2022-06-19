package framework.com.example.demo.controller;

import framework.com.example.demo.domain.lass.LassDataVO;
import framework.com.example.demo.domain.lass.ModalLassVO;
import framework.com.example.demo.domain.lass.reqModalLassVO;
import framework.com.example.demo.service.lass.LassLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LassApiController {

    @PostMapping("/api/Lass/getHouseInfo")
    public ModalLassVO modalLass(@RequestBody reqModalLassVO body){
        String link = body.getLink();
        ModalLassVO modalLassVO = ModalLassVO.builder()
                .locName("인천")
                .build();

        return modalLassVO;
    }

    @GetMapping("/api/Lass/getLassList")
    public LassDataVO Test() throws Exception {
        LassLogicService lassLogicService = new LassLogicService();

        LassDataVO result = lassLogicService.GetLassList();
        result = lassLogicService.GetLassList();
        return result;
    }
}
