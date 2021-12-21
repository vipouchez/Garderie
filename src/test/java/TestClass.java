import models.Student;
import services.StudentService;

public class TestClass {

    public static void main(String[] args) {
        StudentService s = StudentService.getInstance();
        s.addStudent(new Student());
        s.getStudents();
    }

}
