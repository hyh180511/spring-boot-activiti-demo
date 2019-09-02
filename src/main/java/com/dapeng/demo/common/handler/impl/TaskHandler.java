package com.dapeng.demo.common.handler.impl;

import com.dapeng.demo.common.handler.ActTask;
import com.dapeng.demo.common.handler.ServiceFactory;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.task.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class TaskHandler extends ServiceFactory implements ActTask {

    protected static Logger logger = LoggerFactory.getLogger(TaskHandler.class);

    @Override
    public void claim(String taskId, String userId) throws RuntimeException, Exception {

        taskService.claim(taskId, userId);
    }

    @Override
    public void unclaim(String taskId) throws RuntimeException, Exception {

        taskService.unclaim(taskId);
    }

    @Override
    public void complete(String taskId) throws RuntimeException, Exception {

        this.complete(taskId, null);

        logger.info("-----------任务ID：{},已完成-----------", taskId);
    }

    @Override
    public void complete(String taskId, Map<String, Object> variables) throws RuntimeException, Exception {

        taskService.complete(taskId, variables);
    }

    @Override
    public void delegate(String taskId, String userId) throws ActivitiObjectNotFoundException {

        taskService.delegateTask(taskId, userId);
    }


    @Override
    public void resolveTask(String taskId) throws ActivitiObjectNotFoundException {
        taskService.resolveTask(taskId);
    }


    @Override
    public void setAssignee(String taskId, String userId) throws RuntimeException, Exception {

        taskService.setAssignee(taskId, userId);
    }

    @Override
    public void addCandidateUser(String taskId, String userId) throws RuntimeException, Exception {

        taskService.addCandidateUser(taskId, userId);
    }

    @Override
    public Comment addComment(String taskId, String processInstanceId, String message)
            throws RuntimeException, Exception {

        return taskService.addComment(taskId, processInstanceId, message);
    }

    @Override
    public List<Comment> getTaskComments(String taskId) throws RuntimeException, Exception {

        return taskService.getTaskComments(taskId);
    }

    @Override
    public void setVariableLocal(String taskId, String variableName, Object value) {
        taskService.setVariableLocal(taskId, variableName, value);
    }

    @Override
    public void setVariablesLocal(String taskId, Map<String, ? extends Object> variables) {
        taskService.setVariablesLocal(taskId, variables);
    }

}
