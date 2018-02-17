package MotherPCT;

import java.util.ArrayList;
import java.util.List;

public class LCD {
	static int[] arrResult;
	static int arraySize = 0;
	static int level;
	static List<Integer> aUpline = new ArrayList<Integer>();
	static List<Integer> bUpline = new ArrayList<Integer>();

	public static void main(String[] args) {
		level = 4;

		for (int i = 0; i < level; i++) {
			arraySize = 2 * arraySize + 1;
		}

		arrResult = new int[arraySize + 1];
		arrResult[0] = -1;
		fillHerUp();
		printLCD(0, 1);
		System.out.println(compareUplines());
	}

	private static int compareUplines() {

		for (int src = 0; src < aUpline.size(); src++) {
			for (int des = 0; des < bUpline.size(); des++) {
				if (aUpline.get(src) == bUpline.get(des)) {
					return aUpline.get(src);
				}
			}
		}

		return -1;
	}

	private static void printLCD(int a, int b) {
		int aIndex = a;
		int bIndex = b;

		if (arrResult[aIndex] == -1 && arrResult[bIndex] == -1)
			return;

		if (arrResult[aIndex] != -1) {
			aUpline.add(arrResult[aIndex]);
			a = a / 2;
		}

		if (arrResult[bIndex] != -1) {
			bUpline.add(arrResult[bIndex]);
			b = b / 2;
		}
		printLCD(a, b);
	}

	private static void fillHerUp() {
		for (int i = 1; i <= arraySize; i++) {
			arrResult[i] = i;
		}
	}
}
