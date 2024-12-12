package org.bshtj.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HeFengLocationModel {

    private ArrayList<HeFengLocationContent> location;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class HeFengLocationContent{
        private String id;
        private String name;
        private String lat;
        private String lon;
    }

}
