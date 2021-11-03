
package dao;

import entity.Question;
import entity.Quiz;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.DBContext;

/**
 * QuizDao.<br>
 *
 * <pre>
 * Class dùng các câu query để lấy dữ liệu từ database
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 *
 * . getQuiz.
 *
 *
 * </pre>
 *
 */
public class QuizDao extends DBContext {

    /**
     * getQuiz.<br>
     * Lấy ra một bài quiz ứng với số câu hỏi và quiz id truyền vào
     *
     * @param numOfQuiz
     * @param quiz_id
     * @return a Quiz
     */
    public Quiz getQuiz(int numOfQuiz, String quiz_id) {
        StringBuilder sql = new StringBuilder("SELECT TOP(?) Question.*,name,creator,createdDate FROM dbo.Quiz INNER JOIN dbo.Question ON Question.quiz_id = Quiz.id ");
        sql.append("WHERE quiz_id = ? ORDER BY NEWID()");
        Quiz quiz = null;
        List<Question> listQuestion = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            statement.setInt(1, numOfQuiz);
            statement.setString(2, quiz_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (quiz == null) {
                    quiz = new Quiz();
                    quiz.setId(rs.getString("quiz_id"));
                    quiz.setName(rs.getString("name"));
                    quiz.setCreator(rs.getString("creator"));
                    quiz.setCreatedDate(rs.getDate("createdDate"));
                }
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuizId(rs.getString("quiz_id"));
                question.setQuestion(rs.getString("question"));
                question.setAnswer(rs.getString("answer"));
                question.setAnswerCorrect(rs.getString("answer_correct"));
                listQuestion.add(question);
            }
            if (quiz != null) {
                quiz.setListQuestion(listQuestion);
            }
            return quiz;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    /**
     * getQuizById.<br>
     * Lấy ra một bài quiz ứng với quiz id truyền vào
     *
     * @param id
     * @return a Quiz
     */
    public Quiz getQuizById(String id) {
        String sql = "SELECT * FROM dbo.Quiz WHERE id =?";
        List<Quiz> listQuiz = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getString("id"));
                quiz.setName(rs.getString("name"));
                quiz.setCreator(rs.getString("creator"));
                quiz.setCreatedDate(rs.getDate("createdDate"));
                return quiz;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    
    /**
     * getAllQuizByCreator.<br>
     * Lấy ra tất cả bài quiz ứng với người tạo truyền vào
     *
     * @param creator
     * @return a Quiz
     */
    public List<Quiz> getAllQuizByCreator(String creator) {
        String sql = "SELECT * FROM dbo.Quiz WHERE creator =?";
        List<Quiz> listQuiz = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, creator);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getString("id"));
                quiz.setName(rs.getString("name"));
                quiz.setCreator(rs.getString("creator"));
                quiz.setCreatedDate(rs.getDate("createdDate"));
                listQuiz.add(quiz);
            }
            return listQuiz;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    /**
     * AddQuiz.<br>
     * Thêm một bài quiz
     *
     * @param quizId
     * @param name
     * @param creator
     * @return a boolean
     */
    public boolean AddQuiz(String quizId, String name, String creator) {
        String sql = "INSERT INTO [dbo].[Quiz] ([id],[name],[creator]) VALUES (?,?,?)";
        int check = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, quizId);
            statement.setString(2, name);
            statement.setString(3, creator);
            check = statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }
    /**
     * AddQuiz.<br>
     * Xóa một bài quiz ứng với quiz id truyền vào
     *
     * @param quizId
     * @return a boolean
     */
    public boolean removeQuiz(String quizId) {
        String sql = "DELETE FROM [dbo].[Quiz] WHERE id = ?";
        int check = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, quizId);
            check = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }

}
