package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="ListStudents",urlPatterns="/AddStudent")
public class ListStudentServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;

    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("courseId") == null){
            resp.sendRedirect("/listCourses");
            return;
        }
        resp.setCharacterEncoding("UTF-8");
        WebContext context=new WebContext(req,resp,req.getServletContext());
        String courseId=req.getParameter("courseId");
        req.getSession().setAttribute("courseId",courseId);
        context.setVariable("students",studentService.listAll());
        context.setVariable("courseId",courseId);
        springTemplateEngine.process("listStudents.html",context,resp.getWriter());
    }

}