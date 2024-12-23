package uni;

import enums.LessonType;
import lombok.Getter;
import lombok.Setter;
import users.Student;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
public class Journal extends Lesson {
    private Date lessonDate;
    private Map<Student, Integer> studentMarks; // Map to store marks for each student

    public Journal(int lessonId, String topic, LessonType lessonType, Course course, String room, Date lessonDate) {
        super(lessonId, topic, lessonType, course, room);
        this.lessonDate = lessonDate;
        this.studentMarks = new HashMap<>();
    }

    // Add or update a mark for a student
    public void addMark(Student student, int mark) {
        if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark. Please provide a mark between 0 and 100.");
            return;
        }

        if (getCourse().getStudents().contains(student)) {
            studentMarks.put(student, mark);
            System.out.println("Mark added/updated for student: " + student.getName());
        } else {
            System.out.println("Student is not registered for this course.");
        }
    }

    // View all marks for the lesson
    public void viewMarks() {
        System.out.println("Marks for lesson on " + lessonDate + ":");
        for (Map.Entry<Student, Integer> entry : studentMarks.entrySet()) {
            System.out.println("Student: " + entry.getKey().getName() + ", Mark: " + entry.getValue());
        }
    }

    // Get mark for a specific student
    public Integer getMark(Student student) {
        return studentMarks.getOrDefault(student, null);
    }
}

