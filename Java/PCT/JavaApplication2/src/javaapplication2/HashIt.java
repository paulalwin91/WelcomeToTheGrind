package javaapplication2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.Iterator;

public class HashIt {
	public static void main(String[] args) {
		HashMap<String, String> hs = new HashMap<String, String>();
		hs.put("c", "cast");
		hs.put("d", "dust");
		hs.put("b", "basket");
		hs.put("a", "apple");
		
		HashMap<Integer, String> hss = new HashMap<Integer, String>();
		hss.put(2, "cast");
		hss.put(33, "dust");
		hss.put(43, "basket");
		hss.put(21, "apple");
//		
//		TreeMap tp new TreeMap(Map hss);

	
		java.util.Iterator<Integer> ic = hss.keySet().iterator();
		while (ic.hasNext()) {
			//System.out.println(hs.get(ic.next()));
			System.out.println(ic.next());
		}
		
//		
//		HashSet hp = new HashSet();
//		hp.add("d");
//		hp.add("d");
//		
//		java.util.Iterator ip = hp.iterator();
//		while (ip.hasNext()) {
//			System.out.println(ip.next());
//		}
		

//		java.util.Iterator<String> ic = hs.keySet().iterator();
//		while (ic.hasNext()) {
//			System.out.println(hs.get(ic.next()));
//		}
//
//		System.out.println("------");

		// Entry<String, String> is = hs.entrySet();
		// while(ic.hasNext()) {
		// System.out.println("------");
		// System.out.println(ic.get );
		// }

	}
}
