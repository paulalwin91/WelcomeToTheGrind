package MotherPCT;

public class ArrayAVLTest {

	public static void main(String[] args) {
		int[] a = {-1, 10, 0, 15, 0, 0, 13, 18 };

		System.out.println(appeal(0, a));
//		if (appeal(1, a) > 1)
//			System.out.println("False");
//		else
//			System.out.println("True");
	}

	private static int appeal(int i, int[] tree) {
		if (i > tree.length - 1 || tree[i] == 0)
			return 0;
		else {
			int lsize = appeal(i * 2, tree);
			int rsize = appeal(i * 2 + 1, tree);
			//System.out.println(lsize + " " + rsize + " " +i);
			return Math.max(lsize,rsize) + 1;
		}
	}
}
