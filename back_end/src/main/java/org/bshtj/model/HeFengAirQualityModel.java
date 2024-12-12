package org.bshtj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)  // 忽略未知属性
public class HeFengAirQualityModel {
    private AirQualityContent now;
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)  // 忽略未知属性
    public static class AirQualityContent{
        private String pubTime;  // 发布时刻
        private String aqi;      // 空气质量指数
        private String level;    // 等级
        private String category;  // 分类
        private String primary;   // 主要成分
        private String pm10;      // PM10 颗粒物浓度
        private String pm2p5;     // PM2.5 颗粒物浓度
        private String no2;       // 二氧化氮浓度
        private String so2;       // 二氧化硫浓度
        private String co;        // 一氧化碳浓度
        private String o3;        // 臭氧浓度
    }
}
