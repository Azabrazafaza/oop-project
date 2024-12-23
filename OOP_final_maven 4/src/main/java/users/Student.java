package users;

import database.DatabaaseeActions;
import enums.FacultyType;
import enums.StudentCourse;
import interfaces.ResearchActions;
import lombok.Getter;
import lombok.Setter;
import service.ResearchPaper;
import service.ResearchProject;
import uni.Course;
import uni.Marks;
import uni.Organisation;
import decorator.*;
import java.util.List;
import java.util.Vector;

    @Setter
    @Getter
public abstract class Student extends User  implements ResearchActions {
    private String studentId;
    private StudentCourse studentCourse;
    private FacultyType faculty;
    private double gpa;
    private int credits;
    private Marks marks;
    private Vector<Course> courses;
    private boolean isAResearcher = false;
    private List<ResearchPaper> researchPapers;

    public Student(int id, String username, String password, String name, String studentId, StudentCourse studentCourse, FacultyType faculty, double gpa, int credits, Marks marks){
        super(id, username, password, name);
        this.studentId = studentId;
        this.studentCourse = studentCourse;
        this.faculty = faculty;
        this.gpa = gpa;
        this.credits = credits;
        this.marks = marks;
        this.courses = new Vector<>();
    }

    public void viewMarks(){

        System.out.println("Marks:" + name + ":");

        System.out.println(marks);

    }

    public void registerCourse(Course course){
        courses.add(course);
//        course.addStudent(this);
        System.out.println("Successfully registered!");
    }

    public void rateTeacher(Teacher teacher, int rating){
        teacher.addRating(rating);
        System.out.println(name + " rated " + teacher.getName() + " with a rating of" + rating);
    }
//Kamila -------------
    public String getDetails() {
        return "StudentId: " + studentId + ", Student: " + super.getName() + ", GPA: " + gpa + ", Credits: " + credits;
    }
    public Organisation createOrganisation(int organisationId, String organisationName, String description, String specialization) {
        Student leaderWithRole = new LeaderDecorator(this, organisationName);
        Organisation organisation = new Organisation(organisationId, organisationName, description, specialization, leaderWithRole);
        System.out.println("Organisation created: " + organisationName + " by leader: " + leaderWithRole.getDetails());
        DatabaaseeActions.update("leader_organization", leaderWithRole);
        return organisation;

        /*Organisation scienceClub = student1.createOrganisation(1, "Science Club", "A club for science enthusiasts.", "Science")*/
    }
    public void participateOrganisation(Organisation organisation) {
        Student memberWithRole = new MemberDecorator(this, organisation.getOrganisationName());
        System.out.println(memberWithRole.getDetails() + " is participating in " + organisation.getOrganisationName());
        DatabaaseeActions.update("member_organization", memberWithRole);
    }
// ------------------



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