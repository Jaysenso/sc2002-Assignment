package source.Entity;

import java.time.LocalDate;

/**
 * The Enquiry class holds information about enquiries made to a camp by a particular student.
 *
 * @author Edwin Lim
 * @version 1.0
 * @see Student
 * @see Camp
 * @see LocalDate
 * @since 11/4/2023
 */
public class Enquiry {
    /**
     * A boolean to determine whether the enquiry has been processed or not
     * NOTE : Once an enquiry has been accepted, it cannot be deleted ~ FAQ
     */
    private boolean processed = false;
    /**
     * The name of the camp that this enquiry belongs to.
     * NOTE : The camp name is a unique ID ~ FAQ
     */
    private String campName;
    /**
     * The user ID of the student that created this enquiry
     */
    private String createdBy;
    /**
     * Denotes the user id of who replied to this enquiry ( a staff id )
     */
    private String repliedBy = null;
    /**
     * The content of the enquiry
     */
    private String content;
    /**
     * The title of the enquiry
     */
    private String title;
    /**
     * The local date timestamp of when the enquiry was created
     */
    private LocalDate createdDate;
    /**
     * The local date timestamp of when the enquiry was replied
     */
    private LocalDate repliedDate = null;

    /**
     * An overloaded constructor
     *
     * @param campName    The name of the camp that this enquiry belongs to.
     * @param createdBy   The user ID of the student that created this enquiry
     * @param repliedBy   Denotes the user id of who replied to this enquiry ( a staff id )
     * @param content     The content of the enquiry
     * @param title       The title of the enquiry
     * @param createdDate The local date timestamp of when the enquiry was created
     * @param repliedDate The local date timestamp of when the enquiry was replied
     */
    public Enquiry(String campName,
                   String createdBy,
                   String repliedBy,
                   String content,
                   String title,
                   LocalDate createdDate,
                   LocalDate repliedDate) {
        this.campName = campName;
        this.createdBy = createdBy;
        this.repliedBy = repliedBy;
        this.content = content;
        this.title = title;
        this.createdDate = createdDate;
        this.repliedDate = repliedDate;
    }

    /**
     * A setter method to update the processed status of this enquiry.
     */
    public void setProcessed(boolean processed) {

        this.processed = processed;
    }

    /**
     * A getter method to acquire the processed status of this enquiry.
     */
    public boolean getProcessed() {

        return processed;
    }

    /**
     * A setter method to update the content of this enquiry.
     */
    public void setContent(String content) {

        this.content = content;
    }

    /**
     * A getter method to acquire the content of this enquiry.
     */
    public String getContent() {

        return content;
    }

    /**
     * A getter method to acquire the creation date of this enquiry.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * A setter method to set the user id of the person who created this enquiry.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * A getter method to acquire the used id of the person who replied to this enquiry.
     */
    public String getRepliedBy() {
        return repliedBy;
    }

    /**
     * A setter method to set the user id of the person who replied to this enquiry.
     */
    public void setRepliedBy(String repliedBy) {
        this.repliedBy = repliedBy;
    }

    /**
     * A getter method to acquire the creation date of this enquiry.
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    /**
     * A setter method to set the creation date of this enquiry.
     */
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * A getter method to acquire the replied date of this enquiry.
     */
    public LocalDate getRepliedDate() {
        return repliedDate;
    }

    /**
     * A setter method to set the replied date of this enquiry.
     */
    public void setRepliedDate(LocalDate repliedDate) {
        this.repliedDate = repliedDate;
    }

    /**
     * A getter method to acquire the camp name this enquiry belongs to
     */
    public String getCampName() {
        return campName;
    }

    /**
     * A setter method to update the camp name of the enquiry
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }

    /**
     * A getter method to acquire the title of this enquiry
     */
    public String getTitle() {
        return title;
    }

    /**
     * A setter method to update the title of this enquiry
     */
    public void setTitle(String title) {
        this.title = title;
    }
}