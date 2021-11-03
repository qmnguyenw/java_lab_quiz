
package entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Question.<br>
 *
 * <pre>
 *Class mô tả đối tượng Question
 *Trong class này sẽ tiến hành các xử lí dưới đây
 *
 * . GetId.
 * . SetId.
 * . GetQuizId.
 * . SetQuizId.
 * . GetQuestion.
 * . SetQuestion.
 * . GetAnswer.
 * . SetAnswer.
 * . GetYourAnswer.
 * . SetYourAnswer.
 * . GetAnswerCorrect.
 * . SetAnswerCorrect.
 *
 * </pre>
 *
 */
public class Question implements Serializable {

    /**
     * Store id.
     */
    private int id;
    /**
     * Store quizId.
     */
    private String quizId;
    /**
     * Store question.
     */
    private String question;
    /**
     * Store answer.
     */
    private String answer;
    /**
     * Store yourAnswer.
     */
    private String yourAnswer;
    /**
     * Store answerCorrect.
     */
    private String answerCorrect;

    /**
     * Constructor.<br>
     */
    public Question() {
    }

    /**
     * Constructor full parameter<br>
     *
     * @param id the id
     * @param quizId the quiz id
     * @param question the question
     * @param answerCorrect the answer correct
     * @param answer the answer
     * @param yourAnswer your answer
     */
    public Question(int id, String quizId, String question, String answer, String yourAnswer, String answerCorrect) {
        this.id = id;
        this.quizId = quizId;
        this.question = question;
        this.answer = answer;
        this.yourAnswer = yourAnswer;
        this.answerCorrect = answerCorrect;
    }

    /**
     * Get id.<br>
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Set id.<br>
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get question.<br>
     *
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Set question.<br>
     *
     * @param question the question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Get answer.<br>
     *
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Set answer.<br>
     *
     * @param answer the answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Get answerCorrect.<br>
     *
     * @return the answerCorrect
     */
    public String getAnswerCorrect() {
        return answerCorrect;
    }

    /**
     * Set answerCorrect.<br>
     *
     * @param answerCorrect the answerCorrect
     */
    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    /**
     * Get yourAnswer.<br>
     *
     * @return the yourAnswer
     */
    public String getYourAnswer() {
        return yourAnswer;
    }

    /**
     * Set yourAnswer.<br>
     *
     * @param yourAnswer the yourAnswer
     */
    public void setYourAnswer(String yourAnswer) {
        this.yourAnswer = yourAnswer;
    }

    /**
     * Get quizId.<br>
     *
     * @return the quizId
     */
    public String getQuizId() {
        return quizId;
    }

    /**
     * Set quizId.<br>
     *
     * @param quizId the quizId
     */
    public void setQuizId(String quiz_id) {
        this.quizId = quiz_id;
    }
}
