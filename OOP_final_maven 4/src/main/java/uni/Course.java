package uni;

import enums.CoursePeriod;
import enums.CourseType;
import users.Student;
import users.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseCode;
    private String courseName;
    private CourseType courseType;
    private int creditWeight;
    private String courseFormula;
    private CoursePeriod coursePeriod;
    private String courseDepartment;
    private List<Student> students;
    private List<Teacher> teachers;

    public Course(String courseCode, String courseName, CourseType courseType, int creditWeight, String courseFormula, CoursePeriod coursePeriod, String courseDepartment) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseType = courseType;
        this.creditWeight = creditWeight;
        this.courseFormula = courseFormula;
        this.coursePeriod = coursePeriod;
        this.courseDepartment = courseDepartment;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    // Getters and setters for fields
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public int getCreditWeight() {
        return creditWeight;
    }
    public void setCreditWeight(int creditWeight) {
        this.creditWeight = creditWeight;
    }

    public String getCourseFormula() {
        return courseFormula;
    }

    public void setCourseFormula(String courseFormula) {
        this.courseFormula = courseFormula;
    }

    public CoursePeriod getCoursePeriod() {
        return coursePeriod;
    }

    public void setCoursePeriod(CoursePeriod coursePeriod) {
        this.coursePeriod = coursePeriod;
    }

    public String getCourseDepartment() {
        return courseDepartment;
    }

    public void setCourseDepartment(String courseDepartment) {
        this.courseDepartment = courseDepartment;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

}
