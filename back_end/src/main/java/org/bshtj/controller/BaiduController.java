package org.bshtj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.bshtj.model.BaiduModel;
import org.bshtj.service.BaiduService;

@RestController
@RequestMapping("/api/Baidu")
@CrossOrigin(origins = "http://127.0.0.1:5173") // 允许特定来源
public class BaiduController {

    @Autowired
    private BaiduService baiduService;

    @GetMapping
    public BaiduModel search(@RequestParam String query) {
        return baiduService.getBaiduData(query);
    }
}
