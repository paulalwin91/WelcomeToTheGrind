package javaapplication2;

public class swapIt {
	public static void main(String[] args) {
		int x = 3, y = 2;
		System.out.println("x " + x + " y " + y);
		x = x + y;
		y = Math.abs(y - x);
		x = x - y;
		System.out.println("x " + x + " y " + y);
	}
}
