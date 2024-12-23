package interfaces;

import uni.Course;
import users.Student;
import users.Teacher;
public interface ManagerActions {
    void approveRegistration(Student student, Course course);
    void manageTeachers(Teacher teacher, String action,Course course);
    void manageStudents(Student student, String action, Course course);
    void manageNews(String newsItem,String action);
    void viewStudentInfo(Student student);
    void viewTeacherInfo(Teacher teacher);
    void createReport();
    void viewRequestMessages();
    Course findCourseByName(String name);
    Teacher findTeacherByName(String name);
    Student findStudentByName(String name);
}