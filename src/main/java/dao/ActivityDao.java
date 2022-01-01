
package dao;

import config.Config;
import models.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class ActivityDao {

    // ***************************
    // singleton design pattern

    // private constructor so that client code is forced to use the getInstance() method to create instance (new StudentDao() will not be accepted)
    private ActivityDao() {
    }

    // instance to return when any client asks for it.
    private static ActivityDao instance = new ActivityDao();

    // the only possible way for a client code to get the instance. (exp. see how StudentService asks for an instance of StudentDao using the getInstance())
    public static ActivityDao getInstance() {
        return instance;
    }
    // ***************************


    public List<Activity> findAll() throws Exception {
        List<Activity> result = new LinkedList<>();

        String sql = "select * from activity";
        try (Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(); // execute the database insert query

            while (rs.next() == true) {
                // create new empty activity entity
                Activity a = new Activity();
                a.setId(rs.getInt(1));
                a.setLabel(rs.getString(2));
                Employee employee = EmployeeDao.getInstance().findById(rs.getInt(3));
                Group group = GroupDao.getInstance().findById(rs.getString(4));
                a.setResponsible(employee);
                a.setGroup(group);

                // then add the new created activity to the list of activities as follows:
                result.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Activity save(Activity a){
        String sql = "INSERT INTO activity (label, employee_id, group_id " +
                ") VALUES (?,?,?)";
        try(
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, a.getLabel());
            stmt.setInt(2, a.getResponsible().getId());
            stmt.setString(3, a.getGroup().getName());


            stmt.executeUpdate(); // execute the database insert query
            ResultSet rs = stmt.getGeneratedKeys(); // database returns

            rs.next();
            int generatedActivityId = rs.getInt(1);
            a.setId(generatedActivityId);
            return a;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }



    public boolean deleteById(int id) {
        String sql = "DELETE FROM activity WHERE id = ? ";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public Activity findById(int id) {
        String sql = "select * from activity where id = ? ";
        try (Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(); // execute the database insert query
            rs.next();

            // create new empty activity entity
            Activity a = new Activity();
            a.setId(rs.getInt(1));
            a.setLabel(rs.getString(2));
            Employee employee = EmployeeDao.getInstance().findById(rs.getInt(3));
            Group group = GroupDao.getInstance().findById(rs.getString(4));
            a.setResponsible(employee);
            a.setGroup(group);
            return a;
            // then add the new created activity to the list of activities as follows:
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
