package users;

import database.DatabaaseeActions;
import interfaces.Observe;
import interfaces.UserActions;
import lombok.Getter;
import lombok.Setter;
import service.ResearchJournal;
import service.Func;
import service.ResearchPaper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Vector;


@Setter
@Getter
public abstract class User implements Serializable, UserActions {
    public int id ;
    public String username;
    public String password;
    public String name;
    public boolean loggedIn;
    public Vector<ResearchJournal> journals;

    public User(int id,String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.loggedIn = false;

        this.journals = new Vector<>();
    }
    public boolean login() throws SQLException {

        System.out.println(name + " has logged in.");
        loggedIn = true;
        List<ResultSet> res = DatabaaseeActions.findAll("ResearchJoournals");
        for (ResultSet i : res) {
//            i.getString(1);
//            i.getString("id");

            journals.add(new ResearchJournal(
                    i.getString("name")


            ));
        }

        return loggedIn;

    }
    public boolean logout(){
        System.out.println(name + " has logged out.");
        loggedIn = false;
        return loggedIn;

    }
    public Vector<ResearchJournal> getJournals(){
        return journals;
    }

    public void viewNews() {

        System.out.println(Optional.ofNullable(DatabaaseeActions.findById("News", -1)));

    }
    public void subscribeJournal(User user) {
        journals.removeAll(user.getJournals());
        if (journals.isEmpty()) {
            System.out.println("No Journals!");
            return;
        }
        System.out.println("Go back");
        System.out.println("Choose Research journal:");
        new Func().printList(journals);
        int choice = new Func().validateChoice(journals.size());
        if (choice == 0) {
            return;
        }
        ResearchJournal journal = journals.get(choice - 1);
        journal.addObserver(user);
        user.getJournals().add(journal);
        System.out.println("Subscribed to journal: " + journal.getName());
    }


    private void printList(Vector<ResearchJournal> journals) {

    }

    public void unsubscribeJournal(User user) {

        Vector<ResearchJournal> journals = user.getJournals();
        if(journals.isEmpty()) {
            System.out.println("No Hournals!");
            return;
        }
        System.out.println("Go back");
        System.out.println("Choose Research journal:");
        printList(user.getJournals());
        int choice = new Func().validateChoice(journals.size());
        if(choice == 0) {
            return;
        }
        ResearchJournal journal = journals.get(choice-1);
        for (ResearchJournal i :journals) {
            i.removeObserver(user);// сюда нужно юзера отправить
        }
//        journals.removeObserver();
        journals.remove(journal);
        System.out.println("Unsubscribed from Research journal:" + journal.getName());
    }
    public String toString() {
        return "[id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + "]";
    }
    public int hashCode() {
        return Objects.hash(id, username, password, name);
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    public void handleEvent(ResearchPaper paper, ResearchJournal rj) {
        System.out.println(name + " received a new paper in journal " + rj.getName() + ": " + paper.getTitle());
    }
}