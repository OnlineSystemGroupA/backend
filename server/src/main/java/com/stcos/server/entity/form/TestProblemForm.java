package com.stcos.server.entity.form;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.List;
import java.util.Map;

/*
          ______          __  ____             __    __               ______
         /_  __/__  _____/ /_/ __ \_________  / /_  / /__  ____ ___  / ____/___  _________ ___
          / / / _ \/ ___/ __/ /_/ / ___/ __ \/ __ \/ / _ \/ __ `__ \/ /_  / __ \/ ___/ __ `__ \
         / / /  __(__  ) /_/ ____/ /  / /_/ / /_/ / /  __/ / / / / / __/ / /_/ / /  / / / / / /
        /_/  \___/____/\__/_/   /_/   \____/_.___/_/\___/_/ /_/ /_/_/    \____/_/  /_/ /_/ /_/

 */

/**
 * 软件测试问题清单
 *
 * @author kura
 * @version 1.0
 * @since 2023/5/29 19:42
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TestProblemForm extends Form {
    private List<Problem> problemList;

    @Override
    public Map<String, String> toTemplateFormat() {
        return null;
    }

    @Data
    @Accessors(chain = true)
    public static class Problem {
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
