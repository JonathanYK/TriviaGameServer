package GeneralPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


public class apiImport extends mySQLdb {
	
	public class question {
		private String category;
		private String difficulty;
		private String question;
		private String correct_answer;
		private String[] incorrect_answers;	
		
		@Override
		public String toString() {
			
			 String fullResStr = "\nThe Question: " + question +"\nThe difficulty of the questions is: " + difficulty +  "\nThe correct answer is: " + correct_answer + "\nThe wrong answers are: ";
		      for (int i=0; i< incorrect_answers.length; i++) {
		    	  fullResStr += incorrect_answers[i] + ", ";
		        }
		      return fullResStr.substring(0,fullResStr.length()-2);
		}	
	
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//General Connection (encrypted) based on the interface:
			DBAccessInterface SQLConnApi = new mySQLdb(); 
			try {
				System.out.println(SQLConnApi.connect(Decryptor.Decrypt("DfHDuWgegpdkOwBL2kAyJQ=="), Decryptor.Decrypt("UvuZ3Q+MCNLuFrotRvCDwQ=="), Decryptor.Decrypt("GZeU/Hjjvz3ZbsoBqNHp+A=="), Decryptor.Decrypt("nrP6hmGba2fGvpvtxzGJPg=="), Decryptor.Decrypt("EzQrYJCpoiQQmOaGC9oHBA==")));
				} catch (Exception e) {
				e.printStackTrace();
			}
			
			//Import questions using the API - with spcified diff:
			System.out.println("Please enter the amount of questions you wish to import to the DB: ");
			String quesAmount = sc.nextLine();			
			String urlStr =null ;
			System.out.println("In what level the questions will be ? \n    1-Easy, 2-Medium, 3-Hard");
			int level = sc.nextInt();
			switch (level) {
			case 1:  urlStr ="https://opentdb.com/api.php?type=multiple&amount=" + quesAmount + "&difficulty=easy";
			break;
			case 2: urlStr ="https://opentdb.com/api.php?type=multiple&amount=" + quesAmount + "&difficulty=medium";
			break;
			case 3: urlStr ="https://opentdb.com/api.php?type=multiple&amount=" + quesAmount + "&difficulty=hard";
			break;
			}

			
			BufferedReader in = null;
			try {
				URL questionsUrl = new URL(urlStr);   
				in = new BufferedReader(new InputStreamReader(questionsUrl.openStream()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//Creating arraylist of the questions, then parse as JSON array:
		    JsonParser prsr = new JsonParser();
		    JsonObject obj = prsr.parse(in).getAsJsonObject();
		    JsonArray jArr = obj.getAsJsonArray("results");
		    Type listType = new TypeToken<List<question>>() {}.getType();
		    GsonBuilder builder = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping();
		    Gson gson = builder.create();
		    List<question> listQuestions = gson.fromJson(jArr, listType);
			question q;
			int j = 0;
			
			for(int i = 0; i<Integer.parseInt(quesAmount); i++) {
				j = i+1;
				System.out.println("\nThe " + j + " question that will be imported:");
				q = (question) listQuestions.get(i);
				System.out.println(q.toString());
			}
			System.out.println("Do you confirm? 1-Yes, 2-No");
			int ans = sc.nextInt();
			int error_counter = 0;
				if(ans == 1) {
					for(int i = 0; i<Integer.parseInt(quesAmount); i++) {		
						q = (question) listQuestions.get(i);					
						
						try {
							SQLConnApi.query("INSERT INTO triviagame.realquestions (question, category, difficulty, correct_answer, incorrect_answer1, incorrect_answer2, incorrect_answer3) VALUES ('"+ q.question +"' ,'"+ q.category +"' ,'"+ q.difficulty +"' ,'"+ q.correct_answer+"' ,'"+ q.incorrect_answers[0] +"' ,'"+ q.incorrect_answers[1] +"' ,'"+ q.incorrect_answers[2] +"')","add");
						} catch (Exception e) {
							e.printStackTrace();
							error_counter++;
						}
					}
					System.out.println("Errors amount while adding to SQL: " + error_counter);
				}
	}
}	
	
	
	

