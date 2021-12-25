package dao;

import config.Config;
import models.Group;
import models.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
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




    public List<Group> findAll() throws Exception{
        List<Group> result = new LinkedList<>();

        String sql = "SELECT * FROM group";
        try(Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery(); // execute the database insert query

            while ( rs.next() == true){
                // create new empty group entity
                Group g = new Group();
                g.setName(rs.getString(1));

                // then add the new created group to the list of groups  like follows::
                result.add(g);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
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
