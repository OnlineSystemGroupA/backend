package com.stcos.server;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/23 17:01
 */

public class FlowableTest {


    ProcessEngine processEngine = null;

    @Before
    public void before() {
        ProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUsername("flowable_demo");
        configuration.setJdbcPassword("");
        // 为使其自动创建表结构，添加属性：nullCatalogMeansCurrent=true
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/flowable_demo?serverTimezone=UTC&nullCatalogMeansCurrent=true");
        // 若数据库中的表不存在就新建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        processEngine = configuration.buildProcessEngine();
    }


    @Test
    public void test01() {
        // 获取 RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
//                .addClasspathResource("tmp/flowable/holiday-request.bpmn20.xml")
                .addClasspathResource("processes/software-testing-commission-v1.bpmn20.xml") // 关联需要部署的流程文件
                .name("workflow")
                .key("")
                .deploy();
        System.out.println("deployment.getId() = " + deployment.getId());
        System.out.println("deployment.getName() = " + deployment.getName());
    }

    @Test
    public void testDeploymentQuery() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                .deploymentId("1")
                .singleResult();
        System.out.println("processDefinition.getDeploymentId() = " + definition.getDeploymentId());
        System.out.println("processDefinition.getName() = " + definition.getName());
        System.out.println("processDefinition.getDescription() = " + definition.getDescription());
        System.out.println("processDefinition.getId() = " + definition.getId());

    }

    @Test
    public void testDeleteDeployment() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 若部署流程启动则无法删除
//        repositoryService.deleteDeployment("1");
        // 开启级联删除，若流程启动则将相关的任务一并删除
        repositoryService.deleteDeployment("2501", true);
    }

    @Test
    public void testRunProcess() {
        Map<String, Object> variable = new HashMap<>();
//        variable.put("employee", "Zhang san");
//        variable.put("nrOfHolidays", 3);
//        variable.put("description", ".....");
        variable.put("text", "委托测试");
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("workflow", variable);
        System.out.println("processInstance.getProcessDefinitionId() = " + processInstance.getProcessDefinitionId());

//        processInstance.get

    }

}
