package com.dapeng.demo.common.handler;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.task.Comment;

import java.util.List;
import java.util.Map;

public interface ActTask {

    /**
     * 为流程任务设置变量。
     * 如果变量尚未存在，则将在任务中创建该变量。
     *
     * @param taskId        任务的id，不能为null.
     * @param variableName  变量键名.
     * @param variableValue 变量键值.
     * @throws
     */
    public void setVariableLocal(String taskId, String variableName, Object variableValue);

    /**
     * 为流程任务设置多对变量。
     * 如果变量尚未存在，则将在任务中创建该变量。
     *
     * @param taskId    任务的id，不能为null.
     * @param variables 多对变量键值对.
     * @throws
     */
    public void setVariablesLocal(String taskId, Map<String, ? extends Object> variables);

    /**
     * 任务签收。
     *
     * @param taskId 任务的id，不能为null.
     * @param userId 签收人标识.
     * @throws
     */
    public void claim(String taskId, String userId) throws RuntimeException, Exception;

    /**
     * 任务反签收
     *
     * @param taskId 任务的id，不能为null.
     * @throws
     */
    public void unclaim(String taskId) throws RuntimeException, Exception;


    /**
     * 执行任务
     *
     * @param taskId 任务的id，不能为null.
     * @throws
     */
    public void complete(String taskId) throws RuntimeException, Exception;


    /**
     * 执行任务，并设置任务变量。
     *
     * @param taskId    任务的id，不能为null.
     * @param variables 任务变量.
     * @throws
     */
    public void complete(String taskId, Map<String, Object> variables) throws RuntimeException, Exception;


    /**
     * 任务移交：将任务的所有权转移给其他用户。
     *
     * @param taskId 任务的id，不能为null.
     * @param userId 接受所有权的人.
     * @throws ActivitiObjectNotFoundException 当任务或用户不存在时.
     */

    public void setAssignee(String taskId, String userId) throws RuntimeException, Exception;

    /**
     * 任务委派
     *
     * @param taskId 任务的id，不能为null.
     * @param userId 被委派人ID.
     * @throws ActivitiObjectNotFoundException 当任务或用户不存在时.
     */
    void delegate(String taskId, String userId);


    /**
     * 委派任务完成，归还委派人
     *
     * @param taskId 任务的id，不能为null.
     * @throws ActivitiObjectNotFoundException 当任务不存在时.
     */
    void resolveTask(String taskId);


    /**
     * 为任务添加任务处理人。
     *
     * @param taskId 任务的id，不能为null.
     * @param userId 任务处理人ID.
     * @throws
     */
    public void addCandidateUser(String taskId, String userId) throws RuntimeException, Exception;


    /**
     * 为流程任务 和/或 流程实例添加注释。
     *
     * @param taskId            流程任务ID.
     * @param processInstanceId 流程实例ID.
     * @param message           注释信息
     * @throws
     */
    public Comment addComment(String taskId, String processInstanceId, String message) throws Exception;


    /**
     * 查询与任务相关的注释信息。
     *
     * @param taskId 流程任务ID.
     * @throws
     */
    public List<Comment> getTaskComments(String taskId) throws RuntimeException, Exception;

}
