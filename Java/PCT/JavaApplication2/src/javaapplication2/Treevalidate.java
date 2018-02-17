package javaapplication2;

import javaapplication2.Boundry.TreeNode;

public class Treevalidate {

	static TreeNode rootNode;
	static Boolean isValid = true;

	public static void main(String[] args) {
		rootNode = createBinaryTree();
		parseTree(rootNode);
		validateBinaryTree(rootNode);
		if (isValid)
			System.out.println("True");
		else
			System.out.println("False");
	}

	private static void validateBinaryTree(TreeNode currentNod) {

		if (currentNod == null)
			return;

		if (currentNod.min != 0) {
			if (!(currentNod.data >= currentNod.min)) {
				System.out.println("data " + currentNod.data + " min " +  currentNod.min + "");
				isValid = false;
				return;
			}
		}

		if (currentNod.max != 0) {
			if (!(currentNod.data <= currentNod.max)) {
				System.out.println("data " + currentNod.data + " max " +  currentNod.max + "");
				isValid = false;
				return;
			}
		}
//
//		if (currentNod.left != null)
//			if (currentNod.data >= currentNod.left.data) {
//				currentNod = currentNod.left;
//				validateBinaryTree(currentNod);
//			} else {
//				isValid = false;
//				return;
//			}
//
//		if (currentNod.right != null)
//			if (currentNod.data < currentNod.right.data) {
//				currentNod = currentNod.right;
//				validateBinaryTree(currentNod);
//			} else {
//				isValid = false;
//				return;
//			}
		
		validateBinaryTree(currentNod.left);
		validateBinaryTree(currentNod.right);

	}

	public static void createTree(TreeNode currentNode, TreeNode newNode) {
		if (rootNode == null) {
			rootNode = currentNode;
			rootNode.max = 0;
			rootNode.min = 0;
			return;
		}

		if (newNode.data > currentNode.data)
			if (currentNode.right != null)
				createTree(currentNode.right, newNode);
			else {
				newNode.min = currentNode.data + 1;
				newNode.max = currentNode.max;
				currentNode.right = newNode;
				return;
			}

		if (newNode.data <= currentNode.data)
			if (currentNode.left != null)
				createTree(currentNode.left, newNode);
			else {
				newNode.min = currentNode.min;
				newNode.max = currentNode.data;
				currentNode.left = newNode;
				return;
			}
	}

	public static void parseTree(TreeNode currentNode) {

		if (currentNode == null)
			return;

		if (currentNode.right != null) {
			currentNode.right.min = currentNode.data + 1;
			currentNode.right.max = currentNode.max;
		}

		if (currentNode.left != null) {
			currentNode.left.min = currentNode.min;
			currentNode.left.max = currentNode.data;
		}
		parseTree(currentNode.left);
		parseTree(currentNode.right);
	}

	public static TreeNode createBinaryTree() {

		rootNode = new TreeNode(10);
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

//		createTree(rootNode, rootNode);
//		createTree(rootNode, node5);
//		createTree(rootNode, node20);
//		createTree(rootNode, node2);
//		createTree(rootNode, node8);
//		createTree(rootNode, node15);
//		createTree(rootNode, node25);
//		createTree(rootNode, node1);
//		createTree(rootNode, node3);
//		createTree(rootNode, node7);
//		createTree(rootNode, node9);
//		createTree(rootNode, node12);
//		createTree(rootNode, node17);
//		createTree(rootNode, node22);
//		createTree(rootNode, node28);

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
