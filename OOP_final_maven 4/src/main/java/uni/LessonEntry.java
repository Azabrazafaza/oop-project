package uni;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@Getter
@Setter

public class LessonEntry{
    private Lesson lesson;
    private LocalDate date;
    private Map<String, Integer> grades;
    private Set<String> attendance;

    public LessonEntry(Lesson lesson, LocalDate date) {
        this.lesson = lesson;
        this.date = date;
        this.grades = new HashMap<>();
        this.attendance = new HashSet<>();
    }



    public void addGrade(String studentName, int grade) {
        grades.put(studentName, grade);
    }

    public void markAttendance(String studentName) {
        attendance.add(studentName);
    }

    @Override
    public String toString() {
        return "LessonEntry{" +
                "lesson=" + lesson +
                ", date=" + date +
                ", grades=" + grades +
                ", attendance=" + attendance +
                '}';
    }
}

