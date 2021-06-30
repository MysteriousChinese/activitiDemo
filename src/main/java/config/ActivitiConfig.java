package config;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ActivitiConfig {

    @Bean
    public ProcessEngine processEngine(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        if (processEngine == null) {
            System.out.println("processEngine is null");
            processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
        }
        else{
            System.out.println("processEngine is not null");
        }

        return processEngine;
    }

    @Bean
    public RepositoryService repositoryService(){
        RepositoryService repositoryService = processEngine().getRepositoryService();
        if(repositoryService == null){
            System.out.println("repositoryService is null");
        }
        else{
            System.out.println("repositoryService is not null");
        }
        return repositoryService;
    }

    @Bean
    public RuntimeService runtimeService(){
        return processEngine().getRuntimeService();
    }

    @Bean
    public TaskService taskService(){
        return processEngine().getTaskService();
    }

    @Bean
    public HistoryService historyService(){
        return processEngine().getHistoryService();
    }

}
