package com.example.learningenglish;

import android.util.Log;

import com.example.learningenglish.Database.DBContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO  {
    DBContext db = new DBContext();
    Connection con =db.getConnection();
    String z = "";
    public  String Login(String username, String password){
        try {
            if (con == null){
                z = "error";
            } else {
                String query = " select * from UserTBL where username = '" + username + "' and password = '" + password+"'";
                Statement stmt = con.createStatement();
                Log.d("DEBUG", query);
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    z = "Dang nhap thanh cong";

                } else {
                    z = "failed";
                }

            }
        } catch (SQLException e) {
           z = "exception";
        }
        return z;
    }


}
