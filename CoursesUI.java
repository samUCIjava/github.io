
import java.util.ArrayList;
import java.util.Collections;

// Show the list of available courses and gives the options to register a course.
public class CoursesUI extends UserInterface {

	// Shows the course catalog console with title and list of courses
	public void show() {
		
		System.out.println();
		System.out.println(Constants.DELIMITER + Constants.OPTION_ALL_COURSES + Constants.DELIMITER);
		displayCourses(Database.loadCourses());
		super.display(true);
	}

	@Override
	protected void display() {
		System.out.println("1." + Constants.OPTION_ADD_COURSE);
		System.out.println("2." + Constants.OPTION_BACK_MAIN);
	}

	@Override
	protected void select(int option) {
		switch (option) {
		case Constants.REGISTER_COURSE:
			processRegisterRequest();
			break;
		case Constants.MAIN_MENU:
			StudentRegistrationSystemUI.main(null);
			break;
		default:
			System.out.println(Constants.INVALID_OPTION);
			display(true);
			break;
		}
	}

	private void processRegisterRequest(){
		
		if (StudentRegistrationSystemUI.getLogin().isLoggedIn()) {
			StudentRegistrationSystemUI.getRegistrar().show(RegistrationUI.REGISTER);
		}
		else {
			System.out.println("");
			System.out.print(Constants.DELIMITER+ " You must be logged in to register for a course " + Constants.DELIMITER);
			System.out.println("");
			StudentRegistrationSystemUI.main(null);
		}
	}
	
	private void displayCourses(ArrayList<Course> courses){
		Collections.sort(courses);
		for (Course course : courses) {
			System.out.println(course.toString());
		}
	}
}