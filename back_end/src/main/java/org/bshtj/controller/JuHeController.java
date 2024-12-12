package org.bshtj.controller;


import org.bshtj.model.JuHeNewsModel;
import org.bshtj.model.JuHeTouristAttractionModel;
import org.bshtj.service.JuHeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/JuHe")
@CrossOrigin(origins = "http://127.0.0.1:5173") // 允许特定来源
public class JuHeController {
    @Autowired
    private JuHeService juHeService;

    @GetMapping("/News")
    public JuHeNewsModel searchJuHeNews(@RequestParam String query) {
        return juHeService.getJuHeNewsData(query);
    }

    @GetMapping("/TouristAttraction")
    public JuHeTouristAttractionModel searchJuHeTouristAttraction(@RequestParam String query) {
        return juHeService.getJuHeTouristAttractionData(query);
    }

}
