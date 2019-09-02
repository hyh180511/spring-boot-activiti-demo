package com.dapeng.demo.controller;

import com.dapeng.demo.common.handler.impl.InstanceHandler;
import com.dapeng.demo.common.handler.impl.TaskHandler;
import com.dapeng.demo.common.handler.impl.TaskQueryHandler;
import com.dapeng.demo.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 流程任务相关
 * </p>
 *
 * @author
 * @since 2019-08-20
 */
@RestController
@RequestMapping("api/flow/task")
@Api(value = "Task", tags = {"流程任务"})
public class TaskController {

    protected static Logger logger = LoggerFactory.getLogger(InstanceHandler.class);
    @Autowired
    private TaskHandler taskHandler;
    @Autowired
    private TaskQueryHandler taskQueryHandler;


    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "执行任务", notes = "部门领导审批：{\"days\":\"6\",\"check\":\"1\",\"actorIds\":[\"wangwu\",\"lifei\"]}                                             \n" +
            "分管领导审批：{\"check\":\"1\",\"actorIds\":[\"hr_li\",\"hr_zhang\"]}", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String")})
    public ResponseData executeTask(String taskId, @RequestBody Map<String, Object> variables) throws Exception {
        taskHandler.complete(taskId, variables);
        return ResponseData.success("执行任务成功");
    }


    @RequestMapping(value = "/claim", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "任务签收", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String"),
    })
    public ResponseData claim(String taskId, String userId) throws Exception {
        taskHandler.claim(taskId, userId);
        return ResponseData.success("任务签收成功");
    }


    @RequestMapping(value = "/unclaim", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "任务反签收", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String")})
    public ResponseData unclaim(String taskId) throws Exception {
        taskHandler.unclaim(taskId);
        return ResponseData.success("任务反签收成功");
    }


    @RequestMapping(value = "/delegate", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "任务委派", notes = "【act_ru_task】委派人：owner，被委派人：assignee，委派任务：delegateTask，任务回到委派人：resolveTask", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String"),
    })
    public ResponseData delegate(String taskId, String userId) throws Exception {
        taskHandler.delegate(taskId, userId);
        return ResponseData.success("任务委派成功");
    }


    @RequestMapping(value = "/resolve", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "任务归还：被委派人完成任务之后，将任务归还委派人", notes = "", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),})
    public ResponseData<Task> reslove(String taskId) throws Exception {
        taskHandler.resolveTask(taskId);
        return ResponseData.success();
    }


    @RequestMapping(value = "/assignee ", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "任务转办", notes = "【act_ru_task】设置assignee为转办人", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String"),
    })
    public ResponseData assignee(String taskId, String userId) throws Exception {
        taskHandler.setAssignee(taskId, userId);
        return ResponseData.success("任务转办成功");
    }


    @RequestMapping(value = "/query/taskId ", method = RequestMethod.GET)
//    @ResponseBody
    @ApiOperation(value = "任务查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String")})
    public ResponseData assignee(String taskId) throws Exception {
        Task task = taskQueryHandler.taskId(taskId);
//        TaskVO taskVO = new TaskVO();
//        BeanUtils.copyProperties(task,taskVO);
        return ResponseData.success(task);
    }


    @RequestMapping(value = "/comment/query ", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询批注信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String")})
    public ResponseData getTaskComments(String taskId) throws Exception {
        List<Comment> taskComments = taskHandler.getTaskComments(taskId);
        return ResponseData.success(taskComments);
    }


    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加批注信息", notes = "批注信息：act_hi_comment", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "instanceId", value = "实例ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "message", value = "批注内容", required = true, dataType = "String")
    })
    public ResponseData<Comment> addComment(String taskId, String instanceId, String message) throws Exception {
        Comment comment = taskHandler.addComment(taskId, instanceId, message);
        return ResponseData.success(comment);
    }
}

