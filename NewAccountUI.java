
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.*;

// Allow the student to create a new account with auto-generated Student ID.
public class NewAccountUI extends UserInterface {

	private Student newStudent = null;

	public void show() {
		System.out.println();
		System.out.println(Constants.DELIMITER + Constants.OPTION_NEW_STUDENT_ACCOUNT + Constants.DELIMITER);
		display(true);
	}

	@Override
	public void display(boolean hasOptionList) {

		try {
			Scanner inputScanner = UserInterface.getInputScanner();
			String firstName = null, lastName = null, streetAddress = null, city = null, state = null, zip = null, password = null;

			System.out.println();

			System.out.print(Constants.PLEASE_ENTER_YOUR_PROMPT + Constants.FIRST_NAME_PROMPT);
			while((firstName = inputScanner.nextLine().trim()).isEmpty()){
				System.out.println("Sorry, cannot be blank.");
				System.out.print(Constants.PLEASE_ENTER_YOUR_PROMPT + Constants.FIRST_NAME_PROMPT);
			};

			System.out.print(Constants.PLEASE_ENTER_YOUR_PROMPT + Constants.LAST_NAME_PROMPT);
			while((lastName = inputScanner.nextLine().trim()).isEmpty()){
				System.out.println("Sorry, cannot be blank.");
				System.out.print(Constants.PLEASE_ENTER_YOUR_PROMPT + Constants.LAST_NAME_PROMPT);
			};

			System.out.print(Constants.PLEASE_ENTER_YOUR_PROMPT + Constants.STREET_ADDRESS_PROMPT);
			while((streetAddress = inputScanner.nextLine().trim()).isEmpty()){
				System.out.println("Sorry, cannot be blank.");
				System.out.print(Constants.PLEASE_ENTER_YOUR_PROMPT + Constants.STREET_ADDRESS_PROMPT);
			};

			System.out.print(Constants.PLEASE_ENTER_YOUR_PROMPT + Constants.CITY_PROMPT);
			while((city = inputScanner.nextLine().trim()).isEmpty()){
				System.out.println("Sorry, cannot be blank.");
				System.out.print(Constants.PLEASE_ENTER_YOUR_PROMPT + Constants.CITY_PROMPT);
			};

			System.out.print(Constants.PLEASE_ENTER_YOUR_PROMPT + Constants.STATE_PROMPT);
			while((state = inputScanner.nextLine().trim()).isEmpty()){
				System.out.println("Sorry, cannot be blank.");
				System.out.print(Constants.PLEASE_ENTER_YOUR_PROMPT + Constants.STATE_PROMPT);
			};

			System.out.print(Constants.PLEASE_ENTER_YOUR_PROMPT + Constants.ZIP_PROMPT);
			while((zip = inputScanner.nextLine().trim()).isEmpty()){
				System.out.println("Sorry, cannot be blank.");
				System.out.print(Constants.PLEASE_ENTER_YOUR_PROMPT + Constants.ZIP_PROMPT);
			};

			System.out.print(Constants.CREATE_PASSWORD_PROMPT);
			while((password = inputScanner.nextLine().trim()).isEmpty()){
				System.out.println("Sorry, cannot be blank.");
				System.out.print(Constants.CREATE_PASSWORD_PROMPT);
			};

			newStudent = new Student(generateStudentId(), firstName.trim(), lastName.trim(), streetAddress.trim(), city.trim(), state.trim(), zip.trim(), password.trim());
			Database.saveStudentInfo(newStudent);
			
		} catch (InputMismatchException e) {
			System.out.println(this.getClass().getName() + ": Error! " + e.getMessage());
		}
	}

	private int generateStudentId() {

		ArrayList<Integer> studentIds = new ArrayList<Integer>();
		studentIds = Database.loadStudentIds();
		Collections.sort(studentIds);
		
		// Create new student ID by adding 15 to previous max studentID
		int maxStudentID = studentIds.get(studentIds.size() - 1);
		int newStudentID = maxStudentID + 15;

		return newStudentID;
	}
	
	@Override
	protected void display() {
	}

	@Override
	protected void select(int option) {
	}

}