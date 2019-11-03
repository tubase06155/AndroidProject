package com.example.learningenglish.dal;

import com.example.learningenglish.Database.DBContext;
import com.example.learningenglish.Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDAO {
    // query to new user in userController of admin
    public void createUser(String username, String password, String email, int type) throws Exception {
        String query = "INSERT INTO UserTBL VALUES(?,?,?,?,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        // set param
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, email);
        ps.setInt(4, type);
        ps.setInt(5, 0);
        ps.setInt(6, 1);

        ps.executeUpdate();

        ps.close();
        conn.close();

    }

    // register new user
    public void registerUser(String username, String password, String email) throws Exception {
        String query = "INSERT INTO UserTBL VALUES(?,?,?,?,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        // set param
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, email);
        ps.setInt(4, 1);
        ps.setInt(5, 0);
        ps.setInt(6, 1);

        ps.executeUpdate();

        ps.close();
        conn.close();

    }
    // get List of user in db and can paging
    public List<User> getListOfUser(int pageIndex) throws Exception {

        List<User> users = new ArrayList<>();
        String query = "select userID,username,password,email,userType,score,isActive from (\n"
                + "select ROW_NUMBER() over (order by userID ASC) as p, *\n"
                + "from UserTBL  where userType = 1\n"
                + ") as x\n"
                + "where p between (?-1)*10 + 1\n"
                + "and 10*?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, pageIndex);
        ps.setInt(2, pageIndex);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int userID = rs.getInt("userID");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            int userType = rs.getInt("userType");
            int score = rs.getInt("score");
            boolean isActive = rs.getBoolean("isActive");

            users.add(new User(userID, username, password, email, userType, score, isActive));
        }
        ps.close();
        conn.close();
        return users;
    }
    // count all of user
    public int countRows() throws Exception {
        String sql = "select COUNT (*) as Total from UserTBL where userType =1";
        try {
            Connection conn = new DBContext().getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    // get top 10 list of rank of user
    public List<User> getListOfRank() throws Exception {

        List<User> users = new ArrayList<>();
        String query = "SELECT TOP 10 * from UserTBL WHERE isActive = 1 and score > 0 ORDER BY score DESC";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int userID = rs.getInt("userID");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            int userType = rs.getInt("userType");
            int score = rs.getInt("score");
            boolean isActive = rs.getBoolean("isActive");

            users.add(new User(userID, username, password, email, userType, score, isActive));
        }
        ps.close();
        conn.close();
        return users;
    }
    // deActive user by ID
    public void deleteByID(String id) throws Exception {
        String query = "UPDATE UserTBL SET isActive = 0 where userID = " + id;
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.execute();

        ps.close();
        conn.close();
    }
    // reActive the deleted user
    public void reActiveByID(String id) throws Exception {
        String query = "UPDATE UserTBL SET isActive = 1 where userID = " + id;
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.execute();

        ps.close();
        conn.close();
    }

    // check username has existed
    public boolean isUsernameExisted(String username) throws Exception {
        String query = "SELECT * FROM UserTBL WHERE username = '" + username + "'";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    // check valid username and password
    public boolean isValidUsernameAndPassword(String username, String password) throws Exception {
        String query = "SELECT * FROM UserTBL WHERE isActive = 1 AND username = '" + username + "' AND password = '" + password + "'";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    // select user by username to display detail information of user
    public User selectUserByUsername(String usernamex) throws Exception {

        String query = "SELECT * FROM UserTBL WHERE username = '" + usernamex + "'";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        User theUser = null;
        while (rs.next()) {
            int userID = rs.getInt("userID");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            int userType = rs.getInt("userType");
            int score = rs.getInt("score");
            boolean isActive = rs.getBoolean("isActive");

            theUser = new User(userID, username, password, email, userType, score, isActive);
        }
        rs.close();
        ps.close();
        conn.close();

        return theUser;
    }
    // select user by userID
    public User selectUserByUserID(String userIDx) throws Exception {

        String query = "SELECT * FROM UserTBL WHERE userID = " + userIDx;
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        User theUser = null;
        while (rs.next()) {
            int userID = rs.getInt("userID");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            int userType = rs.getInt("userType");
            int score = rs.getInt("score");
            boolean isActive = rs.getBoolean("isActive");

            theUser = new User(userID, username, password, email, userType, score, isActive);
        }
        rs.close();
        ps.close();
        conn.close();

        return theUser;
    }
    // updateScoreByUserID
    public void updateScoreByUserID(int userID, int currentScore, int countRightAnswer) throws Exception {
        String total = String.valueOf(currentScore + countRightAnswer);
        String query = "UPDATE UserTBL SET score = " + total + " WHERE userID = " + String.valueOf(userID);
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public void updateUserInfo(String userID, String email, String type, String score) throws Exception {
        String query = "UPDATE UserTBL SET email='" + email + "', userType=" + type + ",score=" + score + " WHERE userID = " + userID;
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public int getCurrentScoreByUserID(int userID) throws Exception {
        int currentScore = 0;
        String query = "SELECT score FROM UserTBL WHERE userID = " + String.valueOf(userID);
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int score = rs.getInt("score");
            currentScore = score;
        }

        rs.close();
        ps.close();
        conn.close();

        return currentScore;
    }

    public void updatePassword(String newPassword, String userID) throws Exception {
        String query = "UPDATE UserTBL SET password = '" + newPassword + "' WHERE userID = " + userID;
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public void resetPassword(String userID) throws Exception {
        String query = "UPDATE UserTBL SET password='123' WHERE userID = " + userID;
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }
}
