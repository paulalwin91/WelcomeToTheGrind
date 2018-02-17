package MotherPCT;

import java.util.ArrayList;

public class AVLChecl {
	static ArrayList<int[]> roots = new ArrayList<int[]>();

	public static void main(String[] args) {
		ArrayList<int[]> inputs = new ArrayList<int[]>();
		// input elements that make array
		int[] a = { 10, 15, 13, 18 };
		int[] q = { 10, 4, 9 };
//		int[] s = { 2, 3, 4 };
//		int[] d = { 2, 3, 4 };

		inputs.add(a);
		inputs.add(q);
//		inputs.add(s);
//		inputs.add(d);

		makeTrees(inputs);
		soWhatiSiT();
	}

	private static void soWhatiSiT() {
		for (int[] tree : roots) {
			if (boyOrGirl(tree))
				System.out.println("T");
			else
				System.out.println("F");

		}
	}

	private static Boolean boyOrGirl(int[] tree) {
		for (int i = tree.length - 1; i >= 1; i--) {
			if (tree[i] != 0)
				if (Math.abs(appeal(i * 2, tree) - appeal(i * 2 + 1, tree)) > 1)
					return false;
		}
		return true;
	}

	private static int appeal(int i, int[] tree) {
		if (i > tree.length - 1 || tree[i] == 0)
			return 0;
		else {
			int lsize = appeal(i * 2, tree);
			int rsize = appeal(i * 2 + 1, tree);
			return Math.max(lsize, rsize) + 1;
		}
	}

	private static void makeTrees(ArrayList<int[]> inputs) {
		for (int[] a : inputs) {
			int size = 0;
			for (int i = 0; i < a.length; i++) {
				size = 2 * size + 1;
			}
			int[] root = new int[size + 1];
			initializeArray(root);
			root[0] = -1;
			root = fillMeUp(a, root);
			roots.add(root);
		}
	}

	private static int[] fillMeUp(int[] input, int[] output) {
		// TODO Auto-generated method stub
		for (int l : input) {
			output = makeMe(l, output, 1);
		}
		return output;
	}

	private static int[] makeMe(int l, int[] output, int currentIndex) {

		if (output[1] == 0) {
			output[1] = l;
			return output;
		}

		if (l < output[currentIndex]) {
			currentIndex = 2 * currentIndex;
			if (currentIndex <= output.length - 1)
				if (output[currentIndex] == 0) {
					output[currentIndex] = l;
					return output;
				} else
					makeMe(l, output, currentIndex);
		}

		if (l > output[currentIndex]) {
			currentIndex = 2 * currentIndex + 1;
			if (currentIndex <= output.length - 1)
				if (output[currentIndex] == 0) {
					output[currentIndex] = l;
					return output;
				} else
					makeMe(l, output, currentIndex);
		}
		return output;
	}

	private static void initializeArray(int[] root) {
		for (int i = 0; i < root.length; i++)
			root[i] = 0;
	}
}
