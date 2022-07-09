package framework.com.example.demo.controller.coin;

import framework.com.example.demo.snapshot.service.SnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SnapshotController {
    @Autowired
    private SnapshotService snapshotService;

    @GetMapping("/api/snapshot")
    public void snapshot() throws Exception {
        snapshotService.getSnapShot();
    }

}
