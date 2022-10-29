package mk.ukim.finki.wp.lab.service.imp;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImp implements CourseService {
    private CourseRepository courseRepository;
    private StudentService studentService;

    public CourseServiceImp(CourseRepository courseRepository, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        if(username==null || courseId==null) throw new IllegalArgumentException();
        Student s=studentService.searchByUsername(username);
        Course c=courseRepository.findById(courseId);
        return courseRepository.addStudentToCourse(s,c);
    }
    @Override
    public List<Course> listAll () {
        return courseRepository.findAllCourses();
    }

    @Override
    public Course searchById(Long courseId) {
        return courseRepository.findById(courseId);
    }
}
