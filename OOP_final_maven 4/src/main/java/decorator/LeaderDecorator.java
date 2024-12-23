package decorator;
import users.Student;

public class LeaderDecorator extends RoleOrganizationDecorator {
    protected String organisationName;

    public LeaderDecorator(Student student, String organizationName) {
        super(student);
        this.organisationName = organisationName;
    }

    @Override
    public String getDetails() {
        return student.getDetails() + "\nRole: Leader of " + organisationName;
    }
}
