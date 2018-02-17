package javaapplication2;

import java.util.HashMap;

public class kTHFrek {
	static String inp = "i do not know what i do not know is that a bad thing that i do not know what i do not know, by the way do you know what you dont know or you also dont have any clue of what you dont know ?";
	static HashMap<String, Integer> hm = new HashMap<String, Integer>();

	public static void main(String[] args) {
		processString();
		sortHash();
		System.out.println("And the kth element isssssssssssss ... " + hm.entrySet().toArray()[2]);
	}

	private static void sortHash() {
		
	}

	private static void processString() {
		String[] arrArr = inp.split(" ");
		for (String s : arrArr) {
			if (hm.containsKey(s)) {
				int count = hm.get(s);
				hm.put(s, count + 1);
			} else {
				hm.put(s, 1);
			}
		}
	}
}
