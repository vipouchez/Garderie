package dao;

import models.Student;

import java.sql.*;
import java.util.List;


public class StudentDao   {

    // ***************************
    // singleton design pattern

    // private constructor so that client code is forced to use the getInstance() method to create instance (new StudentDao() will not be accepted)
    private StudentDao(){
    }

    // instance to return when any client asks for it.
    private static StudentDao instance = new StudentDao();

    // the only possible way for a client code to get the instance. (exp. see how StudentService asks for an instance of StudentDao using the getInstance())
    public static StudentDao getInstance() {
        return instance;
    }
    // ***************************



    public List<Student> findAll(){
        // TODO
        return null;
    }

    public Student save(Student s){
        String sql = String.format("INSERT INTO `entity`(`first_name`, `last_name`, `father_name`) VALUES ('%s','%s','%s')",s.getFirstName(), s.getLastName(), s.getFatherName() );
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garderie", "root", "");
            Statement stmt = conn.createStatement();
            ) {
            stmt.execute(sql);
            // Extract data from result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }




    public boolean deleteById(String id){
        //todo
        return false;
    }


    public Student findById(String id){
        //todo
        return null;
    }

}
