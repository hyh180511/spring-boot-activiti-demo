package com.dapeng.demo.common.handler.impl;

import com.dapeng.demo.common.handler.ActTaskQuery;
import com.dapeng.demo.common.handler.ServiceFactory;
import com.dapeng.demo.common.model.VariablesEnum;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


@Component
public class TaskQueryHandler extends ServiceFactory implements ActTaskQuery {

    protected static Logger logger = LoggerFactory.getLogger(TaskQueryHandler.class);


    @Override
    public TaskQuery createTaskQuery() {

        return taskService.createTaskQuery();
    }

    @Override
    public HistoricTaskInstanceQuery createHistoricTaskInstanceQuery() {

        return historyService.createHistoricTaskInstanceQuery();
    }

    @Override
    public Task taskId(String taskId) {

        return createTaskQuery().taskId(taskId).singleResult();
    }

    @Override
    public List<Task> taskCandidateUser(String candidateUser, int start, int limit) {

        return createTaskQuery().taskCandidateUser(candidateUser)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(start, limit);
    }

    @Override
    public List<Task> taskAssignee(String assignee, int start, int limit) {

        return createTaskQuery().taskAssignee(assignee).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(start, limit);
    }

    @Override
    public List<Task> taskCandidateOrAssigned(String userId, int start, int limit) {

        return createTaskQuery().taskCandidateOrAssigned(userId).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(start, limit);
    }

    @Override
    public List<HistoricTaskInstance> taskAssigneeHistory(String assignee, int start, int limit) {

        return createHistoricTaskInstanceQuery().taskAssignee(assignee).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(start, limit);
    }


    public TaskQuery buildeTaskQueryByvariables(Map<String, Object> args) {
        TaskQuery tq = createTaskQuery();
        if (args != null && args.size() > 0) {
            for (Entry<String, Object> entry : args.entrySet()) {
                if (VariablesEnum.activityName.toString().equals(entry.getKey()) ||
                        VariablesEnum.orgName.toString().equals(entry.getKey())) {
                    tq.processVariableValueLike(entry.getKey(), String.valueOf(entry.getValue()));
                } else {
                    tq.processVariableValueEquals(entry.getKey(), entry.getValue());
                }
            }
        }

        return tq;
    }

    @Override
    public List<Task> taskCandidateUserByCondition(String candidateUser, Map<String, Object> variables, int start, int limit) {
        return buildeTaskQueryByvariables(variables).taskCandidateUser(candidateUser)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(start, limit);
    }

    @Override
    public List<Task> taskAssigneeByCondition(String assignee, Map<String, Object> variables, int start, int limit) {
        return buildeTaskQueryByvariables(variables).taskAssignee(assignee).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(start, limit);
    }

    @Override
    public List<Task> taskCandidateOrAssignedByCondition(String userId, Map<String, Object> variables, int start,
                                                         int limit) {
        return buildeTaskQueryByvariables(variables).taskCandidateOrAssigned(userId).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(start, limit);
    }

    @Override
    public long countTaskCandidateUser(String candidateUser) {
        return createTaskQuery().taskCandidateUser(candidateUser)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public long countTaskAssignee(String assignee) {
        return createTaskQuery().taskAssignee(assignee).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public long countTaskCandidateOrAssigned(String userId) {
        return createTaskQuery().taskCandidateOrAssigned(userId).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public long countTaskCandidateUserByCondition(String candidateUser, Map<String, Object> variables) {
        return buildeTaskQueryByvariables(variables).taskCandidateUser(candidateUser)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public long countTaskAssigneeByCondition(String assignee, Map<String, Object> variables) {
        return buildeTaskQueryByvariables(variables).taskAssignee(assignee).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public long countTaskCandidateOrAssignedByCondition(String userId, Map<String, Object> variables) {
        return createTaskQuery().taskCandidateOrAssigned(userId).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public Task processInstanceId(String processInstanceId) {

        return createTaskQuery().processInstanceId(processInstanceId).singleResult();
    }

    @Override
    public String findBusinessKeyByTaskId(String taskId) {

        Task task = this.createTaskQuery().taskId(taskId).singleResult();

        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();

        if (pi != null) {
            return pi.getBusinessKey();
        }

        return null;
    }

    @Override
    public String findVariableByTaskId(String taskId, String variableName) {

        Object value = taskService.getVariable(taskId, variableName);

        return String.valueOf(value);
    }

    @Override
    public long countTaskAssigneeByTaskQuery(String assignee, TaskQuery query) {
        return query.taskAssignee(assignee).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public List<Task> taskAssigneeByTaskQuery(String assignee, TaskQuery query, int start, int limit) {
        return query.taskAssignee(assignee).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(start, limit);
    }

}