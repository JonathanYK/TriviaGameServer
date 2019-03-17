package GeneralPackage;

public class exportQues {
	private String question, category, difficulty,correct_answer,incorrect_answer1,incorrect_answer2, incorrect_answer3;

	public exportQues(String question, String category, String difficulty, String correct_answer,
			String incorrect_answer1, String incorrect_answer2, String incorrect_answer3) {
		this.question = question;
		this.category = category;
		this.difficulty = difficulty;
		this.correct_answer = correct_answer;
		this.incorrect_answer1 = incorrect_answer1;
		this.incorrect_answer2 = incorrect_answer2;
		this.incorrect_answer3 = incorrect_answer3;
	}
	public String getQuestion() {
		return question;
	}
	public String getCategory() {
		return category;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public String getCorrect_answer() {
		return correct_answer;
	}
	public String getIncorrect_answer1() {
		return incorrect_answer1;
	}
	public String getIncorrect_answer2() {
		return incorrect_answer2;
	}
	public String getIncorrect_answer3() {
		return incorrect_answer3;
	}	
}
