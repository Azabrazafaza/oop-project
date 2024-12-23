package uni;
import enums.LessonType;


public class LessonFactory {
    public static Lesson createLesson(int lessonId, String topic, LessonType lessonType, Course course, String room) {
        return new Lesson(lessonId, topic, lessonType, course, room);
    }
}

