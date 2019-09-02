package com.dapeng.demo.confg;

import com.alibaba.druid.pool.DruidDataSource;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class ActivitiesDataSourceConfig extends AbstractProcessEngineAutoConfiguration {

    @Autowired
    private ActivitiesDataSourceProperties activitiesDataSourceProperties;

    @Bean
    @Primary
    public DataSource activitiesDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(activitiesDataSourceProperties.getUrl());
        dataSource.setUsername(activitiesDataSourceProperties.getUserName());
        dataSource.setPassword(activitiesDataSourceProperties.getPassWord());
        dataSource.setDriverClassName(activitiesDataSourceProperties.getDriver());
        dataSource.setMaxWait(activitiesDataSourceProperties.getMaxWait());
        dataSource.setInitialSize(activitiesDataSourceProperties.getInitialSize());

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(activitiesDataSource());
    }

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration() {
        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        processEngineConfiguration.setDataSource(activitiesDataSource());
        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        processEngineConfiguration.setTransactionManager(transactionManager());
//        processEngineConfiguration.setJobExecutorActivate(true);
        processEngineConfiguration.setDbIdentityUsed(false);
        processEngineConfiguration.setAsyncExecutorActivate(false);
        // 流程历史等级
        processEngineConfiguration.setHistoryLevel(HistoryLevel.FULL);

        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
        return processEngineConfiguration;
    }


}
