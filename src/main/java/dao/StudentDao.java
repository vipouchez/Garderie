package dao;

import config.Config;
import error.NotFoundException;
import models.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static config.Config.DB_USER;


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



    public List<Student> findAll() throws Exception{
        List<Student> result = new LinkedList<>();

        String sql = "SELECT * FROM student";
        try(
            Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery(); // execute the database insert query

            while ( rs.next() == true){
                // create new empty student entity
                Student s = new Student();
                s.setId(rs.getInt(1));
                // get student fields from the current result set exp:
                s.setFirstName(rs.getString(2));
                s.setLastName(rs.getString(3));
                s.setFatherName(rs.getString(4));
                Date d = rs.getDate(5);
                s.setBirthday(LocalDate.of(d.getYear(), d.getMonth(), d.getDay()));
                s.setImageUrl(rs.getString(6));
                s.setMotherName(rs.getString(7));
                s.setGrandFatherName(rs.getString(8));
                s.setFatherCin(rs.getString(9));
                s.setFatherPhoneNumber(rs.getString(10));
                // then add the new created student to the list of student s  like follows::
                result.add(s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }


    public Student findById(int id) {
        Student s = new Student();
        String sql = "SELECT * FROM student WHERE id = ? ";
        try(
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            boolean foundSomething = rs.next();
            if(!foundSomething){
                throw new NotFoundException();
            }

            s.setId(rs.getInt(1));
            // get student fields from the current result set exp:
            s.setFirstName(rs.getString(2));
            s.setLastName(rs.getString(3));
            s.setFatherName(rs.getString(4));
            Date d = rs.getDate(5);
            s.setBirthday(LocalDate.of(d.getYear(), d.getMonth(), d.getDay()));
            s.setImageUrl(rs.getString(6));
            s.setMotherName(rs.getString(7));
            s.setGrandFatherName(rs.getString(8));
            s.setFatherCin(rs.getString(9));
            s.setFatherPhoneNumber(rs.getString(10));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public Student save(Student s){
        String sql = "INSERT INTO student (first_name, last_name, father_name ,birthday, image_url, " +
                "mother_name, grand_father_name, father_cin, father_phone_number) VALUES (?,?,?,?,?,?,?,?,?)";
        try(
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, s.getFirstName());
            stmt.setString(2, s.getLastName());
            stmt.setString(3, s.getFatherName());
            // todo  stmt.setString(4, s.getAddress());
            stmt.setDate(4, Date.valueOf(s.getBirthday()));
            stmt.setString(5, s.getImageUrl());
            stmt.setString(6, s.getMotherName());
            stmt.setString(7, s.getGrandFatherName());
            stmt.setString(8, s.getFatherCin());
            stmt.setString(9, s.getFatherPhoneNumber());


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

            public boolean deleteById(int id){
                String sql ="DELETE FROM student WHERE id = ? ";
                try(
                        Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                    PreparedStatement stmt = conn.prepareStatement(sql);)
                {
                    stmt.setInt(1, id);
                    return stmt.executeUpdate() == 1;
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }


            public Student update(Student s){
                String sql ="UPDATE student SET first_name=?, last_name=?, father_name=? ,birthday=?, image_url=?,mother_name=?, grand_father_name=?, father_cin=?, father_phone_number=? WHERE id=?";
                try(Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                    PreparedStatement stmt = conn.prepareStatement(sql);)
                {
                    stmt.setString(1,s.getFirstName());
                    stmt.setString(2,s.getLastName());
                    stmt.setString(3,s.getFatherName());
                    stmt.setDate(4,Date.valueOf(s.getBirthday()));
                    stmt.setString(5,s.getImageUrl());
                    stmt.setString(6,s.getMotherName());
                    stmt.setString(7,s.getGrandFatherName());
                    stmt.setString(8,s.getFatherCin());
                    stmt.setString(9,s.getFatherPhoneNumber());
                    stmt.setInt(10,s.getId());
                    stmt.executeUpdate();
                    return  findById(s.getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }

}
