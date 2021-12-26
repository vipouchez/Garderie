package dao;

import config.Config;
import error.NotFoundException;
import models.Address;
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

                // then add the new created group to the list of groups as follows:
                result.add(g);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public Group save(Group g){

        String sql = "insert into groups (name) values (?) ";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, g.getName());
            stmt.executeUpdate(); // execute the database insert query
            return g;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean deleteById(String id){
        //todo
        return false;
    }

    public Group findById(String name){
        String sql = "SELECT * FROM `groups` where name = ? ";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            boolean foundSomething = rs.next();
            if (!foundSomething) {
                throw new NotFoundException("Group " + name + " does not exist");
            }
            Group g = new Group();
            g.setName(name);

            // TODO: also fetch list of activities for this gorup

            // get the list of students
            List<Student> groupStudents = getListOfStudentsInGroup(name);
            g.setStudents(groupStudents);
            return g;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Student> getListOfStudentsInGroup(String name) {
        String sql = "SELECT * FROM `student` where group_id = ? ";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            List<Student> result = new LinkedList<>();
            while(rs.next()){
                Student s = new Student();
                s.setId(rs.getInt(1));
                // get student fields from the current result set exp:
                s.setFirstName(rs.getString(2));
                s.setLastName(rs.getString(3));
                s.setFatherName(rs.getString(4));
                Date d = rs.getDate(5);
                s.setBirthday(d.toLocalDate());
                result.add(s);
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
