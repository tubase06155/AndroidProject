package com.example.learningenglish.Database;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private static final String LOG = "DEBUG";
    private static String ip = "192.168.15.104";//Dorm 10.21.11.135

    private static String port = "1433";
    private static String classs = "net.sourceforge.jtds.jdbc.Driver";
    private static String db = "FunEnglish";
    private static String un = "abc";
    private static String password = "123456";
    @SuppressLint("NewApi")
    public Connection getConnection(){

        Connection conn = null;
        String ConnURL = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(classs);

            ConnURL = "jdbc:jtds:sqlserver://" + ip +":"+port+";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);

        } catch (SQLException e) {
            Log.d(LOG, "SQLException");
            Log.d(LOG, e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.d(LOG, "ClassNotFoundException");
            Log.d(LOG, e.getMessage());
        }
        return conn;
    }

}
