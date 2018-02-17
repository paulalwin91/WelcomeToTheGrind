package javaapplication2;

public class TournamentSort {
	static int level;
	static int[] leafArr;
	static Node root;
	static int leafIndex = 0;
	static StringBuilder sbResult = new StringBuilder();
	static int maxLeaf;

	public static void main(String[] args) {
		String input = "2 25 37 15 10";
		level = Integer.valueOf(input.split(" ")[0]);
		int index = 0;
		leafArr = new int["25 37 15 10".split(" ").length];
		for (String s : "25 37 15 10".split(" ")) {
			leafArr[index] = Integer.valueOf(s);
			index++;
		}
		createTree(root);
		populateleaves(root);

		getMaxLeaf();
		startGame(root);
		setPreviousMax();
		updateTree(root);

		getMaxLeaf();
		startGame(root);
		setPreviousMax();
		updateTree(root);

		getMaxLeaf();
		startGame(root);
		setPreviousMax();
		updateTree(root);

		printPostOrder(root);
		System.out.println(sbResult.toString().trim());
	}

	private static void getMaxLeaf() {
		maxLeaf = 0;
		for (int i = 1; i < leafArr.length; i++) {
			if (leafArr[i] > leafArr[maxLeaf])
				maxLeaf = i;
		}
	}

	private static void setPreviousMax() {
		leafArr[maxLeaf] = -1;
	}

	private static void printPostOrder(Node currentNode) {		
		
		if(currentNode == null)
			return;
		
		printPostOrder(currentNode.left);
		sbResult.append(currentNode.Key + " ");
		printPostOrder(currentNode.right);

	}

	private static void updateTree(Node currentNode) {
		if (currentNode.level > level - 1)
			return;
				
		updateTree(currentNode.left);
		updateTree(currentNode.right);
		currentNode.Key  = Math.max(currentNode.left.Key, currentNode.right.Key);
	}

	private static void startGame(Node current) {
		if (current == null)
			return;
		if (current.Key == leafArr[maxLeaf] && current.level == level) {
			current.Key = -1;
			return;
		}
		startGame(current.left);
		startGame(current.right);
	}

	private static void populateleaves(Node root2) {
		if (root2 == null)
			return;

		if (root2.level == level) {
			root2.Key = leafArr[leafIndex];
			leafIndex++;
		}

		populateleaves(root2.left);
		populateleaves(root2.right);
	}

	private static void createTree(Node currentNode) {
		if (root == null) {
			root = new Node(-1, 0, null);
			createTree(root);
		}

		if(currentNode == null)
			return;
		
		if (currentNode.level >= level)
			return;

		currentNode.left = new Node(-1, currentNode.level + 1, currentNode);
		currentNode.right = new Node(-1, currentNode.level + 1, currentNode);
		createTree(currentNode.left);
		createTree(currentNode.right);
	}
}
