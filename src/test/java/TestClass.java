import dao.StudentDao;
import models.Student;

public class TestClass {

    public static void main(String[] args) throws Exception  {
       /* StudentDao dao = StudentDao.getInstance();
        Student s = new Student();
        s.setFirstName("adem");
        s.setLastName("hmama");
        s.setFatherName("fathi");
        s.setBirthday(LocalDate.now());
        dao.save(s);*/


        /*StudentDao dao = StudentDao.getInstance();
        List<Student> all = dao.findAll();
        for(Student s : all ){
            System.out.println(s);
            System.out.println("============================");
        }
        */

        /*StudentDao dao = StudentDao.getInstance();
        Student s = dao.findById(3);
        System.out.println("return student: " + s);
*/

        StudentDao dao = StudentDao.getInstance();
        Student  s = dao.findById(2);
        s.setFirstName("tounis");
        s.setMotherName("bounes");
        dao.update(s);
    }



}
