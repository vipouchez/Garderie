package dao;

import config.Config;
import error.NotFoundException;
import models.Address;
import models.Employee;
import models.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
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



    public List<Employee> findAll() throws Exception {
        List<Employee> result = new LinkedList<>();

        String sql = "SELECT * FROM employee e, address a WHERE e.address_id = a.id";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(); // execute the database insert query

            while (rs.next() == true) {
                // create new empty Employee entity
                Employee e = new Employee();
                e.setId(rs.getInt(1));
                e.setFirstName(rs.getString(2));
                e.setLastName(rs.getString(3));
                e.setFatherName(rs.getString(4));
                //e.setAddress_id(rs.getInt(5));

                Date d = rs.getDate(6);
                e.setBirthday(d.toLocalDate());
                e.setImageUrl(rs.getString(7));
                e.setCinNumber(rs.getString(8));
                e.setPhoneNumber(rs.getString(9));
                // then add the new created emp to the list of employees  like follows::
                result.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }




    public Employee save(Employee s){
        //todo
        return null;
    }




    public boolean deleteById(String id){
        //todo
        return false;
    }


    public Employee findById(int id) {
        Employee e = new Employee();
        String sql = "SELECT * FROM `employee` e inner join address a on e.address_id = a.id where s.id = ? ";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            boolean foundSomething = rs.next();
            if (!foundSomething) {
                throw new NotFoundException();
            }

            e.setId(rs.getInt(1));
            e.setFirstName(rs.getString(2));
            e.setLastName(rs.getString(3));
            e.setFatherName(rs.getString(4));
            Date d = rs.getDate(5);
            e.setBirthday(d.toLocalDate());
            e.setImageUrl(rs.getString(6));
            e.getCinNumber(rs.getString(7));
            e.getPhoneNumber(rs.getString(8));
            e.setAddress(new Address());
            e.getAddress().setId(rs.getInt(10));
            e.getAddress().setPostalCode(rs.getInt(11));
            e.getAddress().setRoadNumber(rs.getString(12));
            e.getAddress().setRoadName(rs.getString(13));
            e.getAddress().setCity(rs.getString(14));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }
    }


