package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    public List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
        students.add(new Student("petar.petrov","petarPetrov123","Петар","Петров"));
        students.add(new Student("mile.milev","mileMilev123","Миле","Милев"));
        students.add(new Student("gjorgji.gjorgjiev","gjorgiGjorgiev123","Ѓорѓи","Ѓорѓиев"));
        students.add(new Student("riste.ristev","risteRistev123","Ристе","Ристев"));
        students.add(new Student("pavle.pavlov","pavlePavlov123","Павле","Павлов"));
    }

    public List<Student> findAllStudents () {
        return students;
    }

    public List<Student> findAllByNameOrSurname (String text) {
        return students.stream().filter(s->s.getName().contains(text) || s.getSurname().contains(text)).toList();
    }
    public Student findByUsername (String username) {
        return students.stream().filter(s->s.getUsername().equals(username)).findFirst().orElse(null);
    }
}