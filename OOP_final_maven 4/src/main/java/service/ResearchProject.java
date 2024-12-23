package service;
import users.User;

import java.util.List;

public class ResearchProject {
    private String topic;
    private List<User> participants;
    private List<ResearchPaper> publishedPapers;

    // Конструктор
    public ResearchProject(String topic, List<User> participants, List<ResearchPaper> publishedPapers) {
        this.topic = topic;
        this.participants = participants;
        this.publishedPapers = publishedPapers;
    }

    // Методы доступа
    public String getTopic() {
        return topic;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    // Добавление участника
    public void addParticipant(User researcher) throws IllegalArgumentException {
        if (researcher == null) {
            throw new IllegalArgumentException("Only researchers can join the project!");
        }
        participants.add(researcher);
    }
}
