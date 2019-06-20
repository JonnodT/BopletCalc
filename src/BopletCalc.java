import java.util.Scanner;

public class BopletCalc {
	
	public Scanner s;

	public static void main(String[] args) {
		BopletCalc game = new BopletCalc();
		game.s = new Scanner(System.in);
		game.start();
		
	}
	
	public void start() {
		welcomeWords();
		String condi = "0";
		while("0".equals(condi)) {
			int questionType = selectQuestionType();
			int questionNum = setQuestionNum();
			QuestionManager manager = new QuestionManager(questionType,questionNum);
			Report report = manager.startTest();
			report.printReport();
			condi = replay();
		}
	}
	
	public String replay() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nGame Over. Enter 0 to restart.");
		return sc.nextLine();
	}
	
	public void welcomeWords() {
		System.out.println("\t\t\t\tWelcome to BopletCalc!\n\n");
	}
	
	public int setQuestionNum() {
		int questionNum;
		try {
			System.out.println(
					  "Pleaes select problem type from one of the categories below：\n"
			);
			String choice = this.s.nextLine();
			questionNum = Integer.parseInt(choice);
		} catch (NumberFormatException e) {
			System.out.println("\t\t\t\tIncorrect input format！");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			questionNum = setQuestionNum();
		}
		return questionNum;
	}
	
	public int selectQuestionType() {
		int questionType;
		try {
			System.out.println(
					  "2 digits plus 2 digits-->Enter 1\n"
					+ "2 digits minus 2 digits-->Enter 2\n"
					+ "3 digits plus 3 digits-->Enter 3\n"
					+ "3 digits minus 3 digits-->Enter 4\n"
					+ "1 digit times 2 digits-->Enter 5\n"
					+ "2 digits times 2 digits-->Enter 6\n"
					);
			String choice = this.s.nextLine();
			questionType = Integer.parseInt(choice);
			if(questionType > 6 || questionType < 1) throw new Exception();
		} catch (Exception e) {
			System.out.println("\t\t\t\tIncorrect input format！");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			questionType = selectQuestionType();
		}
		return questionType;
		
	}

		
		
	

}
