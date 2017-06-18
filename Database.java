

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Database {
	
	
	// Methods for accessing the course data
	protected static ArrayList<Course> loadCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			File courseListFile = new File(Constants.COURSE_LIST_FILE_PATH);
			Scanner fileScanner = new Scanner(courseListFile);
			Course tempCourse;
			while (fileScanner.hasNextLine()) {
				String tempString = fileScanner.nextLine();
				String[] courseAttributes = tempString.split(",");
				tempCourse = new Course(courseAttributes[0], courseAttributes[1], courseAttributes[2], courseAttributes[3],courseAttributes[4],new Integer(courseAttributes[5]).intValue(),new Integer(courseAttributes[6]).intValue());
				courses.add(tempCourse);
			}
			fileScanner.close();
			return courses;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
	}

	protected static void saveCoursesAll(ArrayList<Course> regCourseList)
	{
		boolean appendFile = false; // overwrite file

		BufferedWriter buffWriter = null;
		try {
			buffWriter = new BufferedWriter(new FileWriter(Constants.COURSE_LIST_FILE_PATH,appendFile));
			for (Course c: regCourseList) {
				buffWriter.write(  c.getCourseID() + "," +
						c.getStartDate() + "," +
						c.getEndDate()   + "," +
						c.getCourseName() + "," +
						c.getCourseDescription() + "," +
						c.getMaxNumStudents() + "," +
						c.getNumEnrolled() );
				buffWriter.newLine();
			}
			buffWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	protected static ArrayList<Course> loadCourseFile() 
	{
		ArrayList<Course> regCourseList = new ArrayList<Course>();

		try {
			File courseListFile = new File(Constants.COURSE_LIST_FILE_PATH);
			Scanner fileScanner = new Scanner(courseListFile);
			Course tempCourse;
			while (fileScanner.hasNextLine()) {
				String[] courseAttributes = fileScanner.nextLine().split(",");
				tempCourse = new Course(courseAttributes[0], 
						courseAttributes[1], 
						courseAttributes[2], 
						courseAttributes[3],
						courseAttributes[4],
						new Integer(courseAttributes[5]).intValue(),
						new Integer(courseAttributes[6]).intValue());
				regCourseList.add(tempCourse);
			}

			Collections.sort(regCourseList);
			fileScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return regCourseList;
	}

	
	// Methods for accessing the student data
	protected static void saveStudentInfo(Student newStudent) {
		BufferedWriter buffWriter = null;
		try {
			// Open file with append flag set to true will cause string to be appended to file
			buffWriter = new BufferedWriter(new FileWriter(Constants.STUDENT_FILE_PATH,true));
			buffWriter.newLine();
			buffWriter.write(newStudent.toString());
			buffWriter.close();

			System.out.println();
			System.out.println(Constants.NEW_STUDENT_ACCOUNT_SUCCESS + newStudent.getStudentID() + ". " + Constants.SAVE_STUDENT_ID);

			StudentRegistrationSystemUI.getLogin().validateLogin(newStudent.getStudentID(), newStudent.getPassword(), Constants.MAIN_MENU);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	protected static ArrayList<Integer> loadStudentIds() {

		ArrayList<Integer> studentIds = new ArrayList<Integer>();

		try {
			File studentFile = new File(Constants.STUDENT_FILE_PATH);
			FileReader studentReader = new FileReader(studentFile);
			BufferedReader studentBuffReader = new BufferedReader(studentReader);

			String line = null;

			while ((line = studentBuffReader.readLine()) != null) {
				String studentAttributes[] = line.split(",");
				studentIds.add(Integer.parseInt(studentAttributes[0]));
			}

			studentBuffReader.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return studentIds;
	}

	protected static Student getStudent(int id) {

		File inputFile;
		Scanner fileScanner;
		String currentLine = "";
		String[] studentDetails = {};
		String[] emptyStudent = {"0","","","","","","",""};
		Student student = new Student(emptyStudent);
		boolean studentFound = false;

		try {
			inputFile = new File(Constants.STUDENT_FILE_PATH);
			fileScanner = new Scanner(inputFile);

			while (fileScanner.hasNextLine()&& !studentFound) {
				currentLine = fileScanner.nextLine();
				studentDetails = currentLine.split(",");

				// check if input matches information from Student.txt file
				if(Integer.parseInt(studentDetails[0])== id) {
					studentFound = true;
					student =  new Student(	Integer.parseInt(studentDetails[0]),
							studentDetails[1],
							studentDetails[2],
							studentDetails[3],
							studentDetails[4],
							studentDetails[5],
							studentDetails[6],
							studentDetails[7]);
				}
			}
			fileScanner.close();

		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return student;
	}

	// Methods for accessing the registration data
	protected static void saveRegistration(Enrolled record ) 
	{
		BufferedWriter buffWriter = null;
		try {
			// Open file with append flag set to true will cause string to append to file.
			buffWriter = new BufferedWriter(new FileWriter(Constants.REGISTRATION_FILE_PATH,true));
			buffWriter.write(record.getStudentId() + "," + record.getCourseId());
			buffWriter.newLine();
			buffWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	protected static void saveRegistrationsAll(ArrayList<Enrolled> enrolled)
	{
		boolean appendFile = false; // overwrite file

		BufferedWriter buffWriter = null;
		try {
			buffWriter = new BufferedWriter(new FileWriter(Constants.REGISTRATION_FILE_PATH, appendFile));
			for (Enrolled registered : enrolled) {
				buffWriter.write( registered.getStudentId() + "," + registered.getCourseId());
				buffWriter.newLine();
			}
			buffWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static ArrayList<Enrolled> loadRegistrationFile()
	{
		ArrayList<Enrolled> enrolled = new ArrayList<Enrolled>();
		
		try {
			File studentRegListFile = new File(Constants.REGISTRATION_FILE_PATH);
			Scanner fileScanner = new Scanner(studentRegListFile);
			Enrolled tempRegistration;

			enrolled.clear();

			while (fileScanner.hasNextLine()) {
				String[] studentRegAttributes = fileScanner.nextLine().split(",");
				tempRegistration = new Enrolled(studentRegAttributes);
				enrolled.add(tempRegistration);
			}

			Collections.sort(enrolled, new Enrolled() );
			fileScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enrolled;
	}

}
