package org.bshtj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.bshtj.model.BaiduModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BaiduService {

    @Autowired
    private RestTemplate restTemplate;

    public BaiduModel getBaiduData(String query) {

        String url = "http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?scope=103&format=json&appid=379020&bk_length=600&bk_key=" + query;
        return restTemplate.getForObject(url, BaiduModel.class);
    }
}
