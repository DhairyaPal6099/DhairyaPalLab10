package dhairya.pal.n01576099.dp.ui;

public class CourseItemRV {
    private String courseName;
    private String courseDescription;

    public CourseItemRV() {}

    public CourseItemRV(String courseName, String courseDescription) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public String getCourseDescription() {
        return this.courseDescription;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
}
