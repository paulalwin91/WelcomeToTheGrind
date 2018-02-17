package dynamix;

public class FibbIt {
	public static void main(String[] args) {
		System.out.println(fibb2(75));

	}

	private static int fibb(int n) {
		if (n < 2)
			return n;
		return fibb(n - 1) + fibb(n - 2);
	}

	private static int fibb2(int n) {
		if (n < 2)
			return n;

		int[] cachewa = new int[n + 1];
		for (int i = 0; i < cachewa.length; i++) {
			cachewa[i] = -1;
		}

		cachewa[0] = 0;
		cachewa[1] = 1;
		return fibF(n, cachewa);
	}

	private static int fibF(int n, int[] cachewa) {
		if (cachewa[n] >= 0)
			return cachewa[n];

		cachewa[n] = fibF(n - 1, cachewa) + fibF(n - 2, cachewa);
		return cachewa[n];
	}
}
