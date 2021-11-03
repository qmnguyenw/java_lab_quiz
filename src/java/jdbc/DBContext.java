
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DBContext.<br>
 * 
 * <pre>
 * Class mở kết nối đến sqlServer
 * Trong class này sẽ tiến hành các xử lí dưới đây
 
 * . constructor.
  
 </pre>
 * 
 */
public class DBContext {
    protected Connection connection=null;
    /**
     * Constructor.<br>
     * Mở kết nối tới SQLServer
     * Trường hợp gặp lỗi thực hiện exception.
     */
    public DBContext()
    {
        try {
            // Edit URL , username, password to authenticate with your MS SQL Server
            String url = "jdbc:sqlserver://localhost:1433;databaseName=OnlineQuiz";
            String username = "sa";
            String password = "123456";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
