package source.Entity;

public class Suggestion {
    private String suggestionID;
    private String campName; // Name of the camp the suggestion is for
    private String createdBy; // User ID of the person who made the suggestion
    private String content; // Details of the suggestion
    private boolean isApproved; // Whether the suggestion has been approved

    public Suggestion(String suggestionID, String campName, String createdBy, String content) {
        this.suggestionID = suggestionID;
        this.campName = campName;
        this.createdBy = createdBy;
        this.content = content;
        this.isApproved = false; // By default, suggestions are not approved
    }

    public String getSuggestionID() {
        return suggestionID;
    }

    public void setSuggestionID(String suggestionID) {
        this.suggestionID = suggestionID;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "ID='" + suggestionID + '\'' +
                ", Camp='" + campName + '\'' +
                ", Created By='" + createdBy + '\'' +
                ", Content='" + content + '\'' +
                ", Approved=" + isApproved +
                '}';
    }
}
