package com.dapeng.demo.common.handler;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

import java.util.List;
import java.util.Map;

public interface ActTaskQuery {

    public TaskQuery createTaskQuery();

    public String findVariableByTaskId(String taskId, String variableName);


    public String findBusinessKeyByTaskId(String taskId);


    public HistoricTaskInstanceQuery createHistoricTaskInstanceQuery();

    /**
     * 执行流程任务
     *
     * @param taskId 流程任务ID.
     * @result Task  流程任务.
     */

    public Task taskId(String taskId);

    /**
     * 查询活动的流程任务
     *
     * @param processInstanceId 流程实例ID.
     * @result Task  流程任务.
     */
    public Task processInstanceId(String processInstanceId);


    public List<Task> taskCandidateUser(String userId, int start, int limit);


    public List<Task> taskAssignee(String userId, int start, int limit);


    public List<Task> taskCandidateOrAssigned(String userId, int start, int limit);


    public List<Task> taskCandidateUserByCondition(String userId, Map<String, Object> variables, int start, int limit);


    public List<Task> taskAssigneeByCondition(String userId, Map<String, Object> variables, int start, int limit);


    public List<Task> taskCandidateOrAssignedByCondition(String userId, Map<String, Object> variables, int start, int limit);


    public long countTaskCandidateUser(String userId);


    public long countTaskAssignee(String userId);


    public long countTaskCandidateOrAssigned(String userId);


    public long countTaskCandidateUserByCondition(String userId, Map<String, Object> variables);


    public long countTaskAssigneeByCondition(String userId, Map<String, Object> variables);


    public long countTaskCandidateOrAssignedByCondition(String userId, Map<String, Object> variables);


    public List<HistoricTaskInstance> taskAssigneeHistory(String userId, int start, int limit);


    public long countTaskAssigneeByTaskQuery(String assignee, TaskQuery query);


    public List<Task> taskAssigneeByTaskQuery(String assignee, TaskQuery query, int start, int limit);

}
