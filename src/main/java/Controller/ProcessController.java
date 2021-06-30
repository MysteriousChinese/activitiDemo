package Controller;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.deploy.DeploymentManager;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;



    @RequestMapping(value = "deploy",method = RequestMethod.GET)
    public String Repository(){
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("activiti.bpmn")
                .addClasspathResource("activiti.png")
                .category("审批category")
                .name("审批name").deploy();

        String deployId = deployment.getId();
        System.out.println(String.format("部署ID: %s",deployId));
        System.out.println(String.format("部署name: %s",deployment.getName()));

        //创建流程定义查询对象
        //获取指定部署ID对应的流程定义查询对象
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().deploymentId(deployId);
        //获取查询结果
        ProcessDefinition processDefinitionQueryResult = processDefinitionQuery.singleResult();
        String definitionId = processDefinitionQueryResult.getId();

        //返回流程定义id，用来启动流程
        return definitionId;
    }


    @RequestMapping(value = "start",method = RequestMethod.GET)
    public String Start(@RequestParam("id") String definitionId){

        // 根据流程定义ID启动流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(definitionId);
        String processInstanceId = processInstance.getId();
        System.out.println(String.format("流程实例id: %s",processInstanceId));
        //返回流程实例id，用于查询指定的流程任务
        return processInstanceId;
    }


    @RequestMapping(value = "completetask",method = RequestMethod.GET)
    public void CompleteTask(@RequestParam("id") String pId,String employeeId){

        //创建任务查询对象
        TaskQuery taskQuery = taskService.createTaskQuery()
                .taskAssignee(employeeId) //指定执行人
                .processInstanceId(pId); //查询指定流程实例id对应的任务
        Task task = taskQuery.singleResult();
        String taskId = task.getId();
        System.out.println(String.format("任务id: %s",taskId));
        taskService.complete(taskId);
    }


}
