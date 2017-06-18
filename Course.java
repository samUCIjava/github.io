

// Course class contains information about each of the course objects.
public class Course implements Comparable<Course>{

	private String courseID;
	private String startDate;
	private String endDate;
	private String courseName;
	private String courseDescription;
	private int maxNumStudents;
	private int numEnrolled;

	public Course(String courseID, String startDate, String endDate,String courseName,
			String courseDescription, int courseLimit, int numEnrolled)
	{
		this.courseID = courseID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.maxNumStudents = courseLimit;
		this.numEnrolled = numEnrolled;
	}

	protected void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	protected void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	protected void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	protected void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	protected void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	protected void setMaxNumStudents(int courseLimit) {
		this.maxNumStudents = courseLimit;
	}

	protected void setNumEnrolled(int numEnrolled) {
		this.numEnrolled = numEnrolled;
	}

	protected String getCourseID() {
		return courseID;
	}

	protected String getCourseName() {
		return courseName;
	}

	protected String getStartDate() {
		return startDate;
	}

	protected String getEndDate() {
		return endDate;
	}

	protected String getCourseDescription() {
		return courseDescription;
	}

	protected int getMaxNumStudents() {
		return maxNumStudents;
	}

	protected int getNumEnrolled() {
		return numEnrolled;
	}

	protected boolean isCourseFilled(int limit, int numEnrolled) {
		boolean result;
		if (numEnrolled >= limit)
			result = true;
		else
			result = false;
		return result;
	}

	public int compareTo(Course course) {
		return this.courseName.compareTo(course.getCourseName());
	}

	public String toString() {
		return("\nCourse ID: " + getCourseID() + "\n"
				+ "Course Start Date: " + getStartDate() + "\n"
				+ "Course End Date: " + getEndDate() + "\n"
				+ "Course Name: " + getCourseName() + "\n"
				+ "Course Description: " + getCourseDescription() + "\n"
				+ "Course Limit: " + getMaxNumStudents() + "\n"
				+ "Number of Students Enrolled: " + getNumEnrolled());
	}
}
