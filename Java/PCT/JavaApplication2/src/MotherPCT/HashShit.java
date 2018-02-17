package MotherPCT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HashShit {
	static int size;
	static List<Integer> inputArr;
	static int[] result;
	static StringBuilder resultText = new StringBuilder();
	static int removedNumber;
	static int currentNumber;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = 7;
		// size = Integer.valueOf(br.readLine());
		String input = "38  52 145   16 179 4 -1";
		inputArr = new ArrayList<Integer>();
		result = new int[size];
		initializeArray();
		for (String s : input.split(" ")) {
			if (!s.trim().equals("") && !s.trim().equals("-1")) {
				inputArr.add(Integer.valueOf(s));
			}
		}
		processHash();
		for (int s : result)
			System.out.print(s + " ");
		System.out.println("\n");
		System.out.println(resultText.toString());
	}

	private static void initializeArray() {
		for (int i = 0; i < result.length; i++) {
			result[i] = -123;
		}
	}

	public static void processHash() {
		int index = 0, O = 0;
		try {
			for (int i : inputArr) {
				currentNumber = i;
				O = i;
				index = fetchIndex(i);
				if (result[index] == -123) {
					result[index] = i;
					resultText.append(index + "\n");
				} else {
					resultText.append(index + " ");
					complexProcessBegins(index, i);
				}
			}
		} catch (Exception e) {
			System.err.println(index + " " + O);
		}
	}

	private static int complexProcessBegins(int failedIndex, int inputN) {
		int newIndex;
		if (inputN != 0) {
			removedNumber = inputN % 10;
			inputN = inputN / 10;
			int j = inputN % size;
			if (removedNumber % 2 == 0) {
				newIndex = failedIndex - j;
			} else {
				newIndex = failedIndex + j;
			}

			if (newIndex > size - 1 || newIndex < 0) {
				newIndex = Math.abs(Math.abs(newIndex) - size);
			}

			if (result[newIndex] == -123) {
				result[newIndex] = currentNumber;
				resultText.append(newIndex + "\n");
				return 0;
			} else {
				resultText.append(newIndex + " ");
				complexProcessBegins(newIndex, inputN);
			}
		} else {
			if (removedNumber % 2 != 0) {
				for (int i = failedIndex + 1; i < size; i++) {
					if (result[i] == -123) {
						result[i] = currentNumber;
						resultText.append(i + "\n");
						return 0;
					} else {
						resultText.append(i + " ");
					}
				}
				for (int i = 0; i < failedIndex; i++) {
					if (result[i] == -123) {
						result[i] = currentNumber;
						resultText.append(i + "\n");
						return 0;
					} else {
						resultText.append(i + " ");
					}
				}
			} else {
				for (int i = failedIndex -1; i >= 0; i--) {
					if (result[i] == -123) {
						result[i] = currentNumber;
						resultText.append(i + "\n");
						return 0;
					} else {
						resultText.append(i + " ");
					}
				}
				for (int i = size - 1; i > failedIndex -1; i--) {
					if (result[i] == -123) {
						result[i] = currentNumber;
						resultText.append(i + "\n");
						return 0;
					} else {
						resultText.append(i + " ");
					}
				}

			}
		}
		return 0;
	}

	private static int fetchIndex(int i) {
		return i % size;
	}
}
