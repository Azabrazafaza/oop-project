package interfaces;
import users.Teacher;
import users.User;
import users.Student;
import uni.Course;
public interface TeacherI {
    boolean putMark(Student student, Course course, double mark);
}