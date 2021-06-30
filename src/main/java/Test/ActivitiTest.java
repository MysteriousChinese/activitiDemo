package Test;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class ActivitiTest {



    @Autowired
    @Qualifier("processEngineConfiguration")
    protected StandaloneProcessEngineConfiguration standaloneProcessEngineConfiguration;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void creatActivitiTask(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        /*if (standaloneProcessEngineConfiguration == null) {
            System.out.println("standaloneProcessEngineConfiguration is null");
        }
        else{
            System.out.println("standaloneProcessEngineConfiguration is not null");
        }*/
        //System.out.printf(String.format("repositoryService is %s",repositoryService==null?"null":"not null"));
        //加载的那两个内容就是我们之前已经弄好的基础内容哦。
        //得到了流程引擎
        /*repositoryService
                .createDeployment()
                .addClasspathResource("shenqing.bpmn")
                .addClasspathResource("shenqing.png")
                .deploy();*/
    }
}
