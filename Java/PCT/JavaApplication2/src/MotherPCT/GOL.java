package MotherPCT;

import java.util.HashMap;

public class GOL {
	static String[][] gArr;
	static int rSize;
	static int cSize;
	static int orgC;
	static int genC;
	static HashMap<Integer, Integer> births = new HashMap<Integer, Integer>();
	static HashMap<Integer, Integer> deaths = new HashMap<Integer, Integer>();

	public static void main(String args[]) {
		String[][] gsArr = { { "#", "@", "#" }, { "#", "@", "@" }, { "@", "#", "@" } };
		gArr = gsArr;
		rSize = 3;
		cSize = 3;
		genC = 4;
		//genC = 3;
		for (int i = 0; i < genC; i++) {
			letItBegin();
			regenerate();			
		}
		updateorgC();
		System.out.println(orgC);
	}

	private static void regenerate() {
		for (int i : births.keySet()) {
			gArr[i][births.get(i)] = "@";
		}
		for (int j : deaths.keySet()) {
			gArr[j][deaths.get(j)] = "#";
		}
		births = new HashMap<Integer, Integer>();
		deaths = new HashMap<Integer, Integer>();
	}

	private static void updateorgC() {
		for (int i = 0; i < gArr.length; i++) {
			for (int j = 0; j < gArr[i].length; j++) {
				if (gArr[i][j].equals("@"))
					orgC++;
			}
		}
	}

	private static void letItBegin() {
		for (int i = 0; i < gArr.length; i++) {
			for (int j = 0; j < gArr[i].length; j++) {
				processCell(i, j);
			}
		}
	}

	private static void processCell(int row, int col) {
		int neigCount = 0;
		neigCount = countNeigh(row, col);
		if (neigCount <= 1 && gArr[row][col].equals("@")) {
			deaths.put(row, col);			
			return;
		}

		if (neigCount <= 3 && gArr[row][col].equals("@")) {
			return;
		}

		if (neigCount == 3 && gArr[row][col].equals("#")) {
			births.put(row,col);			
			return;
		}

		if (neigCount >= 4 && gArr[row][col].equals("@")) {
			deaths.put(row, col);
			return;
		}

	}

	private static int countNeigh(int row, int col) {
		int neCount = 0;
		if (findl(row, col))
			neCount++;
		if (findr(row, col))
			neCount++;
		if (findt(row, col))
			neCount++;
		if (findb(row, col))
			neCount++;
		if (findlt(row, col))
			neCount++;
		if (findlb(row, col))
			neCount++;
		if (findrt(row, col))
			neCount++;
		if (findrb(row, col))
			neCount++;
		return neCount;
	}

	private static Boolean findrb(int row, int col) {
		int newRow = 0, newCol = 0;
		newRow = row + 1;
		newCol = col + 1;
		if (newRow > (rSize - 1) || newCol > (cSize - 1))
			return false;
		else {
			if (gArr[newRow][newCol].equals("@"))
				return true;
		}
		return false;
	}

	private static Boolean findlb(int row, int col) {
		int newRow = 0, newCol = 0;
		newRow = row + 1;
		newCol = col - 1;
		if (newRow > (rSize - 1) || newCol < 0)
			return false;
		else {
			if (gArr[newRow][newCol].equals("@"))
				return true;
		}
		return false;
	}

	private static Boolean findrt(int row, int col) {
		int newRow = 0, newCol = 0;
		newRow = row - 1;
		newCol = col + 1;
		if (newRow < 0 || newCol > (cSize - 1))
			return false;
		else {
			if (gArr[newRow][newCol].equals("@"))
				return true;
		}
		return false;
	}

	private static Boolean findlt(int row, int col) {
		int newRow = 0, newCol = 0;
		newRow = row - 1;
		newCol = col - 1;
		if (newRow < 0 || newCol < 0)
			return false;
		else {
			if (gArr[newRow][newCol].equals("@"))
				return true;
		}
		return false;
	}

	private static Boolean findb(int row, int col) {
		int newRow = 0, newCol = 0;
		newRow = row + 1;
		newCol = col;
		if (newRow > (rSize - 1))
			return false;
		else {
			if (gArr[newRow][newCol].equals("@"))
				return true;
		}
		return false;
	}

	private static Boolean findt(int row, int col) {
		int newRow = 0, newCol = 0;
		newRow = row - 1;
		newCol = col;
		if (newRow < 0)
			return false;
		else {
			if (gArr[newRow][newCol].equals("@"))
				return true;
		}
		return false;
	}

	private static Boolean findr(int row, int col) {
		int newRow = 0, newCol = 0;
		newRow = row;
		newCol = col + 1;
		if (newCol > cSize - 1)
			return false;
		else {
			if (gArr[newRow][newCol].equals("@"))
				return true;
		}
		return false;
	}

	private static Boolean findl(int row, int col) {
		int newRow = 0, newCol = 0;
		newRow = row;
		newCol = col - 1;
		if (newCol < 0)
			return false;
		else {
			if (gArr[newRow][newCol].equals("@"))
				return true;
		}
		return false;
	}
}
