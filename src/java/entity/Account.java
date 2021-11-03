
package entity;

/**
 * Account.<br>
 *
 * <pre>
 *Class mô tả đối tượng Account
 *Trong class này sẽ tiến hành các xử lí dưới đây
 *
 * . GetUsername.
 * . SetUsername.
 * . GetPassword.
 * . SetPassword.
 * . GetTypeAccount.
 * . SetTypeAccount.
 * . GetEmail.
 * . SetEmail.
 *
 * </pre>
 *
 */
public class Account {

    /**
     * Store id.
     */
    private String username;
    /**
     * Store id.
     */
    private String password;
    /**
     * Store id.
     */
    private TypeAccount typeAccount;
    /**
     * Store id.
     */
    private String email;

    /**
     * Constructor.<br>
     */
    public Account() {
    }

    /**
     * Constructor full parameter<br>
     *
     * @param username the username
     * @param password the password
     * @param typeAccount type Account
     * @param email the email
     */
    public Account(String username, String password, TypeAccount typeAccount, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Get username.<br>
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username.<br>
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get password.<br>
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password.<br>
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get typeAccount.<br>
     *
     * @return the typeAccount
     */
    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    /**
     * Set typeAccount.<br>
     *
     * @param typeAccount the typeAccount
     */
    public void setTypeAccount(TypeAccount typeAccount) {
        this.typeAccount = typeAccount;
    }

    /**
     * Get email.<br>
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email.<br>
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
