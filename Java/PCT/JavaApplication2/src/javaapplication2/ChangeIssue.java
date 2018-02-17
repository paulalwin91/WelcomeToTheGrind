package javaapplication2;

public class ChangeIssue {
	static int one, five, ten, Tfive = 0;
	static int[] chillar = { 25, 10, 5, 1 };

	public static void main(String[] args) {
		double amount = 234234; // 222223221;
		int amount1 = 23222;
		// calculateChange(amount);
		//System.out.println(callcChane(amount));
		ChangeIssue c = new ChangeIssue();
		System.out.println(c.makeChange(amount1));
		// System.out.println("one - " + one + " five - " + five + " ten - " + ten + "
		// Tfive - " + Tfive);
	}

	private int[] coins = new int[] { 10, 6, 1 };

	public int makeChange(int c) {
		// Initialize cache with values as -1
		int[] cache = new int[c + 1];
		for (int i = 1; i < c + 1; i++)
			cache[i] = -1;
		return makeChange(c, cache);
	}

	// Overloaded recursive function
	private int makeChange(int c, int[] cache) {
		// Return the value if it’s in the cache
		if (cache[c] >= 0)
			return cache[c];
		int minCoins = Integer.MAX_VALUE;
		// Find the best coin
		for (int coin : coins) {
			if (c - coin >= 0) {
				int currMinCoins = makeChange(c - coin, cache);
				if (currMinCoins < minCoins)
					minCoins = currMinCoins;
			}
		}
		// Save the value into the cache
		cache[c] = minCoins + 1;
		return cache[c];
	}

	private static int callcChane(double amount) {
		int gC = 0;
		if (amount == 0)
			return gC;

		for (int c : chillar) {
			if (amount - c >= 0) {
				gC = callcChane(amount - c);
				break;
			}
		}
		return gC + 1;
	}

	private static void calculateChange(double amount) {

		// TODO Auto-generated method stub
		if (amount == 0)
			return;
		if (amount >= 25) {
			Tfive += amount / 25;
			amount = amount - (25 * Tfive);
			calculateChange(amount);
			return;
		}
		if (amount >= 10) {
			ten += amount / 10;
			amount = amount - (10 * ten);
			calculateChange(amount);
			return;
		}
		if (amount >= 5) {
			five += amount / 5;
			amount = amount - (5 * five);
			calculateChange(amount);
			return;
		}
		if (amount >= 1) {
			one += amount / 1;
			amount = amount - (1 * one);
			calculateChange(amount);
			return;
		}

	}
}
