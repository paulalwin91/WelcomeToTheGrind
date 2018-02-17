package javaapplication2;

import java.util.ArrayList;
import java.util.List;

public class KadaneAl {
	static int mega_max = 0;
	static List<Integer> mega_arr = new ArrayList<Integer>();

	public static void main(String[] args) {
		int[] arr = { 2, -1, 34, 1, -3, 6, 35, -1, 40 };
		believeKad(arr);
		System.out.println(mega_max);
		for (Integer i : mega_arr)
			System.out.print(i + " ");
	}

	private static void believeKad(int[] arr) {
		int temp_max = 0;
		List<Integer> lis = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (temp_max > temp_max + arr[i]) {
				if (temp_max > mega_max) {
					mega_max = temp_max;
					mega_arr.clear();
					mega_arr.addAll(lis);
				}
				temp_max = 0;
				lis = new ArrayList<Integer>();
				continue;
			}
			temp_max = temp_max + arr[i];
			lis.add(arr[i]);
			if (i == arr.length - 1)
				if (temp_max > mega_max) {
					mega_max = temp_max;
					mega_arr.clear();
					mega_arr.addAll(lis);
				}
		}
	}
}
