package PCT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HashShit {
	static int[] hashArray;
	static StringBuilder sbResults = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.valueOf(br.readLine());
		int[] inptArr = new int[size];
		hashArray = new int[size];
		String input = br.readLine();
		System.out.println(sbResults.toString());
	}

	public static void processInput(String input) {

		for (String str : input.split(" ")) {
			if (!str.equals("-1")) {
				insert(Integer.valueOf(str));
			}
		}
	}

	private static void insert(Integer valueOf) {
		int index = valueOf % hashArray.length;
		if (hashArray[index] == 0) {
			hashArray[index] = valueOf;
			sbResults.append(index + "\n");
		} else {
			insertComplicated(valueOf);
		}
	}

	private static void insertComplicated(Integer valueOf) {
		
	}

}
