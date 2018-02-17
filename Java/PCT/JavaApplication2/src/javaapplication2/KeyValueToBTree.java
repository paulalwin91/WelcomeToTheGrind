//package javaapplication2;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//
//public class KeyValueToBTree {
//	
//	public static Boolean isDescendent = false;
//	public static Boolean isAscendent= false;
//	public static int isDepth =0;
//	
//	public static void main(String[] args)
//	{
//		Node MainRoot = new Node(0,0);
//		KeyValueToBTree mainObj = new KeyValueToBTree();
//		//HashMap<String, String> treeHashMap = new HashMap<String,String>();
//		List<String[]> treeHashMap = new ArrayList<String[]>();
//		
//		treeHashMap.add(new String[] {"Motilal","Jawahar"});
//		treeHashMap.add(new String[] {"Jawahar" ,"Indira"});
//		treeHashMap.add(new String[] {"Motilal" ,"Kamala"});
//		treeHashMap.add(new String[] {"Indira" ,"Sanjay"});
//		treeHashMap.add(new String[] {"Sanjay" ,"Varun"});
//		treeHashMap.add(new String[] {"Indira","Rajiv"});
//		treeHashMap.add(new String[] {"Rajiv","Priyanka"});
//		treeHashMap.add(new String[] {"Rajiv", "Rahul"});
//
//		printTreeFromHash(treeHashMap, MainRoot);
//		
//		List<String[]> queries = new ArrayList<String[]>();
//		queries.add(new String[]{"Motilal", "child", "Jawahar"});
//		queries.add(new String[]{"Varun", "child", "Indira"});   
//		queries.add(new String[]{"Rajiv", "child", "Priyanka"});
//		queries.add(new String[]{"Sanjay", "ancestor", "Motilal"});
//		queries.add(new String[]{"Motilal", "descendant", "Sanjay"});
//		queries.add(new String[]{"Varun", "sibling", "Priyanka"});
//		
//		generateOutput(queries,MainRoot);
//		
//		
//	}
//	
//	public static void addNodeChild(String parent, String child, Node root)
//	{
//		Node childNode;
//		if(root.left == null || root.right ==null && parent.equals(root.Key))
//		{
//			childNode = new Node(child);
//			if (root.left == null)// check parent and root has to be same to add it
//	              root.left = childNode;
//	              else if(root.right == null)
//	            	  root.right = childNode;	             
//		}
//		 else
//         {
//       	  traverseDownAndAddChild(parent,child,root);	            	  
//         }
//	}
//	
//	public static void traverseDownAndAddChild(String parent, String child, Node root)
//	{
//		Node childNode;
//		if(root == null)
//			return;
//		
//		if(root.Key.equals(parent))
//		{
//			if(root.left == null || root.right ==null)
//			{
//				childNode = new Node(child);
//				if (root.left == null)
//		              root.left = childNode;
//		              else if(root.right == null)
//		            	  root.right = childNode;
//           }
//
//		return;	
//		}
//		
//		traverseDownAndAddChild(parent, child, root.left);
//		traverseDownAndAddChild(parent, child, root.right);
//		
//	}
//	
//	public static void printTreeFromHash(List<String[]> treeHashMap, Node MainRoot)
//	{
//		
//		if (treeHashMap.size() > 0)
//		{
//			MainRoot.Key = treeHashMap.get(0)[0];
//			addNodeChild(treeHashMap.get(0)[0],treeHashMap.get(0)[1] , MainRoot); 	        
//		}
//		
//		for(int i = 1; i< treeHashMap.size();i++)
//		{
//	        addNodeChild(treeHashMap.get(i)[0], treeHashMap.get(i)[1], MainRoot); 
//	        
//		}	
//	}
//	
//	public static void generateOutput(List<String[]> queries,Node MainRoot)
//	{
//		KeyValueToBTree obj = new KeyValueToBTree();
//		for (String[] strings : queries) {
//			switch (strings[1]) {
//            case "child":  
//            	isChild(strings[0], strings[2],MainRoot);
//                     break;
//            case "descendant":  
//            	isDescendent(strings[0], strings[2],MainRoot);
//            	if(isDescendent)
//            		System.out.print("T ");
//            	else
//            		System.out.print("F ");
//            	
//            	isDescendent = false;
//            	
//                     break;
//            case "sibling":  
//            	isSibling(strings[0], strings[2],MainRoot);
//                     break;
//            case "ancestor":  
//            	isAscendent(strings[0], strings[2],MainRoot);
//            	if(isDescendent) //As we give inverse values while calling 
//            		System.out.print("T ");
//            	else
//            		System.out.print("F ");
//            	
//            	
//            	isDescendent = false;
//                     break;
//			}
//		}
//		
//	}
//	
//	
//	public static void isAscendent(String personOne,String personTwo, Node mainRoots)
//	{
//		isDescendent(personTwo,personOne,mainRoots);
//	}
//	
//	public static void traverseDescendent(String personOne,String personTwo, Node mainRoots)
//	{
//		if(mainRoots == null)
//			return;
//		
//
//	    if(mainRoots.Key.equals(personTwo))
//	    {
//	    	
//	    isDescendent = true;
//	    	return;
//	    }
//	    traverseDescendent(personOne,personTwo,mainRoots.left);
//	    traverseDescendent(personOne,personTwo,mainRoots.right);
//	    
//	}
//	
//	
//	public  static void isDescendent(String personOne,String personTwo, Node mainRoots)
//	{
//		
//		if(mainRoots == null)
//			return;
//		
//	    if(mainRoots.Key.equals(personOne))
//	    {
//	    	
//
//	    		    	
//
//		if((mainRoots.left !=null && mainRoots.left.Key.equals(personTwo))  || (mainRoots.right !=null && mainRoots.right.Key.equals(personTwo)))
//			 isDescendent = true;
//		else
//	    	traverseDescendent(personOne,personTwo,mainRoots);
//		
//		return;
//	    }
//		
//	    isDescendent(personOne,personTwo,mainRoots.left);
//	    isDescendent(personOne,personTwo,mainRoots.right);
//
//	}
//	
//	public static void isChild(String personOne,String personTwo, Node mainRoots)
//	{
//	  
//		if(mainRoots == null)
//			return;
//		
//	    if(mainRoots.Key.equals(personOne))
//	    {
//	    	
//	    	if((mainRoots.left !=null && mainRoots.left.Key.equals(personTwo))  || (mainRoots.right !=null && mainRoots.right.Key.equals(personTwo)))
//	    			System.out.print("T ");
//	    	else
//	    		System.out.print("F ");
//	    	
//	    	return;
//	    }
//		
//	    isChild(personOne,personTwo,mainRoots.left);
//	    isChild(personOne,personTwo,mainRoots.right);
//
//	}
//	public static void getDepthCount(String personOne,Node mainRoots)
//	{
//	
//		if(mainRoots == null)
//			{
//			isDepth = isDepth - 1;
//			System.out.println(isDepth);
//			return;
//			}
//		
//	    if(mainRoots.Key.equals(personOne))
//	    {	    	
//	    	return;
//	    }
//	    else
//	    {
//			System.out.println(isDepth);
//	    	isDepth = isDepth +1;
//	    }
//	    getDepthCount(personOne,mainRoots.left);
//	    getDepthCount(personOne,mainRoots.right);
//	}
//	
//	
//	public static void isSibling(String personOne,String personTwo, Node MainRoots)
//	{
//		int DepthOne, DepthTwo;
//		getDepthCount(personOne, MainRoots);
//		System.out.println(isDepth);
//		DepthOne = isDepth;
//		isDepth = 0;
//		
//		getDepthCount(personTwo, MainRoots);
//		System.out.println(isDepth);
//		DepthTwo = isDepth;
//		
//		isDepth = 0;
//		
//		if (DepthOne == DepthTwo)
//		{
//			System.out.print("T " + DepthOne + " " + DepthTwo);
//		}
//		else
//		{
//			System.out.print("F "+ DepthOne + " " + DepthTwo);
//		}
//	}
//	
//	
//	
//	
//	
//	
//	
//}
