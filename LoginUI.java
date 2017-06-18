

import java.util.Scanner;

public class LoginUI extends UserInterface {

	private Student student = null;
	private boolean isLoggedIn = false;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public void show() {
		System.out.println();
		System.out.println(Constants.DELIMITER + Constants.OPTION_STUDENT_ACCOUNT_LOGIN + Constants.DELIMITER);
		show(Constants.MAIN_MENU);
	}

	public void show(int redirectConsoleId) {
		try {
			Scanner inputScanner = UserInterface.getInputScanner();
			int enteredId;
			String enteredPassword;

			System.out.println();
			System.out.println(Constants.STUDENT_LOGIN_PROMPT);

			// check if student id is in valid format and parse it
			enteredId = parseStudentId(inputScanner);

			System.out.print(Constants.PASSWORD_PROMPT);
			enteredPassword = inputScanner.nextLine().trim();

			validateLogin(enteredId, enteredPassword, redirectConsoleId);
			
		} catch (Exception e) {
			System.out.println(this.getClass().getName() + ": Error! " + e.getMessage());
		}
	}

	@Override
	protected void display() {

	}

	@Override
	protected void select(int option) {
		switch (option) {
		case Constants.REGISTER_COURSE:
			if(isLoggedIn()){
				StudentRegistrationSystemUI.getRegistrar().show(RegistrationUI.REGISTER);
			}
			else {
				System.out.println("You must be logged in to register for a course");
				StudentRegistrationSystemUI.main(null);
			}
			break;
		case Constants.MAIN_MENU:
		default:
			StudentRegistrationSystemUI.main(null);
			break;
		}
	}

	protected void validateLogin(int id, String password, int redirectConsoleId) {
		Student student = Database.getStudent(id);
			if ((student.getStudentID()==id) && (student.getPassword().equals(password))){
				setLoggedIn(true);
				setStudent(student);
				select(redirectConsoleId);
			}
			else {
				System.out.print(Constants.DELIMITER+ Constants.INVALID_LOGIN + Constants.DELIMITER);
				StudentRegistrationSystemUI.main(null);
			}
	}

	private int parseStudentId(Scanner inputScanner) {
		System.out.print(Constants.STUDENT_ID_PROMPT);
		int id = 0;
		try {
			id = Integer.parseInt(inputScanner.nextLine());
		}
		catch (NumberFormatException e) {
			System.out.println(Constants.INVALID_FORMAT);
			id = parseStudentId(inputScanner);
		}
		return id;
	}
}