package com.dapeng.demo.common.handler.impl;

import com.dapeng.demo.common.handler.ActProcess;
import com.dapeng.demo.common.handler.ServiceFactory;
import org.activiti.engine.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;


@Component
public class ProcessHandler extends ServiceFactory implements ActProcess {

    protected static Logger logger = LoggerFactory.getLogger(ProcessHandler.class);
    //https://blog.csdn.net/weixin_43607664/article/details/88664777
    public static final String BPMN_FILE_SUFFIX = ".bpmn";

    @Override
    public DeploymentBuilder createDeployment() {
        return repositoryService.createDeployment();
    }

    @Override
    public DeploymentQuery createDeploymentQuery() {
        return repositoryService.createDeploymentQuery();
    }

    @Override
    public ProcessDefinitionQuery createProcessDefinitionQuery() {
        return repositoryService.createProcessDefinitionQuery();
    }

    @Override
    public Deployment deploy(String bpmnFileUrl) {
        Deployment deploy = createDeployment().addClasspathResource(bpmnFileUrl).deploy();
        return deploy;
    }

    @Override
    public Deployment deploy(String url, String pngUrl) {

        Deployment deploy = createDeployment().addClasspathResource(url).addClasspathResource(pngUrl).deploy();
        return deploy;
    }

    @Override
    public Deployment deployBpmnAndDrl(String url, String drlUrl) {
        Deployment deploy = createDeployment().addClasspathResource(url).addClasspathResource(drlUrl).deploy();
        return deploy;
    }

    @Override
    public Deployment deploy(String url, String name, String category) {
        Deployment deploy = createDeployment().addClasspathResource(url).name(name).category(category).deploy();
        return deploy;
    }

    @Override
    public Deployment deploy(String url, String pngUrl, String name, String category) {
        Deployment deploy = createDeployment().addClasspathResource(url).addClasspathResource(pngUrl)
                .name(name).category(category).deploy();
        return deploy;
    }

    @Override
    public boolean exist(String processDefinitionKey) {
        ProcessDefinitionQuery processDefinitionQuery
                = createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey);
        long count = processDefinitionQuery.count();
        return count > 0 ? true : false;
    }

    @Override
    public Deployment deploy(String name, String category, InputStream in) {
        Deployment deploy = createDeployment().addInputStream(name + BPMN_FILE_SUFFIX, in).name(name).category(category).deploy();
        return deploy;

    }

    @Override
    public ProcessDefinition queryByProcessDefinitionKey(String processDefinitionKey) {
        ProcessDefinition processDefinition = createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).active().singleResult();
        return processDefinition;
    }


}
