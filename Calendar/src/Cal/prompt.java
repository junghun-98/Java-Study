package Cal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

class Prompt {
	private Scanner scan;
	private HashMap<Date, String> planMap;
	
	public Prompt() {
		planMap = new HashMap<>();
	}

	public void StartCalendar() {
		while (true) {
			scan = new Scanner(System.in);
			
			System.out.println("+----------------------+");
			System.out.println("| 1. 일정 등록");
			System.out.println("| 2. 일정 검색");
			System.out.println("| 3. 달력 보기");
			System.out.println("| q. 종료");
			System.out.println("+----------------------+");
			System.out.println("명령 (1, 2, 3, q)");

			String order;
			order = scan.next();

			if (order.equals("1"))
				ScheduleRegistration();
			else if (order.equals("2"))
				ScheduleSearch();
			else if (order.equals("3"))
				ShowCalendar();
			else if (order.equals("q"))
				break;
			else
				System.out.println("다시 입력하세요.");
		}
	}

	private int getDay(int m, int y) {
		if (m == 2) {
			if (y % 4 == 0 || y % 400 == 0)
				return 29; // 윤년
			else
				return 28;
		}

		else if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12)
			return 31;

		else if (m == 4 || m == 6 || m == 9 || m == 11)
			return 30;

		return 0;
	}

	private void printCalendar(int m, int y) {

		int day = 0;
		int count = 0; // 출력할 공백 개수
		int printcount = 0; // 출력 횟수

		day = getDay(m, y);
		count = FirstWeek(m, y);

		System.out.printf("   <<%4d년%3d월>>\n", y, m);
		System.out.println("---------------------");
		System.out.println(" SU MO TU WE TH FR SR");

		// 시작 요일에 따라 공백 출력
		for (int i = 0; i < count; i++) {
			System.out.printf("   ");
			printcount++;
		}

		// 날짜 출력
		for (int i = 1; i <= day; i++) {
			System.out.printf("%3d", i);
			printcount++;
			if (printcount % 7 == 0)
				System.out.println();
		}
		System.out.println();
	}

	// 시작 요일 판정
	private int FirstWeek(int month, int year) {
		int sum = 0;
		int first = 0;

		for (int i = 1583; i < year; i++) {
			if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) {
				// 윤년이라면
				sum += 2;
			} else {
				// 평년이라면
				sum += 1;
			}
		}

		first = (sum + 6) % 7;

		for (int j = 1; j < month; j++) {
			first += getDay(j, year) % 7;
		}

		return first % 7;
	}
	
	// 일정입력
	private void ScheduleRegistration() {
		scan = new Scanner(System.in).useDelimiter("\n");
		
		System.out.println("[일정 등록] 날짜를 입력하세요.");
		System.out.print("(yyyy-MM-dd) : ");
		String date = scan.next();
		
		System.out.println("일정을 입력해 주세요.");
		String plan = ""; 
		
		plan = scan.next();		
		
		SaveSchedule(date, plan);
	}
	
	private void SaveSchedule(String d, String plan){
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(d);
			planMap.put(date, plan);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("날짜를 잘 못 입력하셨습니다.");
		}
	}
	
	private void ScheduleSearch() {
		scan = new Scanner(System.in);
		
		System.out.println("[일정 검색] 날짜를 입력하세요.");
		System.out.print("(yyyy-MM-dd) : ");
		String date = scan.next();
		
		Search(date);
	}

	private void Search(String d) {
		try {
			Date date;
			date = new SimpleDateFormat("yyyy-MM-dd").parse(d);
			String plan = planMap.get(date);

			if(plan != null) System.out.println("\n" + plan + "\n");
			else System.out.println("\n일정이 없습니다.\n");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ShowCalendar() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int month, year;

		System.out.print("연도를 입력하세요\nYEAR>");
		year = scan.nextInt();

		System.out.print("월을 입력하세요\nMONTH>");
		month = scan.nextInt();

		if (month < 1 || month > 12) {
			System.out.println("해당 월은 존재 하지 않습니다.");
			return;
		}

		printCalendar(month, year);
	}
}
