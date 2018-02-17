package PCT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class FamTree {

	static StringBuilder resultSB = new StringBuilder();
	static String[] source;
	static String[] reordered;
	static ArrayList<String> reorderfirst;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int rCount = 0;
		String[] arrTree;
		String[] PRarrTree;
		int test = 0;
		String[] testArr;
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);

		// rCount = Integer.valueOf(br.readLine());
		// rCount = 8;
		rCount = 9;

		String[] rels = new String[rCount];
		//
		// for (int i = 0; i < rCount; i++) {
		// rels[i] = br.readLine();
		// }

		// rels[0] = "M J";
		// rels[1] = "J I";
		// rels[2] = "M K";
		// rels[3] = "I S";
		// rels[4] = "S V";
		// rels[5] = "I RJ";
		// rels[6] = "RJ P";
		// rels[7] = "RJ RU";

		rels[0] = "PR R";
		rels[1] = "Sh Sa";
		rels[2] = "PR Sh";
		rels[3] = "R RD";
		rels[4] = "RI RN";
		rels[5] = "RD BEB";
		rels[6] = "RD LO";
		rels[7] = "R RI";
		rels[8] = "RI RDM";
//		PRarrTree = makeArrayTree(rels, rCount);
//		rels = reOrder(rels);
		arrTree = makeArrayTree(rels, rCount);
		test = 7;
		testArr = new String[test];
		testArr[0] = "BEB descendant Sh";
		testArr[1] = "R sibling Sh";
		testArr[2] = "PR ancestor RDM";
		testArr[3] = "LO sibling RDM";
		testArr[4] = "BEB ancestor Sh";
		testArr[5] = "PR ancestor R";
		testArr[6] = "RI descendant R";
		// test = Integer.valueOf(br.readLine());

		// test = 6;
		// testArr = new String[test];
		// testArr[0] = "M child J";
		// testArr[1] = "V descendant I";
		// testArr[2] = "P sibling V";
		// testArr[3] = "S child I";
		// testArr[4] = "S ancestor V";
		// testArr[5] = "K ancestor RU";

		//
		// for (int i = 0; i < test; i++) {
		// testArr[i] = br.readLine();
		// }

		StringBuilder smalres = new StringBuilder();
		for (int i = 0; i < testArr.length; i++) {

			smalres.append(
					driver(arrTree, testArr[i].split(" ")[0], testArr[i].split(" ")[2], testArr[i].split(" ")[1]));
			smalres.append(" ");
		}
		System.out.println(smalres.toString().trim());

		// String result = "dfsd";
		// while(!(result = printTree(arrTree, 0)).equals("")){
		//
		// }
		printTree(arrTree, 1);
		System.out.println(resultSB.toString().trim());

	}

	private static String[] reOrder(String[] rels) {
		source = rels.clone();
		reordered = new String[rels.length];
		reorderfirst = new ArrayList<String>();
		initializeArr();

		String newrel = "";
		int index = 0;

		if (reordered[index].equals("")) {
			reorderfirst.add(source[0].split(" ")[0]);
			reorderfirst.add(source[0].split(" ")[1]);
			reordered[index] = source[0];
			source[index] = "";
		}
		index++;
		while (!(newrel = getNextRel(source, reordered, reorderfirst)).equals("")) {
			reordered[index] = newrel;
			index++;
		}

		return reordered;
	}

	private static void initializeArr() {
		// TODO Auto-generated method stub
		for (int i = 0; i < reordered.length; i++) {
			reordered[i] = "";
		}
	}

	private static String getNextRel(String[] source, String[] reordered, ArrayList reorderfirst) {

		String nextStr = "";
		for (int i = 0; i < source.length; i++) {
			if (!source.equals("") && reorderfirst.contains(source[i].split(" ")[0])) {
				reorderfirst.add(source[i].split(" ")[0]);
				reorderfirst.add(source[i].split(" ")[1]);
				nextStr = source[i];
				source[i] = "";
				return nextStr;
			}
		}
		return nextStr;
	}

	public static String driver(String[] arr, String A, String B, String paran) {

		if (paran.equals("child"))
			return isDes(arr, A, B);
		if (paran.equals("sibling"))
			return isSibling(arr, A, B);
		if (paran.equals("parent"))
			return isPar(arr, A, B);
		if (paran.equals("ancestor"))
			return isAncestor(arr, A, B);
		if (paran.equals("descendant"))
			return isDes(arr, A, B);
		return "F";
	}

	// TODO START FROM 1 NOT 0
	private static String[] makeArrayTree(String[] rels, int rCount) {
		int size = 0;
		for (int i = 0; i <= rCount; i++) {
			size = 2 * size + 1;
		}
		String[] arr = new String[size + 1];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = "";
		}

		arr[0] = "-1";

		for (int i = 0; i < rels.length; i++) {

			if (arr[1].equals("")) {
				arr[1] = rels[i].split(" ")[0];
				arr = insertLeft(arr, rels[i]);
			} else
				insertLeft(arr, rels[i]);
		}
		return arr;
	}

	private static String[] insertLeft(String[] arr, String parentchild) {

		for (int i = 1; i < arr.length; i++) {
			if (arr[i].equals(parentchild.split(" ")[0])) {
				if (2 * i <= arr.length - 1) {
					if (arr[2 * i].equals(""))
						arr[2 * i] = parentchild.split(" ")[1];
					else
						arr = insertRight(arr, parentchild);
				}
				break;
			}
		}

		return arr;
	}

	private static String[] insertRight(String[] arr, String parentchild) {

		for (int i = 1; i < arr.length; i++) {
			if (arr[i].equals(parentchild.split(" ")[0])) {
				if ((2 * i) + 1 <= arr.length - 1)
					arr[(2 * i) + 1] = parentchild.split(" ")[1];
				break;
			}
		}

		return arr;
	}

	public static String isAncestor(String[] arr, String A, String B) {
		String result = "F";
		int i = 0;
		while (!arr[i].equals(A)) {
			i++;
		}
		if (anResc(arr, B, i))
			return "T";

		return "F";
	}

	public static Boolean anResc(String[] arr, String B, int index) {

		if (index > arr.length - 1)
			return false;

		if (2 * index <= arr.length - 1 && (2 * index) + 1 <= arr.length - 1) {
			if ((arr[2 * index].equals(B)) || (arr[(2 * index) + 1].equals(B)))
				return true;
		} else
			return false;

		anResc(arr, B, 2 * index);
		return anResc(arr, B, (2 * index + 1));
	}

	public static Boolean anDesc(String[] arr, String B, int index) {

		if (index <= 0)
			return false;

		if (arr[index / 2].equals(B))
			return true;

		return anDesc(arr, B, (index / 2));
	}

	public static String isSibling(String[] arr, String A, String B) {
		int ai = 0;
		while (!arr[ai].equals(A)) {
			ai++;
		}

		int bi = 0;
		while (!arr[bi].equals(B)) {
			bi++;
		}

		if (arr[ai / 2].equals(arr[bi / 2]))
			return "T";
		else
			return "F";
	}

	public static String isDes(String[] arr, String A, String B) {
		String result = "F";
		int i = 0;
		while (!arr[i].equals(A)) {
			i++;
		}
		if (anDesc(arr, B, i))
			return "T";

		return "F";
	}

	public static String isPar(String[] arr, String A, String B) {
		int i = 0;
		while (!arr[i].equals(A)) {
			i++;
		}
		if ((arr[2 * i].equals(B)) || (arr[(i) + 1].equals(B))) {
			return "T";
		} else
			return "F";
	}

	public static String printTree(String[] arr, int index) {

		if (index >= arr.length - 1)
			return "";

		if (!arr[index].trim().equals(""))
			resultSB.append(arr[index] + " ");

		printTree(arr, index * 2);
		return printTree(arr, (index * 2) + 1);

	}

}
