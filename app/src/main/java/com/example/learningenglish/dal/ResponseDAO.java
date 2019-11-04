package com.example.learningenglish.dal;

import com.example.learningenglish.Database.DBContext;
import com.example.learningenglish.Entity.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResponseDAO {
    public void addResponse(String name, String title, String content, int userID, boolean isActive, String email) throws Exception {
        String query = "INSERT INTO User_Response VALUES(?,?,?,?,?,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        // set param
        ps.setString(1, null);
        ps.setString(2, name);
        ps.setString(3, title);
        ps.setString(4, "");
        ps.setBoolean(5, isActive);
        ps.setString(6, email);
        ps.setString(7, content);

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public int countRows() throws Exception {
        String sql = "select count(*) as Total from User_Response where isActive = 1";
        try {
            Connection conn = new DBContext().getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResponseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    // list response
    public List<Response> listAllResponse() throws Exception {
        List<Response> responses = new ArrayList<>();
        String query = "select * from User_Response where isActive = 1 ORDER BY responseID DESC";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("responseID");
            String name = rs.getString("username");
            String title = rs.getString("title");
            String content = rs.getString("content2");
            String email = rs.getString("email");
            boolean isActive = rs.getBoolean("isActive");

            responses.add(new Response(id, name, email, title, content, isActive));
        }
        ps.close();
        conn.close();
        return responses;

    }

    // delete response
    public void deleteByID(String id) throws Exception {
        String query = "UPDATE User_Response SET isActive = 0 WHERE responseID = " + id;
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }
}
