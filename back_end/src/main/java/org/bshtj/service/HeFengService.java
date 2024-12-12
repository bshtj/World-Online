package org.bshtj.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.bshtj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.GZIPInputStream;

@Slf4j
@Service
public class HeFengService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${HeFeng.Key}")
    private String key;

    public HeFengLocationModel getHeFengCityId(String query) {
        String url = "https://geoapi.qweather.com/v2/city/lookup?location=" + query + "&key=" + key;
        return makeApiCall(url, HeFengLocationModel.class);
    }

    public HeFengWeatherModel getHeFengCityWeather(String id) {
        String url = "https://devapi.qweather.com/v7/weather/now?location=" + id + "&key=" + key;
        return makeApiCall(url, HeFengWeatherModel.class);
    }

    public HeFengAirQualityModel getHeFengCityAirQuality(String id) {
        String url = "https://devapi.qweather.com/v7/air/now?location=" + id + "&key=" + key;
        return makeApiCall(url, HeFengAirQualityModel.class);
    }

    public HeFengDailyWeatherModel getHeFengDailyWeather(String id) {
        String url = "https://devapi.qweather.com/v7/weather/3d?location=" + id + "&key=" + key;
        log.info("HeFengDailyWeather:{}",url);
        return makeApiCall(url, HeFengDailyWeatherModel.class);
    }


    private <T> T makeApiCall(String url, Class<T> responseType) {
        try {
            // 设置请求头，接受 Gzip 编码的响应
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept-Encoding", "gzip");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // 发送请求并获取响应
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
            byte[] responseBody = responseEntity.getBody();

            if (responseBody != null) {
                // 解压 Gzip
                try (GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(responseBody));
                     Reader reader = new InputStreamReader(gis)) {

                    // 读取解压后的内容
                    StringBuilder sb = new StringBuilder();
                    char[] buffer = new char[1024];
                    int len;
                    while ((len = reader.read(buffer)) != -1) {
                        sb.append(buffer, 0, len);
                    }
                    String jsonResponse = sb.toString();
                    log.debug("API Response: {}", jsonResponse); // 打印原始响应

                    // 将响应体转换为指定类型的对象
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(jsonResponse, responseType);
                }
            }
        } catch (RestClientException e) {
            log.error("Error during API call: {}", e.getMessage());
        } catch (IOException e) {
            log.error("Error during Gzip decompression or JSON parsing: {}", e.getMessage());
        }
        return null; // 或者根据需要返回一个默认值
    }
}




