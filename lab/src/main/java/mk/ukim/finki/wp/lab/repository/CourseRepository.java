package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    public List<Course> courses;

    public CourseRepository() {
        courses=new ArrayList<>();
        courses.add(new Course("Веб програмирање","JavaSpringBoot"));
        courses.add(new Course("Оперативни системи","Putty"));
        courses.add(new Course("Електронска и мобилна трговија","Android"));
        courses.add(new Course("Компјутерски мрежи","GNS3"));
        courses.add(new Course("Објектно-ориентирано програмирање","Codeblocks"));
    }

    public List<Course> findAllCourses(){
        return courses;
    }

    public Course findById (Long courseId) {
       return courses.stream().filter(s->s.getCourseId().equals(courseId)).findFirst().orElse(null);
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
       Course course= courses.stream().filter(c->c.getCourseId().equals(courseId)).findFirst().orElse(null);
       if(course!=null)
           return course.getStudents();
       return null;
    }

    public Course addStudentToCourse(Student student, Course course) {
        if(student!=null && courses.contains(course)) {
            int id= courses.indexOf(course);
            courses.get(id).getStudents().add(student);
        }
        return course;
    }
}