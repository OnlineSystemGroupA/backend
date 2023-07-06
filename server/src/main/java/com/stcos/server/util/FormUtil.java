package com.stcos.server.util;

import com.stcos.server.service.FormService;
import lombok.experimental.UtilityClass;
import org.flowable.task.api.Task;
import org.flowable.task.service.delegate.DelegateTask;

import java.util.List;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/5 16:58
 */

@UtilityClass
public class FormUtil {

    private static final List<String> FORM_LIST =
            List.of("ApplicationForm",
                    "ApplicationVerifyForm",
                    "ContractForm",
                    "DocumentReviewForm",
                    "QuotationForm",
                    "ReportVerifyForm",
                    "TestFunctionForm",
                    "TestPlanForm",
                    "TestPlanVerifyForm",
                    "TestProblemForm",
                    "TestRecordsForm",
                    "TestReportForm",
                    "TestWorkCheckForm");

    public void addReadPermission(String userId, DelegateTask task, FormService formService) {
        for (String formName : FORM_LIST) {
            Long formMetadataId = (Long) task.getVariable(formName);
            formService.addReadPermission(formMetadataId, userId);
        }
    }

}
