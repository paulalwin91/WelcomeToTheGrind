package dynamix;

import java.util.ArrayList;

public class KnapSac {
	static class item {
		int weight;
		int value;

		public item(int w, int v) {
			this.weight = w;
			this.value = v;
		}
	}

	static int maxWeight = 5;
	public static void main(String[] args) {
		ArrayList<item> items = new ArrayList<item>();
		items.add(new item(2,6));
		items.add(new item(2,10));
		items.add(new item(3,12));
		System.out.println(maxValue(items));
	}
	private static char[] maxValue(ArrayList<item> items) {
		int resultn = 0;
		item result = new item(Integer.MAX_VALUE,Integer.MIN_VALUE);
		ArrayList<item> resultList = new ArrayList<>();
		int max = 5;
		for(int i = 0; i < items.size() - 1; i++) {
			resultn = items.get(i).value;
			max = max - items.get(i).weight;
			for(int j = i + 1; j < items.size(); i++) {
					
			}			
		}		
		return null;
	}
	
}
