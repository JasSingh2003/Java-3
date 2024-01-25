package com.example.assignment3_jaskeeratsingh.Database;

import com.example.assignment3_jaskeeratsingh.beans.User;
import com.example.assignment3_jaskeeratsingh.beans.Watch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {
    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    @Lazy
    protected NamedParameterJdbcTemplate jdbc;

    public void addUser(String email, String password) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO users "
                + "(email, encryptedPassword, enabled) "
                + "VALUES (:email, :encryptedPassword, 1)";
        namedParameters.addValue("email", email);
        namedParameters.addValue("encryptedPassword",
                passwordEncoder.encode(password));
        jdbc.update(query, namedParameters);
    }
        public List<Watch> getcartList() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM cart ";
        return jdbc.query(query, namedParameters, new
                BeanPropertyRowMapper<Watch>(Watch.class));
    }
    public List<Watch> getcartList(String brand) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM cart WHERE brand = :brand";
        namedParameters.addValue("brand", brand);
        return jdbc.query(query, namedParameters, new
                BeanPropertyRowMapper<Watch>(Watch.class));
    }


    public List<String> getRolesById(Long userId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT sec_role.roleName "
                + "FROM user_role, sec_role "
                + "WHERE user_role.roleId = sec_role.roleId "
                + "AND userId = :userId";
        namedParameters.addValue("userId", userId);
        return jdbc.queryForList(query, namedParameters, String.class);
    }


    public List<Watch> getwatchList() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM watches ";
        return jdbc.query(query, namedParameters, new
                BeanPropertyRowMapper<Watch>(Watch.class));
    }

    public void addRole(Long userId, Long roleId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO user_role (userId, roleId) "
                + "VALUES (:userId, :roleId)";
        namedParameters.addValue("userId", userId);
        namedParameters.addValue("roleId", roleId);
        jdbc.update(query, namedParameters);
    }

    public void insertWatch(String brand,double price, double rating) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO cart(brand,price,rating) VALUES ('" + brand +  "' ,'"+ price + "' ,'"+  rating +"')";
        int rowsAffected = jdbc.update(query, namedParameters);
        if (rowsAffected > 0)
            System.out.println("Hard coded book inserted into the database");
    }
    public void insertWatchAdmin(int watchId,String brand,double price,int stockQuantity, String description, double rating) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO watches(watchId,brand,price,stockQuantity,description,rating) VALUES (" + watchId +  " ,'" + brand +  "' ,"+ price + " ," + stockQuantity +  " ,'" + description +  "', "+  rating +")";
        int rowsAffected = jdbc.update(query, namedParameters);
        if (rowsAffected > 0)
            System.out.println("Hard coded book inserted into the database");
    }
    public void deleteWatchbyBrand(String brand) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "DELETE FROM cart WHERE brand = :brand";
        namedParameters.addValue("brand", brand);
        if (jdbc.update(query, namedParameters) > 0) {
            System.out.println("Deleted Book " + brand + " from the database.");
        }
    }

    public User findUserAccount(String email) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM users where email = :email";
        namedParameters.addValue("email", email);
        try {
            return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<User>(User.class));
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }

}
