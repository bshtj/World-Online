package org.bshtj.model;
import lombok.Data;

import java.util.ArrayList;

@Data
public class JuHeNewsModel {
    private Result result;

    @Data
    private static class Result {
        private ArrayList<JuHeNewsContent> list;
    }

    @Data
    private static class JuHeNewsContent {
        private String title;
        private String url;
    }
}
