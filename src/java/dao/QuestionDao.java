
package dao;

import entity.Question;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.DBContext;

/**
 * QuestionDao.<br>
 *
 * <pre>
 * Class dùng các câu query để lấy dữ liệu từ database
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 *
 * . getQuestionsByQuizId.
 * . AddListQuestion.
 * . removeQuestionByQuestionId.
 * . removeQuestionByQuizId.
 *
 *
 * </pre>
 *
 */
public class QuestionDao extends DBContext {

    /**
     * getQuestionsByQuizId.<br>
     * Lấy danh sách các câu hỏi thuộc bài quiz truyền vào
     *
     * @param quiz_id
     * @return a List of Question
     */
    public List<Question> getQuestionsByQuizId(String quiz_id) {
        String sql = "SELECT * FROM dbo.Question WHERE quiz_id =?";
        List<Question> listQuestion = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, quiz_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuizId(rs.getString("quiz_id"));
                question.setQuestion(rs.getString("question"));
                question.setAnswer(rs.getString("answer"));
                question.setAnswerCorrect(rs.getString("answer_correct"));
                listQuestion.add(question);
            }
            return listQuestion;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    /**
     * AddListQuestion.<br>
     * Thêm 1 danh sách các câu hỏi
     *
     * @param listQuestion
     * @return a boolean
     */
    public boolean AddListQuestion(List<Question> listQuestion) {

        String sql = "INSERT INTO [dbo].[Question]([quiz_id],[question],[answer],[answer_correct]) VALUES (?,?,?,?)";
        int check[] = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            //thêm các câu query vào Batch
            for (Question question : listQuestion) {
                statement.setString(1, question.getQuizId());
                statement.setString(2, question.getQuestion());
                statement.setString(3, question.getAnswer());
                statement.setString(4, question.getAnswerCorrect());
                statement.addBatch();
            }
            check = statement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            check=new int[0];
            try {
                //Nếu thêm thất bại thì rollback
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuestionDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return check.length > 0;
    }
    /**
     * removeQuestionByQuestionId.<br>
     * Xóa 1 câu hỏi ứng với question id truyền vào
     *
     * @param questionId
     * @return a boolean
     */
    public boolean removeQuestionByQuestionId(int questionId) {
        String sql = "DELETE FROM [dbo].[Question] WHERE id = ?";
        int check = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, questionId);
            check = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }
    /**
     * removeQuestionByQuizId.<br>
     * Xóa các câu hỏi ứng với quiz id truyền vào
     *
     * @param quizId
     * @return a boolean
     */
    public boolean removeQuestionByQuizId(String quizId) {
        String sql = "DELETE FROM [dbo].[Question] WHERE quiz_id = ?";
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
