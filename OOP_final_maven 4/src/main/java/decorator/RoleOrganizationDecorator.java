package decorator;
import users.Student;
public abstract class RoleOrganizationDecorator extends Student{
    protected Student student;
    public RoleOrganizationDecorator(Student student) {
        super(student.getId(), student.getUsername(), student.getPassword(), student.getName(), student.getStudentId(), student.getStudentCourse(), student.getFaculty(), student.getGpa(), student.getCredits(), student.getMarks());
        this.student = student;
    }

    @Override
    public String getDetails() {
        return student.getDetails();
    }

}
