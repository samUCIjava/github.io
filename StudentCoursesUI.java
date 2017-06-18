

// Show the list of courses student has enrolled in and gives the option to drop a course.
public class StudentCoursesUI extends UserInterface {

	public void displayMenu() {
		int studentId = StudentRegistrationSystemUI.getLogin().getStudent().getStudentID();
		System.out.println();
		System.out.println(Constants.DELIMITER + Constants.OPTION_MY_COURSE_SCHEDULE + Constants.DELIMITER);
		StudentRegistrationSystemUI.getRegistrar().myCourseSchedule(studentId);
		super.display(true);
	}

	@Override
	protected void display() {
		System.out.println("1." + Constants.OPTION_DROP_COURSE);
		System.out.println("2." + Constants.OPTION_BACK_MAIN);
	}

	@Override
	protected void select(int option) {
		switch (option) {
		case Constants.UNREGISTER_COURSE:
			StudentRegistrationSystemUI.getRegistrar().show(RegistrationUI.UNREGISTER);
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
}