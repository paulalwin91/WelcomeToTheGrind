package javaapplication2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PerfectBSTTrees {

	static int count = 0;
	static Boolean bool = false;
	static ArrayList<Nodewa> listNodes = new ArrayList<Nodewa>();

	public static void main(String[] args) throws IOException {
		String[] input;
		int height = 3;
		int[] LCDArray = { 11, 2 };
		Nodewa root = new Nodewa(0, 0, null);
		createBST(root, height);
		printTree(root, height);
		System.out.println("LCD of " + LCDArray[0] + " and " + LCDArray[1] + " is: ");
		getLCD(LCDArray, root, height);

	}

	private static void getLCD(int[] lCDArray, Nodewa root, int height) {
		Nodewa aNode = getNodeFromInt(lCDArray[0], root);
		Nodewa bNode = getNodeFromInt(lCDArray[1], root);
		int[] aNodeDesecdents = new int[height + 1];
		int[] bNodeDesecdents = new int[height + 1];

		int aIndex = 0, bIndex = 0;
		if (aNode.value == bNode.value)
			System.out.println(aNode.value);
		else {
			while (aNode != null) {
				aNodeDesecdents[aIndex] = aNode.value;
				aNode = aNode.pr;
				aIndex++;
			}
			while (bNode != null) {
				bNodeDesecdents[bIndex] = bNode.value;
				bNode = bNode.pr;
				bIndex++;
			}
			printLCD(aNodeDesecdents, bNodeDesecdents, root);
		}
	}

	private static void printLCD(int[] aNodeDesecdents, int[] bNodeDesecdents, Nodewa root) {
		for (int i = 0; i < aNodeDesecdents.length; i++) {
			for (int j = 0; j < bNodeDesecdents.length; j++) {
				if (aNodeDesecdents[i] == bNodeDesecdents[j]) {
					System.out
							.print(aNodeDesecdents[i] + " height is " + getNodeFromInt(aNodeDesecdents[i], root).level);
					return;
				}
			}
		}

	}

	private static Nodewa getNodeFromInt(int NodeValue, Nodewa root) {
		for (int i = 0; i < listNodes.size(); i++) {
			if (NodeValue == listNodes.get(i).value)
				return listNodes.get(i);
		}
		return root;
	}

	public static void createBST(Nodewa root, int height) {
		if (root.level + 1 <= height) {
			root = makeChildren(root);
			createBST(root.lc, height);
			createBST(root.rc, height);
		}
	}

	public static Nodewa makeChildren(Nodewa node) {
		node.lc = new Nodewa(0, node.level + 1, node);
		node.rc = new Nodewa(0, node.level + 1, node);
		return node;
	}

	public static int getCount() {
		return count++;
	}

	private static void printTree(Nodewa root, int height) {

		for (int level = 0; level <= height; level++) {
			printLevel(root, level);
			System.out.println("");
		}

		System.out.println("");
	}

	private static void printLevel(Nodewa root, int level) {

		if (root.level == level) {
			root.value = getCount();
			listNodes.add(root);
			System.out.print(root.value + " ");
		}
		if (root.lc != null)
			printLevel(root.lc, level);

		if (root.rc != null)
			printLevel(root.rc, level);

	}

}

class Nodewa {
	public int value;
	public Nodewa lc;
	public Nodewa rc;
	public int level;
	public Nodewa pr;

	public Nodewa(int val, int lvl, Nodewa parent) {
		this.value = val;
		this.level = lvl;
		this.pr = parent;
	}
}
