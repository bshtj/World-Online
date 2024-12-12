package org.bshtj.controller;

import org.bshtj.model.HeFengAirQualityModel;
import org.bshtj.model.HeFengDailyWeatherModel;
import org.bshtj.model.HeFengLocationModel;
import org.bshtj.model.HeFengWeatherModel;
import org.bshtj.service.HeFengService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/HeFeng")
@CrossOrigin(origins = "http://127.0.0.1:5173") // 允许特定来源
public class HeFengController {

    @Autowired
    private HeFengService heFengService;

    @GetMapping("/CityId")
    public HeFengLocationModel SearchCityId(@RequestParam String query) {
        return heFengService.getHeFengCityId(query);
    }

    @GetMapping("/CityWeather")
    public HeFengWeatherModel SearchCityWeather(@RequestParam String id) {
        return heFengService.getHeFengCityWeather(id);
    }

    @GetMapping("/CityAirQuality")
    public HeFengAirQualityModel SearchCityAirQuality(@RequestParam String id) {
        return heFengService.getHeFengCityAirQuality(id);
    }

    @GetMapping("/CityDailyWeather")
    public HeFengDailyWeatherModel SearchCityDailyWeather(@RequestParam String id) {
        return heFengService.getHeFengDailyWeather(id);
    }


}
