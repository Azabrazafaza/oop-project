package users;

import database.DatabaaseeActions;
import enums.Teacheros;
import enums.UrgencyLevel;
import interfaces.ResearchActions;
import lombok.Getter;
import service.Message;
import service.ResearchPaper;
import service.ResearchProject;
import uni.Course;
import uni.Journal;

import java.util.ArrayList;
import java.util.List;


public abstract class Teacher extends Employee implements ResearchActions {
    @Getter
    private Teacheros title;
    private List<Course> coursesTaught;
    @Getter
    private double rating;
    private List<Integer> studentRating;
    private boolean isAResearcher = false;
    private List<ResearchPaper> researchPapers;
    public Teacher(int id,
                   String username,
                   String password,
                   String name,
                   Teacheros title,
                   List<Course> coursesTaught,
                   double rating
    ) {
        super(id,username,password,name);
        this.title = title;
        this.coursesTaught = coursesTaught;
        this.rating = rating;
        this.studentRating = new ArrayList<>();
    }

    public void viewCourses() {
        for (Course course : coursesTaught) {
            System.out.println("- " + course.getCourseName());
        }
    }

    // View all marks for a specific lesson in a journal
    public void viewMarks(Journal journal) {
        //fetching from daatabase
//        if (journal.getCourse().contains(course)) {
//            System.out.println("Marks for lesson on " + journal.getLessonDate() + ":");
//            journal.viewMarks();
//        } else {
//            System.out.println("No lesson was found on this date"+journal.getLessonDate());
//        }
    }

    // Grade a student for a specific lesson
    public void gradeStudent(Journal journal, Student student, int mark) {
        if (journal.getCourse().getStudents().contains(student)) {
            journal.addMark(student, mark);
            System.out.println("Mark " + mark + " assigned to student " + student.getName() + " for lesson on " + journal.getLessonDate());
        } else {
            System.out.println("Student " + student.getName() + " is not registered in the course " + journal.getCourse().getCourseName());
        }
    }


    public void sendRequest(Dean dean, Student student, String content, UrgencyLevel urgency) {
        Message message = new Message(this , dean, content);
        dean.receiveMessage(message);
        DatabaaseeActions.save("Message",message);
        System.out.println(name  + " sent message to " + dean.getName());

    }


    public void viewStudents(Course course) {
        System.out.println("Students enrolled in " + course.getCourseName() + ":");
        for (Student student : course.getStudents()) {
            System.out.println("- " + student.getName());
        }
    }


    public void addRating(int rating) {
        DatabaaseeActions.save("TeachrsRatings", this);
    }




    @Override
    public void publishPaper(ResearchPaper paper) {
        DatabaaseeActions.save("Researches",paper);
    }

    @Override
    public void manageProject(ResearchProject project) {
        DatabaaseeActions.update("ResearchProject",project );
    }

    @Override
    public int calculateHIndex() {
        researchPapers.sort((p1, p2) -> Integer.compare(p2.getCitations(), p1.getCitations()));
        int h = 0;
        for (int i = 0; i < researchPapers.size(); i++) {
            if (researchPapers.get(i).getCitations() >= i + 1) {
                h = i + 1;
            } else {
                break;
            }
        }

        return h;
    }

    @Override
    public void becomeAResearchr() {
        isAResearcher = true;
    }
}