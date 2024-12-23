package interfaces;

import users.User;
import service.ResearchJournal;

public interface UserActions {
    boolean login(User user);
    boolean logout(User user);
    void viewProfile(User user);
    void subscribeJournal(User user,ResearchJournal journal);
    void unsubscribeJournal(User user,ResearchJournal journal);
    void viewNews(User user);
    void viewJournals(User user);
    void handleEvent(User user, String paperTitle, ResearchJournal journal);
}
