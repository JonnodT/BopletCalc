import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Report {
	
	private static final String[] operants = {"","+","-","+","-","x","x"};
	
	private int correctNum;     //做对的题数
	private int wrongNum;       //做错的题数
	private long timeInMillis;  //用时 单位：毫秒
	
	
	
	private ArrayList<Integer> first;
	private ArrayList<Integer> second;
	private ArrayList<Integer> answers;
	private int questionType;
	
	private HashMap<Integer,Integer> wrong;
	
	
	public Report( ArrayList<Integer> first, ArrayList<Integer> second,
			ArrayList<Integer> answers, int questionType) {
		this.correctNum = 0;
		this.wrongNum = 0;
		this.first = first;
		this.second = second;
		this.answers = answers;
		this.questionType = questionType;
		wrong = new HashMap<Integer,Integer>();
	}
	
	/**
	 * 获取题型的字符表达
	 * @param questionType
	 * @return
	 */
	public String getType(int questionType) {
		switch(questionType) {
		case 1:
			return "两位数加两位数";
		case 2:
			return "两位数减两位数";
		case 3:
			return "三位数加三位数";
		case 4:
			return "三位数减三位数";
		case 5:
			return "一位数乘两位数";
		case 6:
			return "两位数乘两位数";
		}
		return null;
	}
	
	/**
	 * 
	 * @return 一个字符串，表示正确率，格式是 **%
	 */
	public String getCorrectRate() {
		DecimalFormat df = new DecimalFormat("#.00");
		double result = (double)correctNum/((double)correctNum+(double)wrongNum);
		result *= 100;
		return df.format(result) + "%";
	}
	 
	/**
	 * 表示做对了一道题
	 */
	public void addCorrect() {
		this.correctNum += 1;
	}
	
	/**
	 * 表示做错了一道题
	 */
	public void addWrong(int index, int userAnswer) {
		wrong.put(index, userAnswer);
		this.wrongNum+=1;
	}
	
	/**
	 * 
	 * @return 表示本次做完所有题目用时的字符串
	 * 格式是  **小时**分**秒
	 */
	public String getTime() { 
		long second = timeInMillis/1000;
		if(second < 60) {
			return second+"秒";
		}else if(second >= 60 && second < 3600) {
			long min = second/60;
			second = second%60;
			return min + "分" + second +"秒";
		}else if(second >= 3600) {
			long min = second/60;
			long hour = min/60;
			min = min%60;
			second = second/60;
			return hour +"小时" +min + "分钟" + second +"秒"; 
		}
		return null;
	}
	
	/**
	 * 答题结束之后在主程序中设置本次答题所用的毫秒数
	 * @param timeInMillis
	 */
	public void setTimeInMillis(long timeInMillis) {
		this.timeInMillis = timeInMillis;
	}
	
	public String getSpeed() { //用字符形式返回一个两位小数，表示平均每道题用的秒数
		DecimalFormat df = new DecimalFormat("#.00");
		double result = (double)timeInMillis/(double)(wrongNum+correctNum)/1000;
		return df.format(result) + "秒/题";
	}
	
	public void printReport() {
		fakeAnalysis();
		int total = this.correctNum + this.wrongNum;
		System.out.println("\t\t\t以下是您本次的速算报告");
		System.out.println("您总共做了" + total +"道" + getType(questionType)+"速算题目");
		System.out.println("总用时:"+getTime());
		System.out.println("平均速度:"+ getSpeed());
		System.out.println("正确率:"+ getCorrectRate()+"\n");
		if(wrongNum!=0) {
			printErrors();
		}
		
		
	}
	
	private void printErrors() {
		String opr = operants[questionType]; //获得运算符
		System.out.println("以下是您这次没有做对的题目：");
		Set<Integer> keyset = wrong.keySet();
		for(Integer key : keyset) {
			System.out.println(first.get(key)+" "+opr+" "+second.get(key) + " =");
			System.out.println("您的答案:"+wrong.get(key)+"        "+"正确答案:"+answers.get(key));
		}
		
		
	}
	
	private void fakeAnalysis() {
		try {
			Thread.sleep(500);
			System.out.print("恭喜您，完成所有题目！");
			Thread.sleep(1200);
			System.out.print("正在生成报告");
			Thread.sleep(1000);
			System.out.print(".");
			Thread.sleep(1000);
			System.out.print(".");
			Thread.sleep(1000);
			System.out.println(".\n\n\n");

			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
