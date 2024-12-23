package uni;
import enums.LessonType;
public class Lesson {
    private int lessonId;
    private String topic;
    private LessonType lessonType;
    private Course course;
    private String room;

    public Lesson(int lessonId, String topic, LessonType lessonType, Course course, String room) {
        if (lessonId <= 0) {
            throw new IllegalArgumentException("Lesson ID must be positive.");
        }
        if (lessonType == null) {
            throw new IllegalArgumentException("Lesson type cannot be null.");
        }
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }
        if (room == null || room.isEmpty()) {
            throw new IllegalArgumentException("Room cannot be null or empty.");
        }
        this.lessonId = lessonId;
        this.topic = topic;
        this.lessonType = lessonType;
        this.course = course;
        this.room = room;
    }



    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        if (lessonId <= 0) {
            throw new IllegalArgumentException("Lesson ID must be positive.");
        }
        this.lessonId = lessonId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        if (lessonType == null) {
            throw new IllegalArgumentException("Lesson type cannot be null.");
        }
        this.lessonType = lessonType;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }
        this.course = course;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        if (room == null || room.isEmpty()) {
            throw new IllegalArgumentException("Room cannot be null or empty.");
        }
        this.room = room;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId=" + lessonId +
                ", topic='" + topic + '\'' +
                ", lessonType=" + lessonType +
                ", course=" + course +
                ", room='" + room + '\'' +
                '}';
    }
}
