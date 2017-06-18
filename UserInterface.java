

import java.util.InputMismatchException;
import java.util.Scanner;

// Allow the subclass to display a list of options and get user input.
public abstract class UserInterface {

	private static Scanner input;

	public UserInterface() {
		input = new Scanner(System.in);
	}

	public static Scanner getInputScanner() {
		return input;
	}

	public static void closeInputScanner() {
		if (input != null)
			input.close();
	}

	public void display(boolean hasOptionList) {
	if (hasOptionList) {
			System.out.println();
			display();
			getSelection();
		}
	}

	private void getSelection() {
		try {
			parseSelection();
		} catch (InputMismatchException e) {
			System.out.println(this.getClass().getName() + ": Error! " + e.getMessage());
		}
	}

	private void parseSelection() {
		System.out.print(Constants.SELECT_PROMPT);

		int option = 0;
		try {
			option = Integer.parseInt(input.nextLine());
			select(option);
		}
		catch (NumberFormatException e) {
			System.out.println(Constants.INVALID_FORMAT);
			getSelection();
		}
	}

	// Subclasses need to implement the following methods
	abstract void display();
	abstract void select(int option);
}