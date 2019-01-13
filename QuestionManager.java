import java.util.ArrayList;
import java.util.Scanner;

public class QuestionManager {
	
	private static final String[] operants = {"","+","-","+","-","x","x"};
	private ArrayList<Integer> first;
	private ArrayList<Integer> second;
	private ArrayList<Integer> answers;
	private int questionType;
	private int questionNum;
	Scanner scanner = new Scanner(System.in);
	
	
	/**
	 * 构造函数,初始化三个ArrayList，并产生题目
	 * @param questionType 题目类型
	 * @param questionNum 题目数量
	 */
	public QuestionManager(int questionType, int questionNum){
		first = new ArrayList<Integer>();
		second = new ArrayList<Integer>();
		answers = new ArrayList<Integer>();
		this.questionType = questionType;
		this.questionNum = questionNum;
		generateQuestion();
	}
	
	public Report getReportInstance() {
		return new Report(first, second, answers, questionType);
	}
	
	private void generateQuestion() {
		switch(questionType) {
			case 1:
				fill(2,2);
				for(int i = 0; i < questionNum; i ++) {
					int ans = first.get(i) + second.get(i);
					answers.add(ans);
				}
				break;
			case 2:
				fill(2,2);
				for(int i = 0; i < questionNum; i ++) {
					int ans = first.get(i) - second.get(i);
					answers.add(ans);
				}
				break;
			case 3:
				fill(3,3);
				for(int i = 0; i < questionNum; i ++) {
					int ans = first.get(i) + second.get(i);
					answers.add(ans);
				}
				break;
			case 4:
				fill(3,3);
				for(int i = 0; i < questionNum; i ++) {
					int ans = first.get(i) - second.get(i);
					answers.add(ans);
				}
				break;
			case 5:
				fill(1,2);
				for(int i = 0; i < questionNum; i ++) {
					int ans = first.get(i) * second.get(i);
					answers.add(ans);
				}
				break;
			case 6:
				fill(2,2);
				for(int i = 0; i < questionNum; i ++) {
					int ans = first.get(i) * second.get(i);
					answers.add(ans);
				}
				break;
			
		}
	}
	
	private void fill(int firstLength, int secondLength) {
		for(int i = 0; i < questionNum; i++) {
			int firstNum = (int)((Math.random()*(Math.pow(10, firstLength)-Math.pow(10, firstLength-1)))+Math.pow(10, firstLength-1));
			int secondNum = (int)((Math.random()*(Math.pow(10, firstLength)-Math.pow(10, firstLength-1)))+Math.pow(10, firstLength-1));
			first.add(firstNum);
			second.add(secondNum);
		}
	}
	
	public Report startTest() {
		String oper = operants[questionType];
		
		Report report = getReportInstance();
		
		ready_go();
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < questionNum; i++) {
			System.out.println(first.get(i)+" "+ oper+" "+second.get(i)+" = ");
			int userAnswer = getUserAnswer();
			if(answers.get(i).equals(userAnswer)) {
				report.addCorrect();
			}else {
				report.addWrong(i, userAnswer);
			}
		}
		long endTime = System.currentTimeMillis();
		long timeUsed = endTime - startTime;
		
		report.setTimeInMillis(timeUsed);
		return report;
	}
	
	private int getUserAnswer() {
		String ans = scanner.nextLine();
		int answer;
		try {
			answer = Integer.parseInt(ans);
		}catch( NumberFormatException e) {
			System.out.println("输入格式不正确，请输入纯数字！");
			answer = getUserAnswer();
		}
		return answer;
	}
	
	private static void ready_go() {
		try {
			Thread.sleep(800);
			System.out.println("\t\t准备");
			Thread.sleep(1000);
			System.out.println("\t\t3");
			Thread.sleep(1000);
			System.out.println("\t\t2");
			Thread.sleep(1000);
			System.out.println("\t\t1");
			Thread.sleep(1000);
			System.out.println("\t\t开始！！");
		} catch (Exception e) {

		}
	}
	
	
}
