package PCT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TreeEquavliance {

	static Node[] rootList;
	static Boolean isTreeDiff = false;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int treeCount;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		treeCount = Integer.valueOf(br.readLine());

		String[] treeContent = new String[treeCount];
		rootList = new Node[treeCount];

		for (int i = 0; i < treeCount; i++) {
			treeContent[i] = br.readLine();
		}

		for (int i = 0; i < treeCount; i++) {
			for (String newVal : treeContent[i].split(" "))
				if (!newVal.equals("-1"))
					makeTree(rootList[i], newVal, i);
		}

		// for (int i = 0; i < rootList.length - 1; i++) {
		// isTreeDiff = false;
		// compareTrees(rootList[0], rootList[i + 1]);
		// if (!isTreeDiff)
		// System.out.println("YES");
		// else
		// System.out.println("NO");
		// }

		System.out.println(getDepth(rootList[0]));

	}

	public static void makeTree(Node currentNode, String newVal, int index) {
		if (rootList[index] == null) {
			rootList[index] = new Node(newVal);
			return;
		}

		if (Integer.valueOf(newVal) >= Integer.valueOf(currentNode.nodeValue))
			if (currentNode.rChild == null)
				currentNode.rChild = new Node(newVal);
			else
				makeTree(currentNode.rChild, newVal, index);

		if (Integer.valueOf(newVal) < Integer.valueOf(currentNode.nodeValue))
			if (currentNode.lChild == null)
				currentNode.lChild = new Node(newVal);
			else
				makeTree(currentNode.lChild, newVal, index);
	}

	public static int getDepth(Node currentNode) {
		// 1 (current) + max(lefttree height, right tree height)

		if (currentNode == null)
			return 0;
		else {
			/* compute the depth of each subtree */
			int lDepth = getDepth(currentNode.lChild);
			int rDepth = getDepth(currentNode.rChild);

			/* use the larger one */
			if (lDepth > rDepth)
				return (lDepth + 1);
			else
				return (rDepth + 1);
		}
	}

	public static void compareTrees(Node basis, Node tree) {
		if (basis == null && tree == null)
			return;

		if ((basis == null && tree != null) || (basis != null && tree == null)) {
			isTreeDiff = true;
			return;
		}

		if (basis != null && tree != null) {
			compareTrees(basis.lChild, tree.lChild);
			compareTrees(basis.rChild, tree.rChild);
		}
	}

	// public static int getDeep(Node root) {
	// if(root == null)
	// return 0;
	//
	// if(root.lChild == null && root.rChild == null)
	// return 1;
	//
	//
	//
	// }

}
