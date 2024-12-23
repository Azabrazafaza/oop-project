package decorator;
import users.Student;

public class MemberDecorator extends RoleOrganizationDecorator {
    private String organisationName;

    public MemberDecorator(Student student,String organisationName) {
        super(student);
        this.organisationName = organisationName;
    }

    @Override
    public String getDetails() {
        return student.getDetails() + "\nRole: Member of " + organisationName;
    }
}
