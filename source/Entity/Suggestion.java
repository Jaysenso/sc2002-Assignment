package source.Entity;

public class Suggestion {
    private String suggestionID;
    private String campName; // Camp to which the suggestion is related
    private String createdBy; // User ID of the person who made the suggestion
    private String content; // Details of the suggestion
    private boolean approved; // Whether the suggestion has been approved

    // Constructors, getters, and setters here

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}

