package dao;

import models.Group;

import java.util.List;


public class GroupDao   {

    // ***************************
    // singleton design pattern

    // private constructor so that client code is forced to use the getInstance() method to create instance (new StudentDao() will not be accepted)
    private GroupDao(){
    }

    // instance to return when any client asks for it.
    private static GroupDao instance = new GroupDao();

    // the only possible way for a client code to get the instance. (exp. see how StudentService asks for an instance of StudentDao using the getInstance())
    public static GroupDao getInstance() {
        return instance;
    }
    // ***************************



    public List<Group> findAll(){
        // TODO
        return null;
    }

    public Group save(Group s){
        //todo
        return null;
    }




    public boolean deleteById(String id){
        //todo
        return false;
    }


    public Group findById(String id){
        //todo
        return null;
    }

}
