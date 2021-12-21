package dao;

import models.Student;

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
               //todo
        return null;
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
