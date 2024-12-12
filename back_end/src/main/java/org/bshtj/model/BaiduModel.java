package org.bshtj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaiduModel {
    @JsonProperty("abstract") // 映射 JSON 中的 "abstract" 字段
    private String abstractText; // 自定义字段名
    private String title;
    private String url;
}
