
package entity;

/**
 * TypeAccount.<br>
 *
 * <pre>
 *Class mô tả đối tượng TypeAccount
 *Trong class này sẽ tiến hành các xử lí dưới đây
 *
 * . GetId.
 * . SetId.
 * . GetType.
 * . SetType.
 *
 * </pre>
 *
 */
public class TypeAccount {
    public static final int  STUDENT = 1;
    public static final int  TEACHER = 2;
    /**
     * Store id.
     */
    private int id;
    /**
     * Store type.
     */
    private String type;

    /**
     * Constructor.<br>
     */
    public TypeAccount() {
    }

    /**
     * Constructor full parameter<br>
     *
     * @param id the id
     * @param type the type
     */
    public TypeAccount(int id, String type) {
        this.id = id;
        this.type = type;
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
     * Get type.<br>
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set type.<br>
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

}
