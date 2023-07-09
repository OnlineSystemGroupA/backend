package com.stcos.server.entity.form;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * 软件文档评审表
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(chain = true)
public class DocumentReviewForm extends Form {
    private String softwareName;
    private String softwareVersion;
    private String clientCompany;
    private String reviewGroup;
    private String reviewer;
    private String finishDate;
    private List<ReviewCategory> reviewsOnExplanation;
    private List<ReviewCategory> reviewsOnDocuments;
    private String checker;

    @Data
    @Accessors(chain = true)
    public static class ReviewCategory {
        private String title;
        private List<ReviewItem> items;

        @Data
        @Accessors(chain = true)
        public static class ReviewItem {
            private String content;
            private String result;
            private String explanation;
        }
    }
}