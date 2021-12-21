import dao.StudentDao;
import models.Student;
import services.StudentService;

public class TestClass {

    public static void main(String[] args) {
        StudentDao dao = StudentDao.getInstance();
        Student s = new Student();
        s.setFirstName("ayoub_java");
        s.setLastName("hmama_java");
        s.setFatherName("fathi_java");
        dao.save(s);
    }

}
