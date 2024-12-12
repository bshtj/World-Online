package org.bshtj.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class JuHeTouristAttractionModel {

    private Result result;

    @Data
    private static class Result{
        private ArrayList<JuHeTouristAttractContent>list;
    }

    @Data
    private static class JuHeTouristAttractContent {
        private String content;
        private String name;
    }
}
