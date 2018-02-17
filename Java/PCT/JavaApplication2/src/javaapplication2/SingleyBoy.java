package javaapplication2;

public class SingleyBoy {

	static Band r1, r2;

	public static void main(String[] args) {
		Band head = new Band(1);
		Band two = new Band(2);
		Band three = new Band(3);
		Band four = new Band(4);
		Band five = new Band(5);
		head.next = two;
		two.next = three;
		three.next = four;
		four.next = five;
		five.next = null;
		dividePrint(head);

	}

	private static void dividePrint(Band head) {
		r1 = head;
		r2 = head;
		while (r2 != null) {
			r1 = r1.next;
			if (r2.next != null)
				r2 = r2.next.next;
			else
				r2 = null;
		}
		r2 = r1;
		r1.next = null;
		System.out.println("First listwa - ");
		while (head != null) {
			System.out.print(head.data);
			head = head.next;
			if (head != null)
				System.out.print("->");
		}
		System.out.println();
		System.out.println("Second List - ");
		while (r2 != null) {
			System.out.print(r2.data);
			r2 = r2.next;
			if (r2 != null)
				System.out.print("->");

		}
	}
}

class Band {
	int data;
	Band next;

	public Band(int d) {
		this.data = d;
	}
}
