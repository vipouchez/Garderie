
package dao;

import models.Activity;

import java.util.List;


public class ActivityDao   {

    // ***************************
    // singleton design pattern

    // private constructor so that client code is forced to use the getInstance() method to create instance (new StudentDao() will not be accepted)
    private ActivityDao(){
    }

    // instance to return when any client asks for it.
    private static ActivityDao instance = new ActivityDao();

    // the only possible way for a client code to get the instance. (exp. see how StudentService asks for an instance of StudentDao using the getInstance())
    public static ActivityDao getInstance() {
        return instance;
    }
    // ***************************



    public List<Activity> findAll(){
        // TODO
        return null;
    }

    public Activity save(Activity s){
        //todo
        return null;
    }




    public boolean deleteById(String id){
        //todo
        return false;
    }


    public Activity findById(String id){
        //todo
        return null;
    }

}
