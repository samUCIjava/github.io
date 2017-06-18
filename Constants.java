
public final class Constants {

	public static final int ALL_COURSES 			= 1;
	public static final int REGISTER_COURSE 		= 1;
	public static final int UNREGISTER_COURSE 		= 1;
	public static final int STUDENT_ACCOUNT_LOGIN 	= 2;
	public static final int MY_COURSE_SCHEDULE 		= 2;
	public static final int MAIN_MENU 				= 2;
	public static final int NEW_STUDENT_ACCOUNT 	= 3;
	public static final int LOGOUT 					= 3;
	public static final int QUIT 					= 4;

	public static final String DELIMITER = " **---------------------** ";
	public static final String MAIN_TITLE = " Team A Student Registration System ";
	public static final String WELCOME = "Welcome ";

	public static final String OPTION_ALL_COURSES = " All Courses ";
	public static final String OPTION_ADD_COURSE = " Register for a Course ";

	public static final String OPTION_STUDENT_ACCOUNT_LOGIN = " Student Account Login ";
	public static final String OPTION_MY_COURSE_SCHEDULE = " My Course Schedule ";
	public static final String OPTION_DROP_COURSE = " Drop Course ";
	public static final String OPTION_NEW_STUDENT_ACCOUNT = " New Student Account ";
	public static final String OPTION_LOGOUT = " Logout ";
	public static final String OPTION_BACK_MAIN = " Back to" + MAIN_TITLE;
	public static final String OPTION_QUIT = " Quit ";

	public static final String SELECT_PROMPT = "Please select an option, then press ENTER: ";
	public static final String PLEASE_ENTER_YOUR_PROMPT = "Please enter your ";
	public static final String STUDENT_LOGIN_PROMPT = PLEASE_ENTER_YOUR_PROMPT + "Student ID and Password";
	public static final String STUDENT_ID_PROMPT = "Student ID: ";
	public static final String PASSWORD_PROMPT = "Password: ";
	public static final String COURSE_ID_PROMPT = "Please enter the Course ID: ";
	public static final String FIRST_NAME_PROMPT = "First Name: ";
	public static final String LAST_NAME_PROMPT = "Last Name: ";
	public static final String STREET_ADDRESS_PROMPT = "Street Address: ";
	public static final String CITY_PROMPT = "City: ";
	public static final String STATE_PROMPT = "State (2-letter initials): ";
	public static final String ZIP_PROMPT = "Zip Code: ";
	public static final String CREATE_PASSWORD_PROMPT = "Please create your Password: ";

	public static final String LOGOUT_SUCCESS = "You have successfully logged out!";
	public static final String NEW_STUDENT_ACCOUNT_SUCCESS = "Your account has been created and your Student ID is ";
	public static final String SAVE_STUDENT_ID = "Please save your Student ID.";

	public static final String REQUIRES_LOGIN = " (Requires Login)";

	public static final String INVALID_OPTION = "\nInvalid Option. Please select a number from the list.";
	public static final String INVALID_FORMAT = "\nInvalid Format. Please enter a number.";
	public static final String INVALID_LOGIN = "Invalid Student ID or Password ";

	public static final String STUDENT_FILE_PATH = "data/Student.txt";
	public static final String COURSE_LIST_FILE_PATH = "data/CourseList.txt";
	public static final String REGISTRATION_FILE_PATH = "data/Registration.txt";
}