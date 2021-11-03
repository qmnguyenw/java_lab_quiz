
package entity;

import java.sql.Date;
import java.util.List;

/**
 * Quiz.<br>
 *
 * <pre>
 *Class mô tả đối tượng Quiz
 *Trong class này sẽ tiến hành các xử lí dưới đây
 *
 * . GetId.
 * . SetId.
 * . GetName.
 * . SetName.
 * . GetCreator
 * . SetCreator
 * . GetCreatedDate
 * . setCreatedDate
 *
 * </pre>
 *
 */
public class Quiz {

    /**
     * Store id.
     */
    private String id;
    /**
     * Store name.
     */
    private String name;
    /**
     * Store creator.
     */
    private String creator;
    /**
     * Store createdDate.
     */
    private Date createdDate;
    /**
     * Store listQuestion.
     */
    private List<Question> listQuestion;

    /**
     * Constructor.<br>
     */
    public Quiz() {
    }

    /**
     * Constructor full parameter<br>
     *
     * @param id the id 
     * @param name the name
     * @param creator the creator
     * @param createdDate the createdDate
     * @param listQuestion the listQuestion
     */
    public Quiz(String id, String name, String creator, Date createdDate, List<Question> listQuestion) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.createdDate = createdDate;
        this.listQuestion = listQuestion;
    }
    /**
     * Get id.<br>
     *
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * Set id.<br>
     *
     * @param id the type
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Get name.<br>
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * Set name.<br>
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get creator.<br>
     *
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }
    /**
     * Set creator.<br>
     *
     * @param creator the creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    /**
     * Get createdDate.<br>
     *
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }
    /**
     * Set createdDate.<br>
     *
     * @param createdDate the createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    /**
     * Get listQuestion.<br>
     *
     * @return the listQuestion
     */
    public List<Question> getListQuestion() {
        return listQuestion;
    }
    /**
     * Set listQuestion.<br>
     *
     * @param listQuestion the listQuestion
     */
    public void setListQuestion(List<Question> listQuestion) {
        this.listQuestion = listQuestion;
    }
}
