import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

// Control the add/drop of courses
public class RegistrationUI extends UserInterface
{
	protected ArrayList<Enrolled> studentRegistrations;
	protected ArrayList<Course> courses;

	public static String REGISTER = "register";
	public static String UNREGISTER = "unregister";

	public RegistrationUI()
	{
		studentRegistrations = new ArrayList<Enrolled>();
		courses = new ArrayList<Course>();
	}

	public enum RegistrationStatus{SUCCESSFUL, FULL, INVALID_ID, ALREADY_REGISTERED};

	public void show(String option) {
		try {
			Scanner inputScanner = UserInterface.getInputScanner();
			String courseId = "";
			int studentId = 0;

			System.out.println();
			studentId = StudentRegistrationSystemUI.getLogin().getStudent().getStudentID();

			if (option == REGISTER) {
				System.out.println(Constants.DELIMITER + Constants.OPTION_ADD_COURSE + Constants.DELIMITER);

				System.out.print(Constants.COURSE_ID_PROMPT);
				courseId = inputScanner.nextLine().trim().toUpperCase();

				if (addCourse(studentId,courseId)) {
					StudentRegistrationSystemUI.getMyCourseSchedule().displayMenu();
				}
				else {
					StudentRegistrationSystemUI.main(null);
				}
			}
			else if (option == UNREGISTER) {
				System.out.println(Constants.DELIMITER + Constants.OPTION_DROP_COURSE + Constants.DELIMITER);

				System.out.print(Constants.COURSE_ID_PROMPT);
				courseId = inputScanner.nextLine().trim().toUpperCase();

				if (dropCourse(studentId,courseId)) {
					StudentRegistrationSystemUI.getMyCourseSchedule().displayMenu();
				}
				else {
					StudentRegistrationSystemUI.getMyCourseSchedule().displayMenu();
				}
			}
		} catch (InputMismatchException e) {
			System.out.println(this.getClass().getName() + ": Error! " + e.getMessage());
		}
	}

	@Override
	protected void display() {

	}

	@Override
	protected void select(int option) {

	}

	public boolean addCourse(int studentID, String courseID)
	{
		RegistrationStatus status = RegistrationStatus.SUCCESSFUL;

		Enrolled newStudentReg = new Enrolled(studentID, courseID);
		ArrayList<Enrolled> studentReg = new ArrayList<Enrolled>();
		ArrayList<Course> regCourseList = new ArrayList<Course>();


		if(regCourseList.isEmpty()){
			regCourseList = Database.loadCourseFile();
		}
		if(studentReg.isEmpty()){
			studentReg = Database.loadRegistrationFile();
		}

		if(!isValidCourseID(courseID, regCourseList)) {
			status =  RegistrationStatus.INVALID_ID;
		}
		else if(isAlreadyRegistered(studentReg, newStudentReg)) { 
			status =  RegistrationStatus.ALREADY_REGISTERED;
		}
		else if(isCourseFull(courseID, regCourseList)) {
			status =  RegistrationStatus.FULL;
		}

		boolean returnStatus=false;
		if (status == RegistrationStatus.SUCCESSFUL){
			studentRegistrations.add(newStudentReg);
			incrementCourseEnrollment(courseID, regCourseList);
			Database.saveRegistration(newStudentReg);
			Database.saveCoursesAll(regCourseList);
			returnStatus =true;
		}
		else{
			returnStatus = false;
		}

		displayRegistrationStatus(studentID, courseID, status );
		return returnStatus;

	}	

	private void displayRegistrationStatus(int studentId, String courseId, RegistrationStatus registrationStatus){
	
		Student student = StudentRegistrationSystemUI.getLogin().getStudent();
		String successful = " was successful.";
		String unsuccessful = " was unsuccessful,";
		String basicInfo = student.getFirstName() + " " +
				student.getLastName()  + " your request to register for " + courseId;
		String displayMsg = "";

		switch(registrationStatus){
		case SUCCESSFUL:
			displayMsg = basicInfo + successful;
			break;
		case FULL:
			displayMsg = basicInfo+unsuccessful + "the course is full.";
			break;
		case INVALID_ID:
			displayMsg = basicInfo+unsuccessful + "the course id is invalid.";
			break;
		case ALREADY_REGISTERED:
			displayMsg = basicInfo+unsuccessful + "you are already enrolled in this course.";
			break;
		}
		System.out.println(displayMsg);
	}  

	public boolean isValidCourseID(String id, ArrayList<Course> tempCourseList) 
	{
		for (Course c : tempCourseList)
			if (c.getCourseID().equals(id))
				return true;
		return false;
	}

	protected boolean isAlreadyRegistered(ArrayList<Enrolled> studentReg, Enrolled checkReg)
	{
		for (Enrolled r : studentReg)
			if ((r.getStudentId() == checkReg.getStudentId()) && (r.getCourseId().equals(checkReg.getCourseId())))
				return true;
		return false;
	}

	protected boolean isCourseFull(String courseID, ArrayList<Course> regCourseList)
	{
		for (Course c: regCourseList) {
			if(c.getCourseID().equals(courseID)) {
				if(c.isCourseFilled(c.getMaxNumStudents(), c.getNumEnrolled())) {
					return true;
				}
			}
		}
		return false;
	}

	public void incrementCourseEnrollment(String courseId, ArrayList<Course> regCourseList)
	{
		for(Course c: regCourseList) {
			if(c.getCourseID().equalsIgnoreCase(courseId)) {
				int newEnrolled = c.getNumEnrolled()+1;
				c.setNumEnrolled(newEnrolled);
			}
		}
	}

	protected void decrementCourseEnrollment(String courseId, ArrayList<Course> regCourseList)
	{
		for(Course c: regCourseList) {
			if(c.getCourseID().equalsIgnoreCase(courseId)) {
				int newEnrolled = c.getNumEnrolled()-1;
				c.setNumEnrolled(newEnrolled);
			}
		}
	}

	public void myCourseSchedule(int studentId)
	{
		ArrayList<Enrolled> regStudentList = new ArrayList<Enrolled>();
		ArrayList<Course> regCourseList = new ArrayList<Course>();

		if(regCourseList.isEmpty()){
			regCourseList = Database.loadCourseFile();
		}
		if(regStudentList.isEmpty()){
			regStudentList = Database.loadRegistrationFile();
		}

		// Loop through registrations looking for students id
		// and listing out the course info they are registered for.
		for (Enrolled r : regStudentList) {
			if (r.getStudentId() == studentId) {
				for (Course c : regCourseList) {
					if (c.getCourseID().equals(r.getCourseId())) {
						System.out.println(c.toString());
					}
				}
			}
		}
	}

	public boolean dropCourse(int studentId, String courseId)
	{
		ArrayList<Enrolled> enrolled = new ArrayList<Enrolled>();
		ArrayList<Course> courses = new ArrayList<Course>();

		if(courses.isEmpty()){
			courses = Database.loadCourseFile();
		}
		if(enrolled.isEmpty()){
			enrolled = Database.loadRegistrationFile();
		}

		if(!enrolled.isEmpty()) {
			for(Enrolled enrollmentEntry: enrolled) {
				if((enrollmentEntry.getStudentId() == studentId) && (enrollmentEntry.getCourseId().equalsIgnoreCase(courseId.trim()))) {
					enrolled.remove(enrollmentEntry);
					decrementCourseEnrollment(enrollmentEntry.getCourseId(), courses);
					Collections.sort(enrolled, new Enrolled());
					Database.saveRegistrationsAll(enrolled);
					Database.saveCoursesAll(courses);

					System.out.println("");
					System.out.println(Constants.DELIMITER+StudentRegistrationSystemUI.getLogin().getStudent().getFirstName() + " " +
							StudentRegistrationSystemUI.getLogin().getStudent().getLastName()  + ", you have dropped the course: " + courseId+Constants.DELIMITER);
					System.out.println("");
					return true;
				}
			}

			System.out.println("No matching registration records found.");
			return false;
		}
		return false;
	}
}	