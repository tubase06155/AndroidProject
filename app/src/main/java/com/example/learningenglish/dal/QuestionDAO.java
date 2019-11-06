package com.example.learningenglish.dal;

import com.example.learningenglish.Database.DBContext;
import com.example.learningenglish.Entity.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuestionDAO {
    public List<Question> listQuestionByLessonID(String lessonIDx) throws Exception {

        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM Question WHERE isActive=1 AND lessonID=" + lessonIDx;
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int questionID = rs.getInt("questionID");
            int lessonID = rs.getInt("lessonID");
            String content = rs.getString("questionContent");
            String opt1 = rs.getString("opt1");
            String opt2 = rs.getString("opt2");
            String opt3 = rs.getString("opt3");
            String opt4 = rs.getString("opt4");
            int rightOpt = rs.getInt("rightOption");

            Question question = new Question(questionID, lessonID, content, opt1, opt2, opt3, opt4, rightOpt);

            questions.add(question);
        }
        rs.close();
        ps.close();
        conn.close();

        return questions;
    }

    public Question getQuestionByID(String id) throws Exception {

        Question theQuestion = null;
        String query = "SELECT * FROM Question WHERE isActive=1 AND questionID=" + id;
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int questionID = rs.getInt("questionID");
            int lessonID = rs.getInt("lessonID");
            String content = rs.getString("questionContent");
            String opt1 = rs.getString("opt1");
            String opt2 = rs.getString("opt2");
            String opt3 = rs.getString("opt3");
            String opt4 = rs.getString("opt4");
            int rightOpt = rs.getInt("rightOption");

            theQuestion = new Question(questionID, lessonID, content, opt1, opt2, opt3, opt4, rightOpt);

        }
        rs.close();
        ps.close();
        conn.close();

        return theQuestion;
    }

    public int countRows() throws Exception {
        String sql = "select count(*) as Total from Question where isActive = 1";
        try {
            Connection conn = new DBContext().getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public List<Question> listQuestion() throws Exception {

        List<Question> questions = new ArrayList<>();
        String query = "select a.questionID, b.lessonID,a.questionContent,a.opt1,a.opt2,a.opt3,a.opt4,a.rightOption,a.isActive \n" +
                "from Question a inner join Lesson b on a.lessonID = b.lessonID \n" +
                "where b.isActive = 1 and a.isActive = 1 order by b.lessonID desc";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int questionID = rs.getInt("questionID");
            int lessonID = rs.getInt("lessonID");
            String content = rs.getString("questionContent");
            String opt1 = rs.getString("opt1");
            String opt2 = rs.getString("opt2");
            String opt3 = rs.getString("opt3");
            String opt4 = rs.getString("opt4");
            int rightOpt = rs.getInt("rightOption");

            Question question = new Question(questionID, lessonID, content, opt1, opt2, opt3, opt4, rightOpt);

            questions.add(question);
        }
        rs.close();
        ps.close();
        conn.close();

        return questions;
    }

    public void deleteByID(String id) throws Exception {
        String query = "UPDATE  Question SET isActive = 0 where questionID = " + id;
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.execute();

        ps.close();
        conn.close();
    }

    public void updateQuestion(String id, String content, String opt1, String opt2, String opt3, String opt4,String rightOption) throws Exception {
        String query = "UPDATE Question SET questionContent = '" + content + "', opt1 = '" + opt1 + "', opt2 = '" + opt2 + "', opt3 = '" + opt3 + "', opt4 = '" + opt4 + "', rightOption = " + rightOption
                + "  WHERE questionID = " + id;
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate();

        ps.close();
        conn.close();

    }
}
