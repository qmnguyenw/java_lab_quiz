
package dao;

import entity.TypeAccount;
import entity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.DBContext;
import ultility.Encrypt;

/**
 * AccountDao.<br>
 *
 * <pre>
 * Class dùng các câu query để lấy dữ liệu từ database
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 *
 * . getAccount.
 * . addAccount.
 * . getAccount.
 *
 * </pre>
 *
 */
public class AccountDao extends DBContext {

    /**
     * getAccount.<br>
     * Lấy Account thoa man username va password truyen vao
     *
     * @param userName
     * @param password
     * @return a Account
     */
    public Account getAccount(String userName, String password) {
        StringBuilder sql = new StringBuilder("SELECT dbo.[Account].*,type FROM dbo.[Account] ");
        sql.append("INNER JOIN dbo.Type_Account ON Type_Account.id = [Account].type_account  ");
        sql.append("WHERE username = ? AND password=?");
        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            statement.setString(1, userName);
            statement.setString(2, Encrypt.GetMD5(password));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Account user = new Account();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                TypeAccount type = new TypeAccount();
                type.setId(rs.getInt("type_account"));
                type.setType(rs.getString("type"));
                user.setTypeAccount(type);

                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    /**
     * GetNewById.<br>
     * Thêm 1 tài khoản mới
     *
     * @param username
     * @param password
     * @param typeAccount
     * @param email
     * @return a boolean
     */
    public boolean addAccount(String username, String password, int typeAccount, String email) {
        String sql = "INSERT INTO [dbo].[Account] ([username],[password],[type_account],[email]) VALUES (?,?,?,?)";
        int check = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, Encrypt.GetMD5(password));
            statement.setInt(3, typeAccount);
            statement.setString(4, email);
            check = statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return check > 0;
    }

    /**
     * GetNewById.<br>
     * Lấy ra tài khoản với username truyền vào.
     *
     * @param userName
     * @return a Account
     */
    public Account getAccount(String userName) {
        StringBuilder sql = new StringBuilder("SELECT dbo.[Account].*,type FROM dbo.[Account] ");
        sql.append("INNER JOIN dbo.Type_Account ON Type_Account.id = [Account].type_account  ");
        sql.append("WHERE username = ?");
        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Account user = new Account();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                TypeAccount type = new TypeAccount();
                type.setId(rs.getInt("type_account"));
                type.setType(rs.getString("type"));
                user.setTypeAccount(type);

                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
}
