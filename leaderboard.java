package GeneralPackage;


import java.util.Scanner;

public class leaderboard {
	
	private String username;
	private String difficulty;
	private int score;
	
	public leaderboard(String username, String difficulty, int score) {
		this.username = username;
		this.difficulty = difficulty;
		this.score = score;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}


