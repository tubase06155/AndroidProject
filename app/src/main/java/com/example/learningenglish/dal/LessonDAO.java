package com.example.learningenglish.dal;

import com.example.learningenglish.Database.DBContext;
import com.example.learningenglish.Entity.Lesson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LessonDAO {
    public List<Lesson> listLesson(int pageIndex) throws Exception {
        String query = "select  lessonID,title,imagePath,category,content,difficulty,audioPath,isActive from (\n"
                + "select ROW_NUMBER() over (order by lessonID desc ) as rn, *\n"
                + "from lesson where isActive = 1\n"
                + ") as x\n"
                + "where rn between (?-1)*10 + 1\n"
                + "and 10*?";
        Connection conn = new DBContext().getConnection();

        PreparedStatement ps = conn.prepareStatement(query);
        List<Lesson> lessons = new ArrayList<>();
        ps.setInt(1, pageIndex);
        ps.setInt(2, pageIndex);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            String title = rs.getString(2);
            String imagePath = rs.getString(3);
            int category = rs.getInt(4);
            String content = rs.getString(5);
            int difficulty = rs.getInt(6);
            String audioPath = rs.getString(7);

            Lesson lesson = new Lesson(id, title, imagePath, category, content, difficulty, audioPath);
            lessons.add(lesson);
        }
        rs.close();
        ps.close();
        conn.close();
        return lessons;

    }

    public int countRows() throws Exception {
        String sql = "select count(*) as Total from Lesson where isActive = 1";
        try {
            Connection conn = new DBContext().getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countSearchRows(String searchText) throws Exception {
        String sql = "select COUNT(*) as total  from Lesson where isActive = 1 and \n"
                + " title like '%" + searchText
                + "%'";
        try {
            Connection conn = new DBContext().getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countCategoryRows(String searchText) throws Exception {
        String sql = "select COUNT(*) as total  from Lesson where isActive = 1 and \n"
                + " category = " + searchText;

        try {
            Connection conn = new DBContext().getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Lesson> listLessonByCategory(String categoryx) throws Exception {
        String query = "select * from Lesson where isActive = 1 and category = " + categoryx +
                " order by lessonID desc";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        List<Lesson> lessons = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            String title = rs.getString(2);
            String imagePath = rs.getString(3);
            int category = rs.getInt(4);
            String content = rs.getString(5);
            int difficulty = rs.getInt(6);
            String audioPath = rs.getString(7);

            Lesson lesson = new Lesson(id, title, imagePath, category, content, difficulty, audioPath);
            lessons.add(lesson);
        }
        rs.close();
        ps.close();
        conn.close();
        return lessons;
    }
    public List<Lesson> listAllLesson() throws Exception {
        String query = "select * from Lesson where isActive = 1 and category = 1 or category = 3 order by lessonID desc";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        List<Lesson> lessons = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            String title = rs.getString(2);
            String imagePath = rs.getString(3);
            int category = rs.getInt(4);
            String content = rs.getString(5);
            int difficulty = rs.getInt(6);
            String audioPath = rs.getString(7);

            Lesson lesson = new Lesson(id, title, imagePath, category, content, difficulty, audioPath);
            lessons.add(lesson);
        }
        rs.close();
        ps.close();
        conn.close();
        return lessons;
    }

    // IndexServlet
    public List<Lesson> getInitLesson() throws Exception {
        String query = "SELECT TOP 9 * FROM Lesson WHERE isActive = 1 ORDER BY LessonID DESC ";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        List<Lesson> lessons = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            // collect data
            int id = rs.getInt("lessonID");
            String title = rs.getString("title");
            String imagePath = rs.getString("imagePath");
            int category = rs.getInt("category");
            String content = rs.getString("content");
            int difficulty = rs.getInt("difficulty");
            String audioPath = rs.getString("audioPath");

            Lesson lesson = new Lesson(id, title, imagePath, category, content, difficulty, audioPath);
            lessons.add(lesson);
        }
        rs.close();
        ps.close();
        conn.close();
        return lessons;
    }

    public Lesson getLessonByID(String lessonID) throws Exception {
        String query = "SELECT * FROM Lesson WHERE lessonID = " + lessonID;
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        Lesson lesson = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("lessonID");
            String title = rs.getString("title");
            String imagePath = rs.getString("imagePath");
            int category = rs.getInt("category");
            String content = rs.getString("content");
            int difficulty = rs.getInt("difficulty");
            String audioPath = rs.getString("audioPath");

            lesson = new Lesson(id, title, imagePath, category, content, difficulty, audioPath);
        }

        rs.close();
        ps.close();
        conn.close();
        return lesson;
    }

    public List<Lesson> searchByName(String searchText, int pageIndex) throws Exception {
        String query = "select  * from (\n"
                + "select ROW_NUMBER() over (order by lessonID desc) as rn, *\n"
                + "from Lesson where isActive = 1 and title like '%" + searchText
                + "%'\n"
                + ") as x\n"
                + "where rn between (?-1)*9 + 1\n"
                + "and 9*?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        //  ps.setString(1, searchText);
        ps.setInt(1, pageIndex);
        ps.setInt(2, pageIndex);
        List<Lesson> lessons = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            // collect data
            int id = rs.getInt("lessonID");
            String title = rs.getString("title");
            String imagePath = rs.getString("imagePath");
            int category = rs.getInt("category");
            String content = rs.getString("content");
            int difficulty = rs.getInt("difficulty");
            String audioPath = rs.getString("audioPath");

            Lesson lesson = new Lesson(id, title, imagePath, category, content, difficulty, audioPath);
            lessons.add(lesson);
        }
        rs.close();
        ps.close();
        conn.close();
        return lessons;
    }

    public List<Lesson> sortByName(String categoryx) throws Exception {
        String query
                = "SELECT * FROM Lesson WHERE isActive = 1 AND category = " + categoryx + " ORDER BY title ";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        List<Lesson> lessons = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            // collect data
            int id = rs.getInt("lessonID");
            String title = rs.getString("title");
            String imagePath = rs.getString("imagePath");
            int category = rs.getInt("category");
            String content = rs.getString("content");
            int difficulty = rs.getInt("difficulty");
            String audioPath = rs.getString("audioPath");

            Lesson lesson = new Lesson(id, title, imagePath, category, content, difficulty, audioPath);
            lessons.add(lesson);
        }
        rs.close();
        ps.close();
        conn.close();
        return lessons;
    }

    public List<Lesson> sortByDifficulty(String categoryx) throws Exception {
        String query = "SELECT * FROM Lesson WHERE isActive = 1 AND category = " + categoryx + " ORDER BY difficulty";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        List<Lesson> lessons = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            // collect data
            int id = rs.getInt("lessonID");
            String title = rs.getString("title");
            String imagePath = rs.getString("imagePath");
            int category = rs.getInt("category");
            String content = rs.getString("content");
            int difficulty = rs.getInt("difficulty");
            String audioPath = rs.getString("audioPath");

            Lesson lesson = new Lesson(id, title, imagePath, category, content, difficulty, audioPath);
            lessons.add(lesson);
        }
        rs.close();
        ps.close();
        conn.close();
        return lessons;
    }

    public void addQuestion(int lessonID, String content, String opt1, String opt2, String opt3, String opt4, int rightOpt) throws Exception {
        String query = "INSERT INTO Question VALUES(?,?,?,?,?,?,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, lessonID);
        ps.setString(2, content);
        ps.setString(3, opt1);
        ps.setString(4, opt2);
        ps.setString(5, opt3);
        ps.setString(6, opt4);
        ps.setInt(7, rightOpt);
        ps.setBoolean(8, true);

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public void deleteByID(String id) throws Exception {
        String query = "UPDATE Lesson SET isActive = 0 where lessonID = " + id;
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.execute();

        ps.close();
        conn.close();
    }

    public void addLesson(String title, String imageName, int category, String content, int difficulty, String audioName) throws Exception {
        String query = "INSERT INTO Lesson VALUES(?,?,?,?,?,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        // set param
        ps.setString(1, title);
        ps.setString(2, imageName);
        ps.setInt(3, category);
        ps.setString(4, content);
        ps.setInt(5, difficulty);
        ps.setString(6, audioName);
        ps.setBoolean(7, true);

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public void updateLesson(String title, int category, String content, int difficulty, int lessonID) throws Exception {
        String query = "update Lesson set title = ?,category = ?, difficulty = ? , content = ? where lessonID = ? ";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, title);
        ps.setInt(2, category);
        ps.setInt(3, difficulty);
        ps.setString(4, content);
        ps.setInt(5, lessonID);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }
}
