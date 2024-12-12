package org.bshtj.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)  // 忽略未知属性
public class HeFengDailyWeatherModel {
    private ArrayList<DailyWeatherContent>daily;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)  // 忽略未知属性
    public static class DailyWeatherContent{
        private String fxDate;       // 预报日期
        private String tempMax;       // 最高温度
        private String tempMin;       // 最低温度
        private String textDay;       // 白天气象描述
        private String textNight;     // 夜间气象描述
        private String windDirDay;    // 白天风向
        private String windSpeedDay;  // 白天风速
        private String humidity;       // 湿度
        private String pressure;       // 气压
        private String uvIndex;        // 紫外线指数
    }
}
