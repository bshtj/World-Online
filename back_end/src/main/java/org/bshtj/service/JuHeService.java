package org.bshtj.service;


import lombok.extern.slf4j.Slf4j;
import org.bshtj.model.JuHeNewsModel;
import org.bshtj.model.JuHeTouristAttractionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class JuHeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${JuHe.NewsKey}")
    private String NewsKey;

    @Value("${JuHe.PlacesKey}")
    private String PlaceKey;

    public JuHeNewsModel getJuHeNewsData(String query) {

        String url = "http://apis.juhe.cn/fapigx/areanews/query?key=" + NewsKey +"&areaname="+query;
        return restTemplate.getForObject(url, JuHeNewsModel.class);
    }

    public JuHeTouristAttractionModel getJuHeTouristAttractionData(String query) {

        String url = "http://apis.juhe.cn/fapigx/scenic/query?key=" + PlaceKey +"&word=''"+"&city="+query;
        log.info("JuHeTouristAttractionModel:{}",url);
        return restTemplate.getForObject(url, JuHeTouristAttractionModel.class);
    }
}
