package Courses;

import java.util.*;

public class Course {
    
    private String courseID;
    private String courseName;
    private int courseCredits;
    private int courseDuration;
    private String courseTutor;
    
    
    public Course(String courseID, String courseName, int courseCredits, int courseDuration, String courseTutor) {
        super();
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseCredits = courseCredits;
        this.courseDuration = courseDuration;
        this.courseTutor = courseTutor;
    }
    
    
    
    @Override
    public String toString() {
        return "Course [courseID=" + courseID + ", courseName=" + courseName + ", courseCredits=" + courseCredits
                + ", courseDuration=" + courseDuration + ", courseTutor=" + courseTutor + "]";
    }



    public Course() {
        super();
    }

    public String getCourseID() {
        return courseID;
    }
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public int getCourseCredits() {
        return courseCredits;
    }
    public void setCourseCredits(int courseCredits) {
        this.courseCredits = courseCredits;
    }
    public int getCourseDuration() {
        return courseDuration;
    }
    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }
    public String getCourseTutor() {
        return courseTutor;
    }
    public void setCourseTutor(String courseTutor) {
        this.courseTutor = courseTutor;
    }
}