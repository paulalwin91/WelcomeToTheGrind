package javaapplication2;

public class Node {

	public int Key;
	public Node left, right;
	public int level;
	public int maxLevel;
	public Node pr;


	public Node(int nodeValue, int lvl,Node parent) {
		this.Key = nodeValue;
		this.left = null;
		this.right = null;
		this.level = lvl;
		this.pr = parent;
	}
}
