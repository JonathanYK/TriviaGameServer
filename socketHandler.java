package GeneralPackage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

public class socketHandler extends Thread {
	Socket incoming;
	mySQLdb sql;
	
	socketHandler(Socket _in, mySQLdb sql) {
		incoming = _in;
		this.sql = sql; 
	}
	
	public void run() {
		
		//Calling Options
		//1 - Ask for questions -> Ask difficulty
		//2 - Add new user to leaderboard -> should be username and score
		//3 - Ask for top 10 in the leaderboard -> on the correct difficulty

		ArrayList <exportQues> arrlist = new ArrayList <exportQues>();
		String diff = null;
		String username = null;
		int score = 0;
		BufferedReader inFromClient = null;
		DataOutputStream  outToClient = null;
		String selection = null;
		//Declare before try/catch
	    
		try {	//trying to make connection
			inFromClient = new BufferedReader(new InputStreamReader(incoming.getInputStream()));    
			outToClient = new DataOutputStream(incoming.getOutputStream() );
			selection = inFromClient.readLine();	//Get selection from user
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
           try { //Actions after selection
        	   Gson gson = new Gson();
			switch (selection.charAt(0)) {	//Use the selection to understand
			   
				case '1': 	//Get questions
				   diff = inFromClient.readLine();
				   arrlist = sql.exportQueryApi(diff); //Get questions from mysql
				   String strApi = gson.toJson(arrlist); //convert to json
				   outToClient.writeBytes(strApi + "\n"); //send to client
				   break;
				   
				case '2':	//Add new user to leaderboard
				   username = inFromClient.readLine();	//get parameters from client
				   diff = inFromClient.readLine();
				   score = inFromClient.read();
				   sql.queryImportlb(username, diff, score);	//add to mysql
				   break;
				   
				case '3': //Ask for top 10 in the leaderboard
					diff = inFromClient.readLine();	//Set diff to import
					arrlist = sql.queryExportlb(diff);	
					String strlb = gson.toJson(arrlist); //convert json
					 outToClient.writeBytes(strlb + "\n"); //send top10 from same diff to client
					break;
			   }
			//Catch all the exceptions
		} catch (IOException e1) {
			System.out.println("Error in switch case!");
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			incoming.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
