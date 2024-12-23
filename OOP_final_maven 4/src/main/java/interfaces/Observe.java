package interfaces;

import users.User;
import service.ResearchPaper;
import service.ResearchJournal;

public interface Observe {
    void addObserver(User observer);
    void removeObserver(User observer);
    void notifyObservers(ResearchPaper paper, ResearchJournal rj);
}
