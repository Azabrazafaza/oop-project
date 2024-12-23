package interfaces;

import service.ResearchPaper;
import service.ResearchProject;

public interface ResearchActions {
    void publishPaper(ResearchPaper paper);
    void manageProject(ResearchProject project);
    int calculateHIndex();
    void becomeAResearchr();

}
