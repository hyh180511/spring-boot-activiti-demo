package com.dapeng.demo.common.handler;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.Map;

public interface ActInstance {

    /**
     * 启动流程实例---通过流程定义key（模板ID)
     *
     * @param processDefinitionKey 流程定义Key，不能为空.
     */
    ProcessInstance startProcessInstanceByKey(String processDefinitionKey);

    /**
     * 启动流程实例并执行第一个流程任务---通过流程定义key（模板ID)
     *
     * @param processDefinitionKey 流程定义Key，不能为空.
     * @return
     */
    Map<String, Object> startInstanceAndExecuteFirstTask(String processDefinitionKey, Map<String, Object> variables);

    /**
     * 启动流程实例---流程定义ID
     *
     * @param processDefinitionId 流程定义ID，不能为空.
     */
    ProcessInstance startProcessInstanceById(String processDefinitionId);

    /**
     * 启动流程实例--通过流程定义key、流程实例变量
     *
     * @param processDefinitionKey 流程定义Key，不能为空.
     * @param variables            要传递给流程实例的变量，可以为null.
     */
    ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> variables);

    /**
     * 启动新流程实例----使用给定key在最新版本的流程定义中。
     * <p>
     * 可以提供业务key以将流程实例与具有明确业务含义的特定标识符相关联。
     * 例如，在订单处理中，业务密钥可以是订单ID。
     * 然后，可以使用此业务键轻松查找该流程实例.
     * 提供这样的业务密钥肯定是一个最佳实践.
     * <p>
     * 【说明】processdefinitionKey-businessKey的组合必须是唯一的。
     *
     * @param processDefinitionKey 流程定义Key，不能为空.
     * @param variables            要传递给流程实例的变量，可以为null.
     * @param businessKey          业务key
     */
    ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> variables) throws RuntimeException, Exception;


    void suspendProcessInstanceById(String processInstanceId) throws RuntimeException, Exception;


    void deleteProcessInstance(String processInstanceId, String deleteReason) throws RuntimeException, Exception;

    /**
     * 设置流程开始节点发起人
     */
    void setAuthenticatedUserId(String authenticatedUserId);

    /**
     * 启动流程实例并执行第一个流程任务，并且设置下一任务处理人---通过流程定义key（模板ID)
     *
     * @param processDefinitionKey 流程定义Key，不能为空.
     * @param variables            流程实例变量.
     * @param actorIds             下一环节任务处理人.
     */
    ProcessInstance startInstanceAndExecuteFirstTask(String processDefinitionKey, Map<String, Object> variables, Map<String, Object> actorIds);

    /**
     * 使用给定的id激活流程实例.
     *
     * @throws ActivitiObjectNotFoundException 如果没有这样的processInstance可以找到.
     * @throws ActivitiException               如果流程实例已处于状态活动状态.
     */
    void activateProcessInstanceById(String processInstanceId);
}
