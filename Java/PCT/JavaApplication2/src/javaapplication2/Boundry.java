package javaapplication2;

public class Boundry {
	public static class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;
		int max = 0;
		int min = 0;
		TreeNode(int data) {
			this.data = data;
		}
	}

	public static void boundaryLevelTraversal(TreeNode root) {
		System.out.print(root.data + " ");
		printLeftEdgeNodes(root.left);
		printLeafNodes(root);
		printRightBottomUp(root.right);

	}

	private static void printLeafNodes(TreeNode root) {
		if (root == null)
			return;

		if (root.left == null && root.right == null) {
			System.out.print(root.data + " ");
			return;
		}
		printLeafNodes(root.left);
		printLeafNodes(root.right);
	}

	private static void printRightBottomUp(TreeNode root) {
		if (root == null)
			return;

		// if leaf node, ignore while printing edges
		if (root.left == null && root.right == null) {
			return;
		}

		if (root.right != null) {
			printRightBottomUp(root.right);
		} else if (root.left != null) {
			printRightBottomUp(root.left);
		}

		System.out.print(root.data + " ");
	}

	private static void printLeftEdgeNodes(TreeNode root) {
		if (root == null)
			return;

		// if leaf node, ignore while printing edges
		if (root.left == null && root.right == null)
			return;

		System.out.print(root.data + " ");

		// If left is null, right will be the boundary edge.
		if (root.left == null) {
			printLeftEdgeNodes(root.right);
		} else {
			printLeftEdgeNodes(root.left);
		}

	}

	public static void main(String[] args) {
		// Creating a binary tree
		TreeNode rootNode = createBinaryTree();
		System.out.println("Boundary traversal of binary tree will be:");
		boundaryLevelTraversal(rootNode);
	}

	public static TreeNode createBinaryTree() {

		TreeNode rootNode = new TreeNode(10);
		TreeNode node5 = new TreeNode(5);
		TreeNode node20 = new TreeNode(20);
		TreeNode node2 = new TreeNode(2);
		TreeNode node8 = new TreeNode(8);
		TreeNode node15 = new TreeNode(15);
		TreeNode node25 = new TreeNode(25);
		TreeNode node1 = new TreeNode(1);
		TreeNode node3 = new TreeNode(3);
		TreeNode node7 = new TreeNode(7);
		TreeNode node9 = new TreeNode(9);
		TreeNode node12 = new TreeNode(12);
		TreeNode node17 = new TreeNode(17);
		TreeNode node22 = new TreeNode(22);
		TreeNode node28 = new TreeNode(28);

		rootNode.left = node5;
		rootNode.right = node20;

		node5.left = node2;
		node5.right = node8;

		node20.left = node15;
		node20.right = node25;

		node2.left = node1;
		node2.right = node3;

		node8.left = node7;
		node8.right = node9;
		
		node15.left = node12;
		node15.right = node17;
		
		node25.left = node22;
		node25.right = node28;
		
		
		return rootNode;
	}
}
