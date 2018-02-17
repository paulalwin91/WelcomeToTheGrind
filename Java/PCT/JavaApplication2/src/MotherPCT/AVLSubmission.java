package MotherPCT;

public class AVLSubmission {
	static int[] avl;
	static int size;
	static String results = "";
	static int rcount = 0;

	public static void main(String[] args) {
		// String avlin = "3 5 1 6 2 4 9 7 -1";
		String avlin = "20 15 30 25 35 40 45 32 10 -1";
		// String avlin = "20 15 30 35 -1";
		int level = avlin.split(" ").length - 1;

		for (int i = 0; i < level; i++) {
			size = 2 * size + 1;
		}

		avl = new int[size + 1];

		for (int i = 0; i < avl.length; i++)
			avl[i] = 0;

		avl[0] = -1;
		for (String s : avlin.split(" ")) {
			if (!s.equals("-1")) {
				{
					// int latestIndex = makeTree(Integer.valueOf(s), 1);
					makeTree(Integer.valueOf(s), 1);
					checkDepth(Integer.valueOf(s));
				}
			}
		}

		System.out.println(results.trim() + " " + rcount);
	}

	private static void checkDepth(int latestVal) {
		// System.out.println(howDeep(2) + " " + howDeep(3) + " " + latestVal);
		if (Math.abs(howDeep(2) - howDeep(3)) > 1) {
			// System.out.println(howDeep(2) +" "+ howDeep(3));
			results = results + latestVal + " ";
			rcount++;
			for (int i = 0; i < avl.length; i++) {
				if (avl[i] == latestVal)
					avl[i] = 0;
			}
		}
	}

	private static int howDeep(int index) {
		if (index > size - 1 || avl[index] == 0)
			return 0;
		else {
			int lDeep = howDeep(2 * index);
			int rDeep = howDeep(2 * index + 1);
			return Math.max(lDeep, rDeep) + 1;
		}
	}

	private static void makeTree(Integer valueOf, int index) {
		if (avl[1] == 0) {
			avl[1] = valueOf;
			return;
		}

		if (valueOf > avl[index]) {
			index = 2 * index + 1;
			if (index <= size - 1) {
				if (avl[index] == 0) {
					avl[index] = valueOf;
					return;
				} else
					makeTree(valueOf, index);
			}
		} else {
			if (valueOf <= avl[index]) {
				index = 2 * index;
				if (index <= size - 1) {
					if (avl[index] == 0) {
						avl[index] = valueOf;
						return;
					} else
						makeTree(valueOf, index);
				}
			}
		}
	}
}
