import dao.EmployeeDao;
import dao.GroupDao;
import dao.StudentDao;
import models.Address;
import models.Employee;
import models.Group;
import models.Student;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class TestClass {

    @Test
    public void should_save_new_student_with_his_address() {
        // create group
        Group g = new Group();
        g.setName("4GI2");
        GroupDao.getInstance().save(g);

        Student s = new Student();
        s.setGroup(g);
        s.setMotherName("neila");
        s.setBirthday(LocalDate.now());
        s.setFirstName("ayoub");
        s.setLastName("hmama");
        s.setAddress(new Address());
        s.getAddress().setRoadName("any road");
        s.getAddress().setPostalCode(5180);
        s.getAddress().setRoadNumber("slkdjfl");
        s.getAddress().setCity("mahdia");

        // when
        StudentDao dao = StudentDao.getInstance();
        Student savedStudent = dao.save(s);
        // then
        Assert.assertTrue(savedStudent.getAddress().getId() != 0);
    }

    @Test
    public void should_select_student_with_his_address() {
        StudentDao dao = StudentDao.getInstance();
        Student student = dao.findById(1);
        System.out.println("student: " + student);
    }


    @Test
    public void should_return_allstudnts_with_adress() throws Exception {
        StudentDao dao = StudentDao.getInstance();
        List<Student> students = dao.findAll();
        for (Student s : students) {
            System.out.println(s);
            System.out.println("******************************************");
        }
    }

    @Test
    public void should_add_employee_to_database() {
        Employee e = new Employee();
        e.setFirstName("eclipse");
        e.setLastName("hmama");
        e.setBirthday(LocalDate.now());
        e.setAddress(new Address());
        e.getAddress().setRoadName("basatine");
        e.getAddress().setPostalCode(5180);
        e.getAddress().setRoadNumber("hahah");
        e.getAddress().setCity("mahdia");
        EmployeeDao dao = EmployeeDao.getInstance();
        Employee savedEmployee = dao.save(e);

    }


    @Test
    public void should_return_all_employees() throws Exception {
        EmployeeDao dao = EmployeeDao.getInstance();
        List<Employee> employees = dao.findAll();
        for (Employee e : employees) {
            System.out.println(e);
            System.out.println("-------------------------------------");
        }
    }


    @Test
    public void should_delete_employee_byID() throws Exception {
        EmployeeDao dao = EmployeeDao.getInstance();
        dao.deleteById(3);
        should_return_all_employees();
    }

    @Test
    public void should_delete_student(){
        StudentDao dao = StudentDao.getInstance();
        Student s = new Student();
        s.setAddress(new Address());
        s.setBirthday(LocalDate.now());
        s.setFirstName("new test");
        Student saved = dao.save(s);
        dao.deleteById(saved.getId());
    }

    @Test
    public void should_return_group_and_students_of_this_group(){
        GroupDao dao = GroupDao.getInstance();
        Group g = dao.findById("4GI1");
        System.out.println("g: "  + g );
    }

    @Test
        public  void should_add_group(){
            GroupDao dao = GroupDao.getInstance();
            Group g = new Group();
            g.setName("4GI");
            dao.save(g);
        }

}
