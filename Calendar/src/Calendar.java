import java.util.Scanner;

public class Calendar {
	public void printCalendar(int m, int y, String d) {
		
		int day = 0;		
		int count = 0;		//출력할 공백 개수
		int printcount = 0; //출력 횟수

		if (m == 2) {
			if (y % 4 == 0 || y % 400 == 0)
				day = 29; // 윤년
			else
				day = 28;
		}

		else if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12)
			day = 31;

		else if (m == 4 || m == 6 || m == 9 || m == 11)
			day = 30;
		count = FirstWeek(d);
		
		if (count != -1) {
			System.out.printf("   <<%4d년%3d월>>\n", y, m);
			System.out.println("---------------------");
			System.out.println(" SU MO TU WE TH FR SR");
			
			//시작 요일에 따라 공백 출력
			for (int i = 0; i < count; i++) {
				System.out.printf("   ");
				printcount++;
			}
			
			//날짜 출력
			for (int i = 1; i <= day; i++) {
				System.out.printf("%3d", i);
				printcount++;
				if (printcount % 7 == 0)
					System.out.println();
			}
			System.out.println();
		}
		else System.out.println("요일을 다시 입력하세요...");
	}
	
	//시작 요일 판정	
	private int FirstWeek(String d) { 
		switch (d) {
		case "SU":
			return 0;
		case "MO":
			return 1;
		case "TU":
			return 2;
		case "WE":
			return 3;
		case "TH":
			return 4;
		case "FR":
			return 5;
		case "SR":
			return 6;
		default:
			return -1;
		}
	}

	public static void main(String[] args) {
		Calendar cal = new Calendar();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		int month, year;
		String weekly;

		while (true) {
			System.out.print("연도를 입력하세요\nYEAR>");
			year = scan.nextInt();

			System.out.print("월을 입력하세요\nMONTH>");
			month = scan.nextInt();

			System.out.print("첫 번째 요일을 입력하세요(SU MO TU WE TH FR SR)\nFIRST DAY>");
			weekly = scan.next();

			if (month < 1 || month > 12) // 존재하지 않는 월
				break;

			cal.printCalendar(month, year, weekly);
		}

		System.out.println("bye~");
	}

}
