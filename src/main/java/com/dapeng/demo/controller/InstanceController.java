package com.dapeng.demo.controller;

import com.dapeng.demo.common.handler.impl.InstanceHandler;
import com.dapeng.demo.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * <p>
 * 流程实例相关
 * </p>
 *
 * @author
 * @since 2019-08-20
 */
@RestController
@RequestMapping("api/flow/instance")
@Api(value = "Instance", tags = {"流程实例"})
public class InstanceController {

    protected static Logger logger = LoggerFactory.getLogger(InstanceHandler.class);
    @Autowired
    private InstanceHandler instanceHandler;


    /**
     * 启动流程实例
     */
    @RequestMapping(value = "/startByKey", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "启动流程实例--通过流程定义KEY，", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processKey", value = "流程定义KEY（模板ID）", required = true, dataType = "String")
    })
    public ResponseData<ProcessInstance> startByKey(String processKey) {
        ProcessInstance pi = instanceHandler.startProcessInstanceByKey(processKey);
        return ResponseData.success(pi);
    }

    /**
     * 启动流程实例
     */
    @RequestMapping(value = "/startById", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "启动流程实例--通过流程定义ID", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processDefinitionId", value = "流程定义ID", required = true, dataType = "String")
    })
    public ResponseData<ProcessInstance> startById(String processDefinitionId) {
        ProcessInstance pi = instanceHandler.startProcessInstanceById(processDefinitionId);
        return ResponseData.success(pi);
    }

    /**
     * 启动流程实例
     */
    @RequestMapping(value = "/startAndExecute", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "启动流程实例并执行第一个流程任务", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processKey", value = "流程定义KEY（模板ID）", required = true, dataType = "String"),
            //@ApiImplicitParam(name = "variables", value = "流程实例全局变量（实例有效期内随时获取）", required = true, dataType = "Map"),
    })
    public ResponseData startAndExecute(String processKey,
                                        @RequestBody Map<String, Object> variables) {

        Map<String, Object> map = instanceHandler.startInstanceAndExecuteFirstTask(processKey, variables);
        return ResponseData.success(map);
    }

    /**
     * 启动流程实例
     */
    @RequestMapping(value = "/startExecuteAndSetActor", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "启动流程实例并执行第一个流程任务,并设置下一任务处理人", notes = "{\"days\":\"6\",\"actorIds\":[\"zhangsan\",\"lisi\"]}", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processKey", value = "流程定义KEY（模板ID）", required = true, dataType = "String"),
            //@ApiImplicitParam(name = "variables", value = "流程实例全局变量（实例有效期内随时获取）", required = true, dataType = "map"),
    })
    public ResponseData startAndExecuteAndSetActor(String processKey, @RequestBody Map<String, Object> variables
    ) {
        Map<String, Object> map = instanceHandler.startInstanceAndExecuteFirstTask(processKey, variables);

        return ResponseData.success(map);
    }


    /**
     * 挂起流程实例
     */
    @RequestMapping(value = "/suspendById", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "挂起流程实例", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")
    })
    public ResponseData suspendById(String processInstanceId) throws Exception {
        instanceHandler.suspendProcessInstanceById(processInstanceId);
        return ResponseData.success("流程实例挂起成功");
    }

    /**
     * 激活流程实例
     */
    @RequestMapping(value = "/activateById", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "激活流程实例", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")
    })
    public ResponseData activateById(String processInstanceId) throws Exception {
        instanceHandler.activateProcessInstanceById(processInstanceId);
        return ResponseData.success("激活流程实例");
    }

}

