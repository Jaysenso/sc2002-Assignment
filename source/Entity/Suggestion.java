package source.Entity;

import java.time.LocalDate;

/**
 * The Suggestion class holds information about suggestions made to a camp by a particular student.
 *
 * @author Pan Haolun
 * @version 1.0
 * @see Student
 * @see Camp
 * @see LocalDate
 * @since 11/4/2023
 */
public class Suggestion {
    /**
     * A boolean to determine whether the suggestion has been processed or not
     */
    private boolean isProcessed = false;
    /**
     * A boolean to determine whether the suggestion has been processed or not
     */
    private boolean isApproved = false;
    /**
     * The name of the camp that this suggestion belongs to.
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
     * The local date timestamp of when the enquiry was created
     */
    private LocalDate createdDate;
    /**
     * The local date timestamp of when the enquiry was replied
     */
    private LocalDate repliedDate = null;

    /**
     * An overloaded constructor that initializes a suggestion based on some values
     *
     * @param campName    The name of the camp that this suggestion belongs to.
     * @param createdBy   The user ID of the student that created this enquiry
     * @param repliedBy   Denotes the user id of who replied to this enquiry ( a staff id )
     * @param content     The content of the enquiry
     * @param isProcessed A boolean to determine whether the suggestion has been processed or not
     * @param isApproved  A boolean to determine whether the suggestion has been processed or not
     * @param createdDate The local date timestamp of when the enquiry was created
     * @param repliedDate The local date timestamp of when the enquiry was replied
     */
    public Suggestion(String campName,
                      String createdBy,
                      String repliedBy,
                      String content,
                      boolean isProcessed,
                      boolean isApproved,
                      LocalDate createdDate,
                      LocalDate repliedDate) {
        this.repliedBy = repliedBy;
        this.content = content;
        this.createdDate = createdDate;
        this.repliedDate = repliedDate;
        this.campName = campName;
        this.createdBy = createdBy;
        this.isApproved = isApproved; // By default, suggestions are not approved
        this.isProcessed = isProcessed;
    }

    /**
     * A getter method to acquire the camp name of this suggestion.
     *
     * @return the camp name to this suggestion
     */
    public String getCampName() {
        return campName;
    }

    /**
     * A setter method to update the camp name of this suggestion.
     *
     * @param campName the new camp name
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }

    /**
     * A getter method to acquire the created by of this suggestion.
     *
     * @return get the user id of who created this suggestion.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * A setter method to update the created by of this suggestion.
     *
     * @param createdBy updates who created this suggestion
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * A getter method to acquire the content of this suggestion.
     *
     * @return the content of this suggestion
     */
    public String getContent() {
        return content;
    }

    /**
     * A setter method to update the content of this suggestion.
     *
     * @param content the new content of this suggestion
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * A getter method to acquire the approved status of this suggestion.
     *
     * @return get the approved status of the suggestion
     */
    public boolean getApproved() {
        return isApproved;
    }

    /**
     * A setter method to update the approved status of this suggestion.
     *
     * @param isApproved the new approved status of this suggestion
     */
    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    /**
     * A setter method to update the created date of this suggestion.
     *
     * @return the processed status of this suggestion
     */
    public boolean getProcessed() {
        return isProcessed;
    }

    /**
     * A setter method to update the processed status of this suggestion.
     *
     * @param processed the new processed status of this suggestion
     */
    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    /**
     * A getter method to acquire the replied by of this suggestion.
     *
     * @return the user id of who replied to this suggestion
     */
    public String getRepliedBy() {
        return repliedBy;
    }

    /**
     * A setter method to update the replied by of this suggestion.
     *
     * @param repliedBy the new user id of who replied to this suggestion
     */
    public void setRepliedBy(String repliedBy) {
        this.repliedBy = repliedBy;
    }

    /**
     * A getter method to acquire the reply date of this suggestion.
     *
     * @return the replied date of this suggestion
     */
    public LocalDate getRepliedDate() {
        return repliedDate;
    }

    /**
     * A setter method to update the replied date of this suggestion.
     *
     * @param repliedDate the new replied date of this suggestion
     */
    public void setRepliedDate(LocalDate repliedDate) {
        this.repliedDate = repliedDate;
    }

    /**
     * A getter method to acquire the created date of this suggestion.
     *
     * @return the creation date of this suggestion
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    /**
     * A setter method to update the created date of this suggestion.
     *
     * @param createdDate creation date of this suggestion
     */
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
