
package controller;

import dao.QuizDao;
import entity.Account;
import entity.Question;
import entity.Quiz;
import entity.TypeAccount;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Home.<br>
 *
 * <pre>
 * Class nhận về các câu hỏi và tiến hành tạo 1 bài quiz mới
 *
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 *
 *. ProcessRequest.
 *. DoGet.
 *. DoPost.
 *
 *
 * </pre>
 *
 */
@WebServlet(name = "makeQuizController", urlPatterns = {"/makeQuiz"})
public class MakeQuizController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     * <pre>
     * - Kiểm tra nếu người đăng nhập là giáo viên thì mới cho sử dụng chức năng này
     * - Ngược lại in ra bạn không có quyền truy cập vào trang này
     * </pre>
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        session.setAttribute("url", "makeQuiz");
//        if (account == null) {
//            response.sendRedirect("login");
//        } else {
//            if (account.getTypeAccount().getId() != TypeAccount.TEACHER) {
//                request.setAttribute("error", "You are not authorized to enter this page");
//                request.getRequestDispatcher("error.jsp").forward(request, response);
//            }
            session.removeAttribute("questionNo");
            session.removeAttribute("newQuiz");
            request.getRequestDispatcher("inputTestId.jsp").forward(request, response);
//        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * <pre>
     * - Tạo 1 bài quiz với danh sách các câu hỏi ứng với số lượng nhận về
     * </pre>
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    int count = 0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String quizId = request.getParameter("quiz_id");
        //Lấy ra bài quiz ứng với quizId truyền vào
        Quiz oldQuiz = new QuizDao().getQuizById(quizId);

        String quizName = request.getParameter("quiz_name");
        HttpSession session = request.getSession();
        //Lấy account xuống từ sesson
        Account account = (Account) session.getAttribute("account");
        String creator = null;
        if (account != null) {//Nếu account tồn tại, username vào biên creator
            creator = account.getUsername();
        }
        if (oldQuiz != null) {
//        if (oldQuiz != null && !oldQuiz.getCreator().equals(creator)) {
            //Nếu bài quiz đã tồn tại , và người truy cập khác tác giả,
            //thì không cho phép thêm câu hỏi.
            request.setAttribute("error", "The Quiz already exists.");
            request.getRequestDispatcher("inputTestId.jsp").forward(request, response);
        } else {
            //lấy xuống số lượng câu hỏi
            String raw_num = request.getParameter("numOfQuestion");
            int numOfQuestion;
            if (raw_num != null) {
                numOfQuestion = Integer.parseInt(raw_num);
                //lưu số lượng câu hỏi lên sesson
                session.setAttribute("numOfQuestion", numOfQuestion);
            }
            Quiz newQuiz = (Quiz) session.getAttribute("newQuiz");
            if (newQuiz == null) {
                //Nếu trên sesson chưa có bài quiz mới thì tạo mới, và đẩy lên sesson
                count = 0;
                newQuiz = new Quiz();
                newQuiz.setId(quizId);
                newQuiz.setName(quizName);
                newQuiz.setCreator(creator);
                List<Question> listQuestion = new ArrayList<>();
                newQuiz.setListQuestion(listQuestion);
                session.setAttribute("newQuiz", newQuiz);
                request.setAttribute("questionNo", listQuestion.size() + 1);
                //chuyển sang trang để nhập câu hỏi
                request.getRequestDispatcher("makeQuiz.jsp").forward(request, response);

            } else {
                //Nếu bài quiz đã có trên sesson
                String question = request.getParameter("question");
                String Answers[] = request.getParameterValues("answer");

                String answer = "";
                StringBuilder str = new StringBuilder("");
                if (Answers != null) {
                    //lấy xuống các câu trả lời, nối lại với nhau ngăn cách bởi dấu |
                    for (int i = 0; i < Answers.length; i++) {
                        if (!Answers[i].equals("")) {
                            str.append(Answers[i] + "|");
                        }
                    }
                    answer = str.substring(0, str.length() - 1);
                }
                String Option[] = request.getParameterValues("option");
                str = new StringBuilder("");
                String answerCorrect;
                if (Option != null) {
                    //lấy xuống đáp án đúng, nối lại với nhau ngăn cách bởi dấu |
                    for (int i = 0; i < Option.length; i++) {
                        str.append(Answers[Integer.parseInt(Option[i])] + "|");
                    }
                    answerCorrect = str.substring(0, str.length() - 1);
                } else {
                    answerCorrect = str.toString();
                }
                //Tạo 1 câu hỏi mới
                Question ques = new Question();
                ques.setQuestion(question);
                ques.setAnswer(answer);
                ques.setAnswerCorrect(answerCorrect);
                ques.setQuizId(newQuiz.getId());
                
                //Lấy xuống list câu hỏi trên sesson xuống, và thêm vào câu hỏi vừa tạo
                List<Question> listQuestion = newQuiz.getListQuestion();
                listQuestion.add(ques);
                count++;
                newQuiz.setListQuestion(listQuestion);
                //đẩy lại bài quiz lên sesson
                session.setAttribute("newQuiz", newQuiz);
                if (count == (int) session.getAttribute("numOfQuestion")) {//nếu đủ số lượng thì chuyển sang trang save
                    request.getRequestDispatcher("save-quiz").forward(request, response);
                } else {
                    //tiếp tục tạo câu hỏi
                    request.setAttribute("questionNo", listQuestion.size() + 1);
                    request.getRequestDispatcher("makeQuiz.jsp").forward(request, response);
                }
            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
