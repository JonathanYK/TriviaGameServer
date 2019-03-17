package GeneralPackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Scanner;

import com.google.gson.Gson;

import java.sql.SQLException;
import java.sql.Statement;

public class Terminal extends mySQLdb {
	
	public static final DBAccessInterface TerminalConnection = new mySQLdb();
		
	public static void main(String args[]) throws NoSuchAlgorithmException {
	Scanner sc = new Scanner(System.in);
	//General Connection (encrypted) based on the interface:
	System.out.println(TerminalConnection.connect(Decryptor.Decrypt("DfHDuWgegpdkOwBL2kAyJQ=="), Decryptor.Decrypt("UvuZ3Q+MCNLuFrotRvCDwQ=="), Decryptor.Decrypt("GZeU/Hjjvz3ZbsoBqNHp+A=="), Decryptor.Decrypt("nrP6hmGba2fGvpvtxzGJPg=="), Decryptor.Decrypt("EzQrYJCpoiQQmOaGC9oHBA==")));
	
	System.out.println("\n         -=TERMINAL=-    ");
	System.out.println("In Order To Add Questions Manually, Please Press 1");
	System.out.println("In Order To Add Questions From www.opentdb.com API, Please Press 2");
	System.out.println("In Order To Cancel And Exit, Please Press 0");

		switch (sc.nextLine()) {
		case "1":  // add new question manually:
					AddNewRow();
					break;
				
		case "2":	// add new question using apiImport class
					apiImport.main(args);
					break;
		
		case "0":	System.out.println("BYE");
					System.exit(1);
		
		}
	}
	//The function below will be used in order to add new questions manually:
	public static int AddNewRow () {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please insert the question text:");
		String question = scan.nextLine();
		

		System.out.println("Please insert the category of the question: ");
		String category = scan.nextLine();
		
		System.out.println("Please insert the question level:(easy / medium / hard) ");
		String difficulty = scan.nextLine();
		
		System.out.println("Please insert the correct answer :");
		String correct_answer = scan.nextLine().toString();
		
		System.out.println("Please insert the first wrong answer :");
		String incorrect_answer1 = scan.nextLine().toString();
		
		System.out.println("Please insert the second wrong answer :");
		String incorrect_answer2 = scan.nextLine().toString();
		
		System.out.println("Please insert the third wrong answer :");
		String incorrect_answer3 = scan.nextLine().toString();

		
		System.out.println("The question will imported to the DB with the details below:\n");
		System.out.println("Qestion text: " + question +"\ncategory: " + category +  "\nCorrect Answer: " + correct_answer + "\nFirst Incorrect Answer: " + incorrect_answer1 + "\nSecond Incorrect Answer: " + incorrect_answer2 + "\nThird Incorrect Answer: " + incorrect_answer3 + "\n\n Confirm? 1-Yes 2-No");
		int ansConf = scan.nextInt();
		if(ansConf == 1) {
			try {
				
				TerminalConnection.query("INSERT INTO triviagame.realquestions (question, category, difficulty, correct_answer, incorrect_answer1, incorrect_answer2, incorrect_answer3) VALUES ('"+ question +"' ,'"+ category +"' ,'"+ difficulty +"' ,'"+ correct_answer +"' ,'"+ incorrect_answer1 +"' ,'"+ incorrect_answer2 +"' ,'"+ incorrect_answer3 +"')","add");
				AddNewRow();
			}
			
			catch (Exception err) {
				System.out.println("The row not added!");
				err.getMessage();
			}	
		}
		else
			AddNewRow();
		return 0;
	}
}
