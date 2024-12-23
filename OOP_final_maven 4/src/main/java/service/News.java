package service;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

public class News {
    private String title;
    private String content;
    private List<Comment> comments;
    private boolean isResearchNews;

    public News(String title, String content) {
        this.title = title;
        this.content = content;
        this.comments = new ArrayList<Comment>();
        this.isResearchNews = false;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isResearchNews() {
        return isResearchNews;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void markAsResearchNews() {
        this.isResearchNews = true;
    }
}
