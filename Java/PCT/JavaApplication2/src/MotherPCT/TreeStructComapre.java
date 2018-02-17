package MotherPCT;

import java.util.ArrayList;

public class TreeStructComapre {
	static ArrayList<int[]> roots = new ArrayList<int[]>();

	public static void main(String[] args) {
		int[] a = { 3, 2, 4, 7, -1 };
		int[] b = { 10, 5, 20, 15, 21, -1 };
		int[] c = { 10, 8, 12, 6, 9, 11, 15, -1 };
		int[] d = { 10, 8, 12, 14, -1 };
		int[] e = { 3, 2, 5, -1 };
		int[] f = { 3, -1 };

		roots.add(MakeAdd(a));
		roots.add(MakeAdd(b));
		roots.add(MakeAdd(c));
		roots.add(MakeAdd(d));
		roots.add(MakeAdd(e));
		roots.add(MakeAdd(f));

		for (int i = 0; i < roots.size() - 1; i++) {
			compareTrees(roots.get(0), roots.get(i + 1));
		}
	}

	private static void compareTrees(int[] is, int[] is2) {
		int depth1, depth2 = 0;

		if (is.length != is2.length) {
			depth1 = findDepth(is);
			depth2 = findDepth(is2);
			System.out.println("NO " + Math.abs(depth1 - depth2));
		} else {
			for (int i = 1; i < is.length; i++) {
				if ((is[i] != is2[i]) && (is[i] == -1 || is2[i] == -1)) {
					depth1 = findDepth(is);
					depth2 = findDepth(is2);
					System.out.println("NO " + Math.abs(depth1 - depth2));
					return;
				}
			}

			depth1 = findDepth(is);
			depth2 = findDepth(is2);
			System.out.println("YES " + Math.abs(depth1 - depth2));
			return;
		}

	}

	private static int findDepth(int[] target) {
		int maxValueIndex = -1;
		int depth = 0;
		for (int i = 0; i < target.length; i++) {
			if (target[i] > maxValueIndex)
				maxValueIndex = i;
		}

		while (maxValueIndex != 0) {
			maxValueIndex = maxValueIndex / 2;
			depth = depth + 1;
		}

		return depth + 1;

	}

	private static int[] MakeAdd(int[] a) {
		// if array is empty null check
		int level = a.length - 1;
		int size = 0;
		for (int i = 0; i < level; i++) {
			size = 2 * level + 1;
		}
		int[] arrTree = new int[size + 1];

		initializeTree(arrTree);

		for (int i : a) {
			if (i != -1)
				arrTree = concreteTree(arrTree, i, 1);
		}

		return arrTree;
	}

	private static void initializeTree(int[] arrTree) {

		for (int i = 0; i < arrTree.length; i++)
			arrTree[i] = -1;

	}

	private static int[] concreteTree(int[] arrTree, int a, int currentIndex) {
		if (arrTree[1] == -1) {
			arrTree[1] = a;
			return arrTree;
		}

		if (a > arrTree[currentIndex]) {
			currentIndex = 2 * currentIndex + 1;
			if (currentIndex <= arrTree.length - 1)
				if (arrTree[currentIndex] == -1)
					arrTree[currentIndex] = a;
				else
					concreteTree(arrTree, a, currentIndex);
		}

		if (a < arrTree[currentIndex]) {
			currentIndex = 2 * currentIndex;
			if (currentIndex <= arrTree.length - 1)
				if (arrTree[currentIndex] == -1)
					arrTree[currentIndex] = a;
				else
					concreteTree(arrTree, a, currentIndex);
		}

		return arrTree;
	}
}
