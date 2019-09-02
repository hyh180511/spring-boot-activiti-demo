package com.dapeng.demo.common.handler.impl;

import com.dapeng.demo.common.handler.ActInstance;
import com.dapeng.demo.common.handler.ServiceFactory;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class InstanceHandler extends ServiceFactory implements ActInstance {

    protected static Logger logger = LoggerFactory.getLogger(InstanceHandler.class);

    @Override
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey);
        logger.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    @Override
    public Map<String, Object> startInstanceAndExecuteFirstTask(String processDefinitionKey, Map<String, Object> variables) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        String piId = pi.getProcessInstanceId();
        logger.info("流程实例ID:{}---流程定义ID:{}", piId, pi.getProcessDefinitionId());
        Task task = taskService.createTaskQuery()
                .processInstanceId(piId)
                .active()
                .singleResult();
        taskService.complete(task.getId(), variables);
        logger.info("第一个流程任务已执行成功taskId:{}", task.getId());
//        TaskEntityImpl activeTask = (TaskEntityImpl) taskService
//                .createTaskQuery()
//                .processInstanceId(piId)
//                .active().singleResult();
        Map<String, Object> map = new HashMap<>(16);
        Object activeTask = null;
        map.put("active", activeTask);
        map.put("finish", task);
        return map;
    }

    @Override
    public ProcessInstance startProcessInstanceById(String processDefinitionId) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionId);
        logger.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    @Override
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> variables) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        logger.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    @Override
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey,
                                                     Map<String, Object> variables) throws RuntimeException, Exception {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
        logger.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }


    @Override
    public void suspendProcessInstanceById(String processInstanceId) throws RuntimeException, Exception {
        runtimeService.suspendProcessInstanceById(processInstanceId);
    }

    @Override
    public void deleteProcessInstance(String processInstanceId, String deleteReason) throws RuntimeException, Exception {

        runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
    }

    @Override
    public void setAuthenticatedUserId(String authenticatedUserId) {
        identityService.setAuthenticatedUserId(authenticatedUserId);
    }

    @Override
    public ProcessInstance startInstanceAndExecuteFirstTask(String processDefinitionKey,
                                                            Map<String, Object> variables,
                                                            Map<String, Object> actorIds) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        logger.info("启动流程实例成功，流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        Task task = taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).active().singleResult();
        taskService.complete(task.getId(), actorIds);
        logger.info("第一个流程任务已执行成功taskId:{}", task.getId());
        return pi;
    }

    @Override
    public void activateProcessInstanceById(String processInstanceId) {
        runtimeService.activateProcessInstanceById(processInstanceId);
    }

}
