package dao;

import config.Config;
import models.Address;

import java.sql.*;

public class AddressDao {

    private AddressDao(){
    }

    private static final AddressDao instance = new AddressDao();

    public static AddressDao getInstance(){
        return instance;
    }

    public Address save(Address a){
        String sql = "INSERT INTO address (postal_code, road_number, road_name ,city " +
                ") VALUES (?,?,?,?)";
        try(
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setInt(1, a.getPostalCode());
            stmt.setString(2, a.getRoadName());
            stmt.setString(3, a.getRoadName());
            stmt.setString(4, a.getCity());

            stmt.executeUpdate(); // execute the database insert query
            ResultSet rs = stmt.getGeneratedKeys(); // database returns

            rs.next();
            int generatedStudentId = rs.getInt(1);
            a.setId(generatedStudentId);
            return a;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM address WHERE id = ? ";
        try (
                Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Address update(Address a) {
        String sql = "UPDATE address SET postal_code = ?, road_number = ?, road_name = ? , city = ? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, a.getPostalCode());
            stmt.setString(2, a.getRoadName());
            stmt.setString(3, a.getRoadName());
            stmt.setString(4, a.getCity());
            stmt.executeUpdate();
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
