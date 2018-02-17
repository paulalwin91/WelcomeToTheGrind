package PCT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Glymp {
	static int interCount = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int vline = 0, hline = 0;
		String[] vLineArr;
		String line = br.readLine();
		vline = Integer.valueOf(line.split(" ")[0]);
		hline = Integer.valueOf(line.split(" ")[1]);
		vLineArr = new String[vline];
		String[] hLineArr = new String[hline];
		for (int i = 0; i < vline; i++) {
			vLineArr[i] = br.readLine();
		}
		for (int i = 0; i < hline; i++) {
			hLineArr[i] = br.readLine();
		}

		processLines(hLineArr, vLineArr);
		System.out.println(interCount);
	}

	private static void processLines(String[] hLineArr, String[] vLineArr) {
		// TODO Auto-generated method stub
		for (String hline : hLineArr) {
			for (String vline : vLineArr) {
				if (doesIntersect(hline, vline))
					interCount++;
			}

		}
	}

	private static Boolean doesIntersect(String hLine, String vLine) {
		if ((Integer.valueOf(hLine.split(" ")[0]) >= Integer.valueOf(vLine.split(" ")[1])
				&& Integer.valueOf(hLine.split(" ")[0]) <= Integer.valueOf(vLine.split(" ")[2]))
				&& Integer.valueOf(vLine.split(" ")[0]) >= Integer.valueOf(hLine.split(" ")[1])
				&& Integer.valueOf(vLine.split(" ")[0]) <= Integer.valueOf(hLine.split(" ")[2])) {
			return true;
		}
		return false;
	}
}
