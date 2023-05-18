package com.stcos.server.config.workflow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/18 11:46
 */

@Configuration
public class TaskConfig {

    @Bean
    public Map<String, List<String>> taskRequiredVarMap() {
        return new HashMap<>() {{
           put("填写委托", List.of("ApplicationForm"));
           // TODO 定义完成每个任务所需要的资源
//           put("分配工作人员", List.of("Asdfjkhasdafas"));
        }};
    }

}
