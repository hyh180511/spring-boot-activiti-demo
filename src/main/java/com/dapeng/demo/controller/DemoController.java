package com.dapeng.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "demoController", description = "activities demo 测试用例")
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/firstDemo", method = RequestMethod.GET)
    public void firstDemo() {

        //根据bpmn文件部署流程
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/bpmn/model.bpmn").deploy();
        //获取流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        //启动流程定义，返回流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
        String processId = pi.getId();
        System.out.println("流程创建成功，当前流程实例ID：" + processId);

        Task task = null;
        while ((task = taskService.createTaskQuery().processInstanceId(processId).singleResult()) != null) {
            System.out.println("任务名：" + task.getName() + "; " + "任务id: " + task.getId());
            taskService.complete(task.getId());
        }
        System.out.println("task为null，任务执行完毕：" + task);
    }


    @ResponseBody
    @GetMapping("/createDeployment")
    @ApiOperation(value = "创建部署流程")
    public String createDeployment() {
        //根据bpmn文件部署流程
        Deployment deployment = repositoryService.createDeployment().name("activities_demo").addClasspathResource("bpmn/model.bpmn").deploy();
        System.out.println("部署id " + deployment.getId());
        System.out.println("部署名 " + deployment.getName());
        return "部署id " + deployment.getId() + "; " + "部署名 " + deployment.getName();
    }

    @ResponseBody
    @PostMapping("/createProcess")
    @ApiOperation(value = "创建流程")
    @ApiImplicitParam(paramType = "query", name = "deployId", value = "部署流程id", required = true, dataType = "String")
    public String createProcess(@RequestParam(value = "deployId") String deployId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();

        //启动流程定义，返回流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
        String processId = pi.getId();
        System.out.println("流程创建成功，当前流程实例ID：" + processId);
        return "流程创建成功，当前流程实例ID：" + processId;
    }

    @ResponseBody
    @PostMapping("/createTaskExecute")
    @ApiOperation(value = "查看任务执行流程")
    @ApiImplicitParam(paramType = "query", name = "processId", value = "流程id", required = true, dataType = "String")
    public String createTaskExecute(@RequestParam(value = "processId") String processId) {
        Task task = null;
        StringBuilder builder = new StringBuilder();
        while ((task = taskService.createTaskQuery().processInstanceId(processId).singleResult()) != null) {
            System.out.println(" 任务名：" + task.getName() + "; " + "任务id: " + task.getId());
            builder.append(" 任务名：" + task.getName() + "; " + "任务id: " + task.getId());
            taskService.complete(task.getId());
        }
        System.out.println(" task为null，任务执行完毕;");
        builder.append(" task为null，任务执行完毕;");
        return builder.toString();

    }


    @ResponseBody
    @PostMapping("/create")
    public void create() {


    }
}
