package com.stcos.server.model.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/*
    ____                                        __  ____            _              ______
   / __ \____  _______  ______ ___  ___  ____  / /_/ __ \___ _   __(_)__ _      __/ ____/___  _________ ___
  / / / / __ \/ ___/ / / / __ `__ \/ _ \/ __ \/ __/ /_/ / _ \ | / / / _ \ | /| / / /_  / __ \/ ___/ __ `__ \
 / /_/ / /_/ / /__/ /_/ / / / / / /  __/ / / / /_/ _, _/  __/ |/ / /  __/ |/ |/ / __/ / /_/ / /  / / / / / /
/_____/\____/\___/\__,_/_/ /_/ /_/\___/_/ /_/\__/_/ |_|\___/|___/_/\___/|__/|__/_/    \____/_/  /_/ /_/ /_/

 */

/**
 * 软件文档评审表
 *
 * @author AmadeusZQK
 * @version 1.0
 * @since 2023/5/15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
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