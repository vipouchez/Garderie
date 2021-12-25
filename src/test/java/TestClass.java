import dao.AddressDao;
import dao.StudentDao;
import models.Address;
import models.Student;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TestClass {

    @Test
    public void should_save_new_student_with_his_address(){
        Student s = new Student();
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
        System.out.println("address: "  + student.getAddress());
    }

}
