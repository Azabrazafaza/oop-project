package service;
import java.io.Serializable;
import java.util.Vector;

import interfaces.Observe;
import users.User;
public class ResearchJournal implements Serializable, Observe {
    private String name;
    private Vector<ResearchPaper> papers = new Vector<>();
    private Vector<User> subscribers = new Vector<>();

    public ResearchJournal(String name) {
        this.name = name;
    }
    /**
     * Adds a research paper to the journal and notifies subscribers about the new publication.
     *
     * @param paper The research paper to be added.
     */
    public void addPaper(ResearchPaper paper) {
        this.papers.add(paper);
        this.notifyObservers(paper, this);
    }
    /**
     * Removes a research paper from the journal.
     *
     * @param paper The research paper to be removed.
     */
    public void removePaper(ResearchPaper paper) {
        this.papers.remove(paper);
    }
    /**
     * Adds a user as a subscriber to the journal.
     *
     * @param observer The user to be added as a subscriber.
     */
    @Override
    public void addObserver(User observer) {
        this.subscribers.add(observer);
    }
    @Override
    public void removeObserver(User observer) {
        this.subscribers.remove(observer);
    }

    /**
     * Notifies all subscribers about a new research paper publication.
     *
     * @param paper The research paper that has been published.
     * @param rj    The research journal to which the paper was added.
     */
    @Override
    public void notifyObservers(ResearchPaper paper, ResearchJournal rj) {
        for(User u: subscribers) {
            u.handleEvent(paper, rj);
        }
    }
    public String toString() {
        return "Journal " + name + ", papers =" + papers + ", subs =" + subscribers;
    }
    public String getName() {
        return this.name;
    }
}