package source.Entity;

public class Enquiry {
    private String content;
    private String reply;
    private boolean processed;

    public Enquiry(String content, String reply, boolean processed) {
        this.content = content;
        this.reply = reply;
        this.processed = processed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
