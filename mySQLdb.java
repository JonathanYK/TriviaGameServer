package GeneralPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import GeneralPackage.apiImport.question;

public class mySQLdb implements DBAccessInterface {

	private static Connection Connection;
	
	@Override
	//Basic connection:
	public String connect(String IP, String Port, String DBname, String User, String Pass) {
		String MySQLConnDetails = "jdbc:mysql://" + IP + ":" + Port + "/" + DBname + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; // mySQL connection method
		try {
			Connection = DriverManager.getConnection(MySQLConnDetails,User,Pass); //try to connect to the DB using the provided user and pass.
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "The connection was successfully established with mySQL database";
	}
	
	@Override
	public void query(String query, String action) throws Exception {
		String output = "SQL Output error :(";
		try {
			Statement NewStatement = Connection.createStatement();
			if(action == "print") {
				ResultSet MyResult = NewStatement.executeQuery(query);
				while (MyResult.next()) {
					System.out.println("The Question Text Is: " + MyResult.getString("question") + "\nThe Category Of the Question: " + MyResult.getString("category")
					+ "\nThe Difficulty Of The Question Is:" + MyResult.getString("difficulty") + "\nThe Correct Answer Is: " + MyResult.getString("correct_answer") + "/The First Incorrect Answer is:" + MyResult.getString("incorrect_answer1")
					+ "\nThe Second Incorrect Answer Is: " + MyResult.getString("incorrect_answer2") + "\nThe Third Incorrect Answer Is: " + MyResult.getString("incorrect_answer3") + "\n------------------------");	 
					output = "Results are above";
				}
			}
			if(action == "add") { // add new questions to the db
				int myResult = NewStatement.executeUpdate(query);
				output = Integer.toString(myResult);
				System.out.println("Row added successfuly!");
			}
			
		} catch (SQLException err) {
			System.out.println("SQL Output error :(");
			err.printStackTrace();
			throw new Exception();
		}
		
	}
	
	public void queryImportlb(String user, String diff, int score) throws Exception {
		try {
			Statement NewStatement = Connection.createStatement();
			int myResult = NewStatement.executeUpdate("INSERT INTO triviagame.leaderboard (username, difficulty, score) VALUES ('"+ user +"' ,'"+ diff +"' ,'"+ score +"')");
			System.out.println("Row added successfuly!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList queryExportlb(String diff) {
		ArrayList<leaderboard> arrListlb = new ArrayList<leaderboard>();
		try {
			Statement NewStatement = Connection.createStatement();
			ResultSet MyResult = NewStatement.executeQuery("SELECT * FROM triviagame.leaderboard where difficulty=" + "'" + diff + "'" + " order by score desc limit 10;"); // full query including the difficulty from the user ordered by descending score.
			while(MyResult.next()) {
				arrListlb.add(new leaderboard(MyResult.getString("username"),MyResult.getString("difficulty"),MyResult.getInt("score"))); // adding the questions to the arrListApi one-by-one.
				}
		}
		catch (Exception e) {
			System.out.println("The export of the leaderboard failed! The reason is: \n" + e);
		}
		return arrListlb;
	}
		
	
	
	public ArrayList <exportQues> exportQueryApi (String diff) {
		//Get question in case 1 to user
		ArrayList<exportQues> arrListApi = new ArrayList<exportQues>();
		try {
			Statement NewStatement = Connection.createStatement();
			ResultSet MyResult = NewStatement.executeQuery("SELECT * FROM triviagame.realquestions where difficulty=" + "'" + diff + "'" + "limit 10"); // full query including the difficulty from the user.
			while(MyResult.next()) { //add to arraylist
				arrListApi.add(new exportQues(MyResult.getString("question"),MyResult.getString("category"),MyResult.getString("difficulty"),MyResult.getString("correct_answer"),MyResult.getString("incorrect_answer1"),MyResult.getString("incorrect_answer2"),MyResult.getString("incorrect_answer3"))); // adding the questions to the arrListApi one-by-one.
				}
		}
		catch (Exception e) {
			System.out.println("The export to api failed! The reason: \n" + e);
		}
		return arrListApi;
	}
		
	@Override
	public String disconnect() {
		
		String Output = "Disconnection Failed :(";
		try {
			Connection.close();
			Output = "Disconnected";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Output;
	}
}
