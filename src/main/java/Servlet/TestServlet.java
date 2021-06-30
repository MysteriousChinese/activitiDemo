package Servlet;

import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "test",urlPatterns = {"/test"})
public class TestServlet  extends HttpServlet {

    @Autowired
    private RepositoryService repositoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        if(repositoryService != null){
            System.out.println("Autowired repositoryService is not null");
        }
        else{
            System.out.println("Autowired repositoryService is null");
        }
    }
}
