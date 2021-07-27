import java.util.Vector;
import java.util.Scanner;
import java.util.InputMismatchException;

class Tower {
	private Vector<Vector<Integer>> tower;
	private Vector<Integer> list1;
	private Vector<Integer> list2;
	private Vector<Integer> list3;
	private Vector<Integer> first = new Vector<Integer>(3);
	private int count = 1;

	// 메모리 공간 3*3(생성자)
	Tower() {
		tower = new Vector<Vector<Integer>>(3);
		list1 = new Vector<Integer>(3);
		list2 = new Vector<Integer>(3);
		list3 = new Vector<Integer>(3);

		for (int i = 3; i > 0; i--) {
			list1.add(i);
			first.add(i);
		}

		tower.add(list1);
		tower.add(list2);
		tower.add(list3);
	}

	// 출력(메소드)
	public void printTower() {
		System.out.println("****TOWER****");
		for (int i = 0; i < 3; i++) {
			System.out.print("[" + (i + 1) + "] ");
			System.out.println(tower.get(i));
		}
	}

	public void printprompt() {
		System.out.println();
		System.out.print("[" + count + "] ");
		System.out.print("From which tower will you move a disk to which tower? (form=[1|2|3], to=[1|2|3]):");
	}

	// disk 이동
	public void input() {
		@SuppressWarnings("resource")
		Scanner movepoint = new Scanner(System.in);
		int first, second;
		boolean valid = true;

		while (valid)
			try {
				{
					first = movepoint.nextInt();
					second = movepoint.nextInt();

					if (movable(first, second)) {
						System.out.println("\n=> Move succeeded!\n");
						move(first, second);
						printTower();
					} else {
						System.out.println("\n=> Move failed!");
					}
					valid = false;
				}
			}

			catch (InputMismatchException ime) {
				System.out.println("You input invalid values try again:");
				movepoint = new Scanner(System.in);
			}
	}

	// 이동가능 여부 판단(메소드) 가능 : true 불가능 : false 반환
	public boolean movable(int first, int second) {

		int towersize1;
		int towersize2;

		// 범위 밖 값
		if (first < 1 || first > 3 || second < 1 || second > 3) {
			return false;
		}
		towersize1 = tower.get(first - 1).size();
		towersize2 = tower.get(second - 1).size();
		// 옮기려는 디스크 x
		if (towersize1 == 0)
			return false;

		// 빈 공간에 옮김
		else if (towersize2 == 0) {
			count++;
			return true;
		}
		// 큰 디스크 -> 작은 디스크
		else if (tower.get(first - 1).lastElement() > tower.get(second - 1).lastElement())
			return false;
		else {
			count++;
			return true;
		}
	}

	// move(메소드)
	public void move(int first, int second) {

		int towersize = tower.get(first - 1).size();

		tower.get(second - 1).add(tower.get(first - 1).lastElement());
		tower.get(first - 1).remove(towersize - 1);
	}

	// 게임의 종료 조건 판정
	public boolean finish() {
		if (tower.get(2).equals(first))
			return replay();
		else
			return false;
	}

	// replay여부 확인
	private boolean replay() {

		String decide;
		@SuppressWarnings("resource")
		Scanner answer = new Scanner(System.in);
		System.out.println("Congratulation! You sloved it in " + (count - 1) + "moves!");
		while (true) {
			System.out.print("Do you want to play again? (Y/N):");

			decide = answer.next();
			if (decide.equals("Y")) {
				tower.clear();
				return true;
			} else if (decide.equals("N")) {
				System.out.println("Thank you for play!");
				System.exit(0);
			}
		}
	}
}

public class Hanoi {
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		while (true) {
			Tower t = new Tower();
			t.printTower();
			while (true) {
				t.printprompt();
				t.input();
				if (t.finish())
					break;
			}
			System.out.println("\nPlay Again!!\n");
		}
	}
}