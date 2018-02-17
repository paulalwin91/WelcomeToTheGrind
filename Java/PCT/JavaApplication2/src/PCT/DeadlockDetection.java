package PCT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class DeadlockDetection {
	static HashMap<Integer, String> iHave;
	static HashMap<Integer, String> iWant;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int results = Integer.valueOf(br.readLine());
		if (results == 0) {
			GameOver(false);
			return;
		}

		String[] arrayResults = new String[results];
		for (int i = 0; i < results; i++) {
			arrayResults[i] = br.readLine();
		}

		hashItBaby(arrayResults);
		if (iHave.size() == 1) {
			GameOver(false);
			return;
		} else {
			if (isAllGood()) {
				GameOver(true);
			} else {
				GameOver(false);
			}
			return;
		}
	}

	private static boolean isAllGood() {
		for (Integer key : iWant.keySet()) {
			for (String want : iWant.get(key).split(",")) {
				if (want != null && !want.equals(""))
					if (!want.equalsIgnoreCase("-1"))
						for (Integer haveKey : iHave.keySet()) {
							if (haveKey != key) {
								for (String have : iHave.get(haveKey).split(",")) {
									
									if (want.equals(have)) {
										return true;
									}
								}
							}
						}

			}
		}
		return false;
	}

	public static void GameOver(Boolean isGood) {
		if (isGood)
			System.out.println("YES");
		else
			System.out.println("NO");
		return;
	}

	private static void hashItBaby(String[] arrayResults) {

		iHave = new HashMap<Integer, String>();
		iWant = new HashMap<Integer, String>();

		for (int i = 0; i < arrayResults.length; i++) {

			if (iHave.containsKey(Integer.valueOf(arrayResults[i].split(" ")[0]))) {
				String value = iHave.get(Integer.valueOf(arrayResults[i].split(" ")[0])) + "," + arrayResults[i].split(" ")[1];
				iHave.put(Integer.valueOf(arrayResults[i].split(" ")[0]), value);
			} else {
				iHave.put(Integer.valueOf(arrayResults[i].split(" ")[0]), arrayResults[i].split(" ")[1]);
			}

			if (iWant.containsKey(Integer.valueOf(arrayResults[i].split(" ")[0]))) {
				String value = iWant.get(Integer.valueOf(arrayResults[i].split(" ")[0])) + "," + arrayResults[i].split(" ")[2];

				iWant.put(Integer.valueOf(arrayResults[i].split(" ")[0]), value);
			} else {
				iWant.put(Integer.valueOf(arrayResults[i].split(" ")[0]), arrayResults[i].split(" ")[2]);
			}

		}
	}
}
