package dao;

import models.Employee;

import java.util.List;


public class EmployeeDao   {

    // ***************************
    // singleton design pattern

    // private constructor so that client code is forced to use the getInstance() method to create instance (new StudentDao() will not be accepted)
    private EmployeeDao(){
    }

    // instance to return when any client asks for it.
    private static EmployeeDao instance = new EmployeeDao();

    // the only possible way for a client code to get the instance. (exp. see how StudentService asks for an instance of StudentDao using the getInstance())
    public static EmployeeDao getInstance() {
        return instance;
    }
    // ***************************



    public List<Employee> findAll(){
        // TODO
        return null;
    }

    public Employee save(Employee s){
        //todo
        return null;
    }




    public boolean deleteById(String id){
        //todo
        return false;
    }


    public Employee findById(String id){
        //todo
        return null;
    }

}
