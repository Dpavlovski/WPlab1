package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Course {
    private Long courseId;
    private String name;
    private String description;
    private List<Student> students;
    private static Long counter=1L;

    public Course(String name, String description) {
        courseId=counter;
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
        counter++;
    }
}
