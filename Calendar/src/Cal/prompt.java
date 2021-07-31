package Cal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

class Prompt {
	private Scanner scan = new Scanner(System.in);
	private HashMap<Date, String> planMap;
	
	public Prompt() {
		planMap = new HashMap<>();
	}

	public void StartCalendar() {
		while (true) {
			System.out.println("+----------------------+");
			System.out.println("| 1. ���� ���");
			System.out.println("| 2. ���� �˻�");
			System.out.println("| 3. �޷� ����");
			System.out.println("| q. ����");
			System.out.println("+----------------------+");
			System.out.println("���� (1, 2, 3, q)");

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
				System.out.println("�ٽ� �Է��ϼ���.");
		}
	}

	private int getDay(int m, int y) {
		if (m == 2) {
			if (y % 4 == 0 || y % 400 == 0)
				return 29; // ����
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
		int count = 0; // ����� ���� ����
		int printcount = 0; // ��� Ƚ��

		day = getDay(m, y);
		count = FirstWeek(m, y);

		System.out.printf("   <<%4d��%3d��>>\n", y, m);
		System.out.println("---------------------");
		System.out.println(" SU MO TU WE TH FR SR");

		// ���� ���Ͽ� ���� ���� ���
		for (int i = 0; i < count; i++) {
			System.out.printf("   ");
			printcount++;
		}

		// ��¥ ���
		for (int i = 1; i <= day; i++) {
			System.out.printf("%3d", i);
			printcount++;
			if (printcount % 7 == 0)
				System.out.println();
		}
		System.out.println();
	}

	// ���� ���� ����
	private int FirstWeek(int month, int year) {
		int sum = 0;
		int first = 0;

		for (int i = 1583; i < year; i++) {
			if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) {
				// �����̶��
				sum += 2;
			} else {
				// ����̶��
				sum += 1;
			}
		}

		first = (sum + 6) % 7;

		for (int j = 1; j < month; j++) {
			first += getDay(j, year) % 7;
		}

		return first % 7;
	}
	
	// �����Է�
	public void ScheduleRegistration() {
		
		System.out.println("[���� ���] ��¥�� �Է��ϼ���.");
		System.out.print("(yyyy-MM-dd) : ");
		String date = scan.next();
		
		System.out.println("������ �Է��� �ּ���.");
		String plan = scan.next();

		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			planMap.put(date1, plan);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("��¥�� �� �� �Է��ϼ̽��ϴ�.");
		}
	}
	

	public void ScheduleSearch() {
		System.out.println("[���� �˻�] ��¥�� �Է��ϼ���.");
		System.out.print("(yyyy-MM-dd) : ");
		String date = scan.next();
		
		search(date);
	}

	public void search(String d) {
		try {
			Date date;
			date = new SimpleDateFormat("yyyy-mm-dd").parse(d);
			String plan = planMap.get(date);

			System.out.println(plan);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ShowCalendar() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int month, year;

		System.out.print("������ �Է��ϼ���\nYEAR>");
		year = scan.nextInt();

		System.out.print("���� �Է��ϼ���\nMONTH>");
		month = scan.nextInt();

		if (month < 1 || month > 12) {
			System.out.println("�ش� ���� ���� ���� �ʽ��ϴ�.");
			return;
		}

		printCalendar(month, year);
	}
}