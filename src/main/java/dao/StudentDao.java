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


public class StudentDao {

    // ***************************
    // singleton design pattern

    // private constructor so that client code is forced to use the getInstance() method to create instance (new StudentDao() will not be accepted)
    private StudentDao() {
    }

    // instance to return when any client asks for it.
    private static final StudentDao instance = new StudentDao();

    // the only possible way for a client code to get the instance. (exp. see how StudentService asks for an instance of StudentDao using the getInstance())
    public static StudentDao getInstance() {
        return instance;
    }
    // ***************************


    public List<Student> findAll() throws Exception {
        List<Student> result = new LinkedList<>();

        String sql = "SELECT * FROM student s, address a WHERE s.address_id = a.id";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(); // execute the database insert query

            while (rs.next() == true) {
                // create new empty student entity
                Student s = new Student();
                Address a = new Address();
                s.setId(rs.getInt(1));
                // get student fields from the current result set exp:
                s.setFirstName(rs.getString(2));
                s.setLastName(rs.getString(3));
                s.setFatherName(rs.getString(4));
                Date d = rs.getDate(5);
                s.setBirthday(d.toLocalDate());
                s.setImageUrl(rs.getString(6));
                s.setMotherName(rs.getString(7));
                s.setGrandFatherName(rs.getString(8));
                s.setFatherCin(rs.getString(9));
                s.setFatherPhoneNumber(rs.getString(10));
                s.setGroup(new Group());
                s.getGroup().setName(rs.getString(12));
                a.setId(rs.getInt(13));
                a.setPostalCode(rs.getInt(14));
                a.setRoadNumber(rs.getString(15));
                a.setRoadName(rs.getString(16));
                a.setCity(rs.getString(17));
                // then add the new created student to the list of student s  like follows::
                s.setAddress(a);
                result.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public Student findById(int id) {
        Student s = new Student();
        String sql = "SELECT * FROM `student` s inner join address a on s.address_id = a.id where s.id = ? ";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            boolean foundSomething = rs.next();
            if (!foundSomething) {
                throw new NotFoundException();
            }

            s.setId(rs.getInt(1));
            // get student fields from the current result set exp:
            s.setFirstName(rs.getString(2));
            s.setLastName(rs.getString(3));
            s.setFatherName(rs.getString(4));
            Date d = rs.getDate(5);
            s.setBirthday(d.toLocalDate());
            s.setImageUrl(rs.getString(6));
            s.setMotherName(rs.getString(7));
            s.setGrandFatherName(rs.getString(8));
            s.setFatherCin(rs.getString(9));
            s.setFatherPhoneNumber(rs.getString(10));
            s.setGroup(new Group());
            s.getGroup().setName(rs.getString(12));
            s.setAddress(new Address());
            s.getAddress().setId(rs.getInt(13));
            s.getAddress().setPostalCode(rs.getInt(14));
            s.getAddress().setRoadNumber(rs.getString(15));
            s.getAddress().setRoadName(rs.getString(16));
            s.getAddress().setCity(rs.getString(17));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public Student save(Student s) {
        AddressDao addressDao = AddressDao.getInstance();
        Address savedAddress = addressDao.save(s.getAddress());

        String sql = "INSERT INTO student (first_name, last_name, father_name ,birthday, image_url, " +
                "mother_name, grand_father_name, father_cin, father_phone_number, address_id, group_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, s.getFirstName());
            stmt.setString(2, s.getLastName());
            stmt.setString(3, s.getFatherName());
            stmt.setDate(4, Date.valueOf(s.getBirthday()));
            stmt.setString(5, s.getImageUrl());
            stmt.setString(6, s.getMotherName());
            stmt.setString(7, s.getGrandFatherName());
            stmt.setString(8, s.getFatherCin());
            stmt.setString(9, s.getFatherPhoneNumber());
            stmt.setInt(10, savedAddress.getId());
            stmt.setString(11, s.getGroup().getName());

            stmt.executeUpdate(); // execute the database insert query
            ResultSet rs = stmt.getGeneratedKeys(); // database returns

            rs.next();
            int generatedStudentId = rs.getInt(1);
            s.setId(generatedStudentId);
            return s;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean deleteById(int id) {
        Student s = findById(id);
        String sql = "DELETE FROM student WHERE id = ? ";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            AddressDao addressDao = AddressDao.getInstance();
            addressDao.deleteById(s.getAddress().getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Student update(Student s) {
        AddressDao addressDao = AddressDao.getInstance();
        addressDao.update(s.getAddress());

        String sql = "UPDATE student SET first_name=?, last_name=?, father_name=? ,birthday=?, image_url=?,mother_name=?, grand_father_name=?, father_cin=?, father_phone_number=? , group_id =? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, s.getFirstName());
            stmt.setString(2, s.getLastName());
            stmt.setString(3, s.getFatherName());
            stmt.setDate(4, Date.valueOf(s.getBirthday()));
            stmt.setString(5, s.getImageUrl());
            stmt.setString(6, s.getMotherName());
            stmt.setString(7, s.getGrandFatherName());
            stmt.setString(8, s.getFatherCin());
            stmt.setString(9, s.getFatherPhoneNumber());
            stmt.setString(10, s.getGroup().getName());
            stmt.setInt(11, s.getId());
            stmt.executeUpdate();
            return findById(s.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
