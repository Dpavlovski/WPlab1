package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="StudentEnrollment",urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/listCourses");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        WebContext context=new WebContext(req,resp,req.getServletContext());
        String username=req.getParameter("student");
        String courseId=req.getSession().getAttribute("courseId").toString();
        if (req.getParameter("student") == null){
            resp.sendRedirect("/AddStudent?courseId="+courseId);
            return;
        }
        courseService.addStudentInCourse(username,Long.valueOf(courseId));
        String courseName=courseService.searchById(Long.valueOf(courseId)).getName();
        context.setVariable("courseId",courseName);
        context.setVariable("students",courseService.listStudentsByCourse(Long.valueOf(courseId)));
        springTemplateEngine.process("studentsInCourse.html",context,resp.getWriter());
    }
}