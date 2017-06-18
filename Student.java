
import java.util.ArrayList;
import java.util.Comparator;


public class Student implements Comparator<Student>, Comparable<Student>  {

	private int studentID;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String password = null;
	ArrayList<String> courseList = new ArrayList<String>();

	public Student(int studentID, String firstName, String lastName, 
			String address, String city, String state, String zip, 
			String password) {

		this.studentID = studentID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.password = password;
		this.courseList = null;
	}

	public Student(String[] fileRecord) {

		this.studentID = Integer.parseInt(fileRecord[0]);
		this.firstName = fileRecord[1].trim();
		this.lastName = fileRecord[2].trim();
		this.address = fileRecord[3];
		this.city = fileRecord[4];
		this.state = fileRecord[5];
		this.zip = fileRecord[6];
		this.password = fileRecord[7];
	}

	protected int getStudentID(){
		return studentID;
	}

	protected void setStudentID(int studentID){
		this.studentID = studentID;
	}

	protected String getPassword(){
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

	protected String getFirstName() {
		return firstName;
	}

	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	protected String getLastName() {
		return lastName;
	}

	protected void setLastName(String lastName){
		this.lastName = lastName;
	}

	protected String getAddress() {
		return address;
	}

	protected void setAddress(String address) {
		this.address = address;
	}

	protected String getCity() {
		return city;
	}

	protected void setCity(String city) {
		this.city = city;
	}

	protected String getState() {
		return state;
	}

	protected void setState(String state) {
		this.state = state;
	}

	protected String getZip() {
		return zip;
	}

	protected void setZip(String zip) {
		this.zip = zip;
	}


	public String toString() {
		return studentID + "," + firstName + ","
				+ lastName + "," + address + "," + city + "," 
				+ state + "," + zip + "," + password;
	}

	// Sort by Last Name then First Name if necessary.
	public int compareTo(Student student) {
		int lastCmp = lastName.compareTo(student.lastName);
		return(lastCmp != 0 ? lastCmp : firstName.compareTo(student.firstName));
	}

	// Sort by increasing studentID numbers
	public int compare(Student s1, Student s2) {
		return(s1.studentID - s2.studentID);	
	}
}