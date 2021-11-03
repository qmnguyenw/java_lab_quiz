
package dao;

import entity.TypeAccount;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.DBContext;

/**
 * TypeAccountDao.<br>
 *
 * <pre>
 * Class dùng các câu query để lấy dữ liệu từ database
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 *
 * . getAll.
 * . getOne.
 *
 * </pre>
 *
 */
public class TypeAccountDao extends DBContext{
    /**
     * getAll.<br>
     * Lấy ra tất cả loại tài khoản
     *
     * @return a list of Type account
     */
    public List<TypeAccount> getAll(){
        String sql = "SELECT * FROM dbo.Type_Account";
        List<TypeAccount> listType = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TypeAccount type = new TypeAccount();
                type.setId(rs.getInt("id"));
                type.setType(rs.getString("type"));
                listType.add(type);
            }
            return listType;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    /**
     * getOne.<br>
     * Lấy một loại tài khoản ứng với id truyền vào.
     *
     * @param id
     * @return a type account
     */
    public TypeAccount getOne(int id){
        String sql = "SELECT * FROM dbo.Type_Account where id =?";
        List<TypeAccount> listType = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TypeAccount type = new TypeAccount();
                type.setId(rs.getInt("id"));
                type.setType(rs.getString("type"));
                return type;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
}
