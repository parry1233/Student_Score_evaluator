/**
 *	HW3 Tester
 *	Usage: 
 *		java Tester [csv file path] [sortKey](Optional)
 *		Example:
 *			java Tester score1.csv
 *			java Tester score1.csv midterm (Sort by midterm)
 *
 *	@author Vincent
 */
package score_evaluater;
import java.util.*;
import java.io.*;

class Tester {
	public static void main(String[] args) {
		ArrayList<Student> students = new ArrayList<>();
		String output = "";

		if(args.length < 1) {
			System.out.printf("Usage: java Tester [csv file path] [sortKey](Optional)\n" +
							"Example:\n" +
							"\tjava Tester score1.csv\n" +
							"\tjava Tester score1.csv midterm (Sort by midterm)\n");
			return;
		}
		try(InputStream csv = new FileInputStream(args[0])) {
			students = parse(csv);
		}
		catch(IOException e) {
			System.err.println("File not found");
			return;
		}

		for(Student stu : students) {
			stu.setStuTtlScore();
			stu.setStuAvgScore();
			stu.setStuGpa();
		}

		String[] colName = {"Student ID", "Midterm", "PreQuiz", "LabQuiz", "HW1", "Total", "Avg.", "GPA"};
		 /*
		 * students = Student.scoreSorting(students,"midterm");
		 * Bonus Block
		 *
		 *  If you haven't implement scoreSorting method, you should implement an empty one.
		 *
		 */
		if(args.length > 1) {
			students = Student.scoreSorting(students, args[1]);
		}

		System.out.printf("%-15s%-10s%-10s%-10s%-10s%-10s%-10s%-10s\n", (Object[])colName);

		for(Student stu: students) {
			String stuId = stu.getId();
			double[] scores = stu.getStuScore();
			double total = stu.getStuTtlScore();
			double avg = stu.getStuAvgScore();
			String gpa = stu.getStuGpa();

			System.out.printf("%-15s%-10.0f%-10.0f%-10.0f%-10.0f%-10.2f%-10.2f%-10s\n", stuId,
				scores[0], scores[1], scores[2], scores[3], total, avg, gpa);
		}

		/** Avg and Std of subject **/
		double[] subjectAvg = Student.allStuAvgScore(students);
		double[] subjectStd = Student.allStuStd(students);
		System.out.printf("%-15s%-10.2f%-10.2f%-10.2f%-10.2f\n", "Avg.", subjectAvg[0], subjectAvg[1], subjectAvg[2], subjectAvg[3]);
		System.out.printf("%-15s%-10.2f%-10.2f%-10.2f%-10.2f\n", "Std.", subjectStd[0], subjectStd[1], subjectStd[2], subjectStd[3]);

		System.out.printf("\nGrade Distribution\n");
		Student.gradeDistribution(students);
	}
	public static ArrayList<Student> parse(InputStream src) throws IOException {
		ArrayList<Student> students = new ArrayList<Student>();
		String delim = ",";
				
		try(InputStream input = new BufferedInputStream(src)) {
			Scanner sc = new Scanner(input);
			String row;
			double[] columns;
			String[] head = sc.nextLine().split(delim);

			while(sc.hasNextLine()) {
				row = sc.nextLine();
				String[] tmp = row.split(delim, 2);
				String id = tmp[0];
				double[] scores = Arrays.stream(tmp[1].split(delim)).mapToDouble(Double::parseDouble).toArray();
				students.add(new Student(id, scores));
			}
		}
		catch(Exception e) {

		}
		return students;
	}
}