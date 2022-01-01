package dao;

import config.Config;
import error.NotFoundException;
import models.Address;
import models.Employee;
import models.Student;
import services.EmployeeService;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


public class EmployeeDao {


    private EmployeeDao() {
    }

    // instance to return when any client asks for it.
    private static EmployeeDao instance = new EmployeeDao();


    public static EmployeeDao getInstance() {
        return instance;
    }


    public List<Employee> findAll() throws Exception {
        List<Employee> result = new LinkedList<>();

        String sql = "SELECT * FROM employee e, address a WHERE e.address_id = a.id";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(); // execute the database insert query

            while (rs.next() == true) {
                Employee e = new Employee();
                Address a = new Address();
                e.setId(rs.getInt(1));
                e.setFirstName(rs.getString(2));
                e.setLastName(rs.getString(3));
                e.setFatherName(rs.getString(4));
                Date d = rs.getDate(5);
                e.setBirthday(d.toLocalDate());
                e.setImageUrl(rs.getString(6));
                e.setCinNumber(rs.getString(7));
                e.setPhoneNumber(rs.getString(8));
                a.setId(rs.getInt(10));
                a.setPostalCode(rs.getInt(11));
                a.setRoadNumber(rs.getString(12));
                a.setRoadName(rs.getString(13));
                a.setCity(rs.getString(14));
                e.setAddress(a);
                result.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public Employee findById(int id) {
        Employee e = new Employee();
        String sql = "SELECT * FROM employee e inner join address a on e.address_id = a.id where e.id = ? ";
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
            // get student fields from the current result set exp:
            e.setFirstName(rs.getString(2));
            e.setLastName(rs.getString(3));
            e.setFatherName(rs.getString(4));
            Date d = rs.getDate(5);
            e.setBirthday(d.toLocalDate());
            e.setImageUrl(rs.getString(6));
            e.setCinNumber(rs.getString(7));
            e.setPhoneNumber(rs.getString(8));
            e.setAddress(new Address());
            e.getAddress().setId(rs.getInt(9));
            e.getAddress().setPostalCode(rs.getInt(10));
            e.getAddress().setRoadNumber(rs.getString(11));
            e.getAddress().setRoadName(rs.getString(12));
            e.getAddress().setCity(rs.getString(13));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }


    public Employee save(Employee e) {
        AddressDao addressDao = AddressDao.getInstance();
        Address savedAddress = addressDao.save(e.getAddress());

        String sql = "INSERT INTO employee (first_name, last_name, father_name ,birthday, image_url, " +
                " cin_number, phone_number, address_id) VALUES (?,?,?,?,?,?,?,?)";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, e.getFirstName());
            stmt.setString(2, e.getLastName());
            stmt.setString(3, e.getFatherName());
            stmt.setDate(4, Date.valueOf(e.getBirthday()));
            stmt.setString(5, e.getImageUrl());
            stmt.setString(6, e.getCinNumber());
            stmt.setString(7, e.getPhoneNumber());
            stmt.setInt(8, savedAddress.getId());

            stmt.executeUpdate(); // execute the database insert query
            ResultSet rs = stmt.getGeneratedKeys(); // database returns

            rs.next();
            int generatedEmployeeId = rs.getInt(1);
            e.setId(generatedEmployeeId);
            return e;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean deleteById(int id) {
        Employee e = findById(id);
        String sql = "DELETE FROM employee WHERE id = ? ";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            AddressDao addressDao = AddressDao.getInstance();
            addressDao.deleteById(e.getAddress().getId());
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }



    public Employee update(Employee e) {
        AddressDao addressDao = AddressDao.getInstance();
        addressDao.update(e.getAddress());

        String sql = "UPDATE employee SET first_name=?, last_name=?, father_name=? ,birthday=?, image_url=?, cin_number=?,phone_number=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, e.getFirstName());
            stmt.setString(2, e.getLastName());
            stmt.setString(3, e.getFatherName());
            stmt.setDate(4, Date.valueOf(e.getBirthday()));
            stmt.setString(5, e.getImageUrl());
            stmt.setString(6, e.getCinNumber());
            stmt.setString(7, e.getPhoneNumber());
            stmt.setInt(8, e.getId());
            stmt.executeUpdate();
            return findById(e.getId());
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}


