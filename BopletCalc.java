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
		System.out.println("\n游戏结束，开始新一轮速算请输入0");
		System.out.println("输入结束请按回车键确认");
		return sc.nextLine();
	}
	
	public void welcomeWords() {
		System.out.println("\t\t\t\t欢迎使用BopletCalc!\n\n");
	}
	
	public int setQuestionNum() {
		int questionNum;
		try {
			System.out.println(
					  "请输入您想做的该类型的题目数量：\n"
			);
			String choice = this.s.nextLine();
			questionNum = Integer.parseInt(choice);
		} catch (NumberFormatException e) {
			System.out.println("\t\t\t\t您的输入格式不正确，请重新输入！");
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
					  "两位数加两位数-->请输入1\n"
					+ "两位数减两位数-->请输入2\n"
					+ "三位数加三位数-->请输入3\n"
					+ "三位数减三位数-->请输入4\n"
					+ "一位数乘两位数-->请输入5\n"
					+ "两位数乘两位数-->请输入6\n"
					+ "输入结束请按回车键确认");
			String choice = this.s.nextLine();
			questionType = Integer.parseInt(choice);
			if(questionType > 6 || questionType < 1) throw new Exception();
		} catch (Exception e) {
			System.out.println("\t\t\t\t您的输入格式不正确，请重新输入！");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			questionType = selectQuestionType();
		}
		return questionType;
		
	}

		
		
	

}
