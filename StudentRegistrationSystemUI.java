
public class StudentRegistrationSystemUI extends UserInterface {

	private LoginUI login;
	private RegistrationUI registrar;
	private CoursesUI courseCatalog;
	private StudentCoursesUI myCourseSchedule;
	private NewAccountUI newStudentAccount;

	private static StudentRegistrationSystemUI mainRegistration;

	public StudentRegistrationSystemUI() {
		login = new LoginUI();
		registrar = new RegistrationUI();
		courseCatalog = new CoursesUI();
		myCourseSchedule = new StudentCoursesUI();
		newStudentAccount = new NewAccountUI();
	}

	public static void main(String[] args) {
		getSingleInstance().show();	
	}

	public static LoginUI getLogin() {
		return getSingleInstance().login;
	}

	public static RegistrationUI getRegistrar() {
		return getSingleInstance().registrar;
	}

	public static CoursesUI getAllCourses() {
		return getSingleInstance().courseCatalog;
	}

	public static StudentCoursesUI getMyCourseSchedule() {
		return getSingleInstance().myCourseSchedule;
	}

	public static NewAccountUI getNewStudentAccount() {
		return getSingleInstance().newStudentAccount;
	}

	public void show() {
		System.out.println();
		System.out.println(Constants.DELIMITER + Constants.OPTION_STUDENT_ACCOUNT_LOGIN + Constants.DELIMITER);
		super.display(true);
	}

	protected void displayWelcomeMenu(){
		System.out.println("1. " + Constants.OPTION_ALL_COURSES);
		System.out.println("2. " + Constants.OPTION_STUDENT_ACCOUNT_LOGIN);
		System.out.println("3. " + Constants.OPTION_NEW_STUDENT_ACCOUNT);
		System.out.println("4. " + Constants.OPTION_QUIT);
	}

	protected void displayMainMenu(Student student) {
		StringBuffer welcomeMessage = new StringBuffer(Constants.WELCOME);
		welcomeMessage.append(student.getFirstName()).append(" ").append(student.getLastName());
		System.out.println(welcomeMessage.toString());
		System.out.println();
		System.out.println("1. " + Constants.OPTION_ALL_COURSES);
		System.out.println("2. " + Constants.OPTION_MY_COURSE_SCHEDULE);
		System.out.println("3. " + Constants.OPTION_LOGOUT);
		System.out.println("4. " + Constants.OPTION_QUIT);
	}
	@Override
	protected void display() {
		Student student = getLogin().getStudent();

		if (student != null && getLogin().isLoggedIn()) {
			displayMainMenu(student);
		}
		else {
			displayWelcomeMenu();
		}
	}

	protected void processLoggedInSelection(int option){
		switch (option) {
		case Constants.ALL_COURSES:
			getAllCourses().show();
			break;
		case Constants.MY_COURSE_SCHEDULE:
			getMyCourseSchedule().displayMenu();
			break;
		case Constants.LOGOUT:
			getLogin().setLoggedIn(false);
			getLogin().setStudent(null);
			System.out.println();
			System.out.println(Constants.LOGOUT_SUCCESS);
			display(true);
			break;
		case Constants.QUIT:
			quit();
		default:
			System.out.println(Constants.INVALID_OPTION);
			getSingleInstance().display(true);
			break;
		}
	}

	protected void processNotLoggedInSelection(int option){

		switch (option) {
		case Constants.ALL_COURSES:
			getAllCourses().show();
			break;
		case Constants.STUDENT_ACCOUNT_LOGIN:
			getLogin().show();
			break;
		case Constants.NEW_STUDENT_ACCOUNT:
			getNewStudentAccount().show();
			break;
		case Constants.QUIT:
			quit();
		default:
			System.out.println(Constants.INVALID_OPTION);
			getSingleInstance().display(true);
			break;
		}
	}

	@Override
	protected void select(int option) {
		if (getLogin().isLoggedIn()) {
			processLoggedInSelection(option);
		}
		else {
			processNotLoggedInSelection(option);
		}
	}

	protected static StudentRegistrationSystemUI getSingleInstance() {
		if (mainRegistration == null)
			mainRegistration = new StudentRegistrationSystemUI();
		return mainRegistration;
	}

	private void quit() {
		super.closeInputScanner();
		System.exit(0);
	}
}