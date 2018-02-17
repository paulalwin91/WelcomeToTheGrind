package PCT;

public class FamTreeBST {
	static Node root;
	static StringBuilder strTree = new StringBuilder();
	static StringBuilder testTree = new StringBuilder();
	static Boolean isParent = false;
	static Boolean isSibling = false;
	static Boolean isAnsector = false;
	static Boolean isDescendant = false;

	public static void main(String[] args) {
		int relCount = 0;
		int testCount = 0;

		// relCount = 8;
		// String[] relArray = new String[relCount];
		// relArray[0] = "Motilal Jawahar";
		// relArray[1] = "Jawahar Indira";
		// relArray[2] = "Motilal KAmla";
		// relArray[3] = "Indira Sanjay";
		// relArray[4] = "Sanjay Varun";
		// relArray[5] = "Indira Rajiv";
		// relArray[6] = "Rajiv Priyanka";
		// relArray[7] = "Rajiv Rahul";
		relCount = 9;
		String[] relArray = new String[relCount];
		relArray[0] = "PR R";
		relArray[1] = "Sh Sa";
		relArray[2] = "PR Sh";
		relArray[3] = "R RD";
		relArray[4] = "RI RN";
		relArray[5] = "RD BEB";
		relArray[6] = "RD LO";
		relArray[7] = "R RI";
		relArray[8] = "RI RDM";

		for (String reltn : relArray) {
			makeTree(root, reltn);
		}

		testCount = 7;
		String[] testArr = new String[testCount];
		testArr[0] = "BEB descendant Sh";
		testArr[1] = "R sibling Sh";
		testArr[2] = "PR ancestor RDM";
		testArr[3] = "LO sibling RDM";
		testArr[4] = "BEB ancestor Sh";
		testArr[5] = "PR ancestor R";
		testArr[6] = "RI descendant R";

		for (int i = 0; i < testArr.length; i++) {
			driver(root, testArr[i].split(" ")[0], testArr[i].split(" ")[2], testArr[i].split(" ")[1]);
		}
		System.out.println(testTree.toString().trim());
		printTree(root);
		System.out.println(strTree.toString());
	}

	public static void driver(Node root, String A, String B, String paran) {

		if (paran.equals("descendant")) {
			isDescendant = false;
			isAnsector = false;
			isDesc(root, A, B);
			if (isDescendant)
				testTree.append("T ");
			else
				testTree.append("F ");
			return;
		}
		if (paran.equals("sibling")) {
			isSibling = false;
			isSibling(root, A, B);
			if (isSibling)
				testTree.append("T ");
			else
				testTree.append("F ");
			return;
		}
		if (paran.equals("parent")) {
			isParent = false;
			isParent(root, A, B);
			if (isDescendant)
				testTree.append("T ");
			else
				testTree.append("F ");
			return;
		}
		if (paran.equals("ancestor")) {
			isAnsector = false;
			isDescendant = false;
			isAnsector(root, A, B);
			if (isAnsector)
				testTree.append("T ");
			else
				testTree.append("F ");
			return;
		}
	}

	public static void isParent(Node currentNode, String A, String B) {
		// isParent = false;
		if (currentNode == null)
			return;

		if (currentNode.nodeValue.equals(A)) {
			if ((currentNode.lChild != null && currentNode.lChild.nodeValue.equals(B))
					|| (currentNode.rChild != null && currentNode.rChild.nodeValue.equals(B))) {
				isParent = true;
				return;
			} else {
				isParent = false;
				return;
			}
		} else {
			isParent(currentNode.lChild, A, B);
			isParent(currentNode.rChild, A, B);
		}
	}

	public static void isSibling(Node currentNode, String A, String B) {
		// isSibling = false;
		if (currentNode == null)
			return;

		if (currentNode.nodeValue.equals(A)) {

			if (currentNode.parent.lChild.nodeValue.equals(B) || currentNode.parent.rChild.nodeValue.equals(B)) {
				isSibling = true;
				return;
			} else {
				isSibling = false;
				return;
			}

		} else {
			isSibling(currentNode.lChild, A, B);
			isSibling(currentNode.rChild, A, B);
		}
	}

	public static void isAnsector(Node currentNode, String A, String B) {
		// isAnsector = false;
		if (currentNode == null)
			return;

		if (currentNode.nodeValue.equals(B)) {
			anscRecur(currentNode, A);
		} else {
			isAnsector(currentNode.lChild, A, B);
			isAnsector(currentNode.rChild, A, B);
		}
	}

	public static void isDesc(Node currentNode, String A, String B) {
		// isDescendant = false;
		// isAnsector = false;
		isAnsector(currentNode, B, A);
		if (isAnsector)
			isDescendant = true;
	}

	public static void anscRecur(Node currentNode, String A) {
		if (currentNode == null)
			return;

		if (currentNode.nodeValue.equals(A)) {
			isAnsector = true;
			return;
		} else {
			anscRecur(currentNode.parent, A);
		}
	}

	public static void makeTree(Node currentNode, String rel) {

		if (root == null) {
			root = new Node(rel.split(" ")[0], null);
			root.lChild = new Node(rel.split(" ")[1], root);
			return;
		}

		if (!currentNode.nodeValue.equals(rel.split(" ")[0])) {
			if (currentNode.lChild != null)
				makeTree(currentNode.lChild, rel);
			if (currentNode.rChild != null)
				makeTree(currentNode.rChild, rel);
		} else {
			if (currentNode.lChild == null) {
				currentNode.lChild = new Node(rel.split(" ")[1], currentNode);
				return;
			}
			if (currentNode.rChild == null) {
				currentNode.rChild = new Node(rel.split(" ")[1], currentNode);
				return;
			}
		}
	}

	public static void printTree(Node cuNode) {
		strTree.append(cuNode.nodeValue + " ");
		if (cuNode.lChild != null)
			printTree(cuNode.lChild);

		if (cuNode.rChild != null)
			printTree(cuNode.rChild);
	}
}

class Node {
	Node lChild;
	Node rChild;
	Node parent;

	String nodeValue;

	public Node(String value, Node pr) {
		this.nodeValue = value;
		this.parent = pr;
	}
	
	public Node(String value) {
		this.nodeValue = value;
		this.parent = null;
	}

}
