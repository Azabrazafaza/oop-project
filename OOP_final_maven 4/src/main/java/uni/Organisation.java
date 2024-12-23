package uni;

import database.DatabaaseeActions;
import lombok.Getter;
import lombok.Setter;
import users.Student;

@Setter
@Getter
public class Organisation {
    int organisationId;
    String organisationName;
    String organisationDescription;
    String specialization;
    Student leader;

    public Organisation(int organisationId, String organisationName, String organisationDescription, String specialization, Student leader) {
        this.organisationId = organisationId;
        this.organisationName = organisationName;
        this.organisationDescription = organisationDescription;
        this.specialization = specialization;
        this.leader = leader;
    }

    @Override
    public String toString() {
        return "Organisation: " + organisationName + "\nDescription: " + organisationDescription + "\nSpecialization: " + specialization + "\nLeader: " + leader.getName();
    }

}
