
import java.util.Comparator;
import java.lang.StringBuilder;

//Contains enrollement for courses and students
public class Enrolled implements Comparator <Enrolled>   {

	private int studentId;
	private String courseId;

	public Enrolled(int studentId, String courseId)
	{
		this.studentId = studentId;
		this.courseId = courseId;
	}

	// Constructor used when reading in registration info from file.
	public Enrolled(String[] fileRecord)
	{
		studentId = Integer.parseInt(fileRecord[0]);
		courseId = fileRecord[1].trim();
	}

	public Enrolled() {

	}

	protected int getStudentId() {
		return studentId;
	}

	protected String getCourseId() {
		return courseId;
	}
	
	public String toString()
	{
		return "Registration [studentID=" + 
				studentId + ", courseID=" + courseId+"]";
	}

	protected String recordFormater()
	{
		StringBuilder outRecord = new StringBuilder();
		String line = studentId + "," + courseId;
		outRecord.append(line);
		return outRecord.toString();

	}

	public int compare(Enrolled r1, Enrolled r2) {
		return(r1.getStudentId()-r2.getStudentId());	// Will sort in increasing studentId value
	}
}
