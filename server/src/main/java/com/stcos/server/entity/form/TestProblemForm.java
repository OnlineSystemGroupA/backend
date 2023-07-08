package com.stcos.server.entity.form;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/29 19:42
 */

@Data
@Accessors(fluent = true)
public class TestProblemForm extends Form {
    private List<Problem> problemList;

    @Override
    public Map<String, String> toTemplateFormat() {
        return null;
    }

    @Data
    @Accessors(fluent = true)
    public static class Problem {
        private String index;
        private String description;
        private String relatedRequirementItem;
        private String initialCondition;
        private String operationRoute;
        private String relatedCase;
        private String discoveredDate;
        private String personInCharge;
        private String editSuggestion;
    }
}
