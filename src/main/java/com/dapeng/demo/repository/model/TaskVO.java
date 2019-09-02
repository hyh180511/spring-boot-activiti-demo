package com.dapeng.demo.repository.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author
 * @since 2019-08-27
 */
@ApiModel(value = "Task对象", description = "")
public class TaskVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键，由Activiti的主键生成策略产生")
    private String id;

    @ApiModelProperty(value = "数据版本号")
    private Integer rev;

    @ApiModelProperty(value = "任务所处的执行流ID")
    private String executionId;

    @ApiModelProperty(value = "对应的流程实例ID")
    private String procInstId;

    @ApiModelProperty(value = "对应流程定义数据的ID")
    private String procDefId;

    @ApiModelProperty(value = "任务名称，在流程文件中定义")
    private String name;

    @ApiModelProperty(value = "父任务ID，子任务才会设置该字段的值")
    private String parentTaskId;

    private String description;

    private String taskDefKey;

    private String owner;

    private String assignee;

    private String delegation;

    private Integer priority;

    private LocalDateTime createTime;

    private LocalDateTime dueDate;

    private String category;

    private Integer suspensionState;

    private String tenantId;

    private String formKey;

    private LocalDateTime claimTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskDefKey() {
        return taskDefKey;
    }

    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSuspensionState() {
        return suspensionState;
    }

    public void setSuspensionState(Integer suspensionState) {
        this.suspensionState = suspensionState;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public LocalDateTime getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(LocalDateTime claimTime) {
        this.claimTime = claimTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", rev=" + rev +
                ", executionId=" + executionId +
                ", procInstId=" + procInstId +
                ", procDefId=" + procDefId +
                ", name=" + name +
                ", parentTaskId=" + parentTaskId +
                ", description=" + description +
                ", taskDefKey=" + taskDefKey +
                ", owner=" + owner +
                ", assignee=" + assignee +
                ", delegation=" + delegation +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", dueDate=" + dueDate +
                ", category=" + category +
                ", suspensionState=" + suspensionState +
                ", tenantId=" + tenantId +
                ", formKey=" + formKey +
                ", claimTime=" + claimTime +
                "}";
    }
}
