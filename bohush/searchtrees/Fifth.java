package bohush.searchtrees;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.io.*;

class Node
{
	int value;
	int high;
	Node left;
	Node right;
	
	Node (int value)
	{
		this.value = value;
		left = null;
		right = null;
		high = -1;
	}
}


class BinaryTree
{
	private Node root;
	private ArrayList<Node> forks = new ArrayList<>();
	
	BinaryTree()
	{
		this.root = null;
	}
	
	public void insert(int value)
	{		
		if (root == null)
		{
			root = new Node(value);
			root.high = 1;
			return;
		}
		Node currentRoot = root;
		
		while(true)
		{
			if (value < currentRoot.value)
			{
				if (currentRoot.left == null)
				{
					currentRoot.left = new Node(value);
					return;
				}
				currentRoot = currentRoot.left;
			}
				
			else if (value > currentRoot.value)
			{
				if (currentRoot.right == null)
				{
					currentRoot.right = new Node(value);
					return;
				}
				currentRoot = currentRoot.right;
			}
			else
			{
				return;
			}
		}
	}
	
	
	public void preOrder(FileWriter writer) throws Exception
	{
		if (root == null)
		{
			return;
		}
		Stack<Node> tree = new Stack<>();
		tree.push(root);
		
		while(!tree.isEmpty())
		{
			Node current = tree.pop();
			writer.write(String.valueOf(current.value) + System.lineSeparator());
			if (current.right != null) tree.push(current.right);
			if (current.left != null) tree.push(current.left);
		}
	}
	
	public void postOrder() 
	{
	    if (root == null)
	    {
	        return;
	    }

	    Stack<Node> stack1 = new Stack<>();
	    Stack<Node> stack2 = new Stack<>();

	    stack1.push(root);

	    while (!stack1.isEmpty())
	    {
	        Node current = stack1.pop();
	        stack2.push(current);

	        if (current.left != null)
	            stack1.push(current.left);
	        if (current.right != null)
	            stack1.push(current.right);
	    }

	    while (!stack2.isEmpty())
	    {
	        Node current = stack2.pop();
	        if (current.left == null && current.right == null)
	        	current.high = 0;
	        else if (current.left == null)
	        	current.high = current.right.high + 1;
	        else if (current.right == null)
	        	current.high = current.left.high + 1;
	        else
	        {
	        	forks.add(current);
	        	current.high = Math.max(current.left.high, current.right.high) + 1;
	        }
	    }
	    
	}
	
	

	public void rightDelete(int value)
	{		
		Node parent = null;
		Node current = root;
		
		while(current != null && current.value != value)
		{
			parent = current;
			if (value < current.value)
			{
				current = current.left;
			}
			else if (value > current.value)
			{
				current = current.right;
			}
		}
		
		if (current == null) return;
		
		if (current.left == null && current.right == null)
		{
			if (parent == null)
				root = null;
			else if (parent.left == current)
				parent.left = null;
			else
				parent.right = null;
			return;
		}
		else if (current.left == null || current.right == null)
		{
			if (parent == null)
			{
				root = (current.left != null) ? current.left : current.right;
			}
			else if (current.left != null)
			{
				if (parent.right == current)
					parent.right = current.left;
				else
					parent.left = current.left;
			}
			else
			{
				if (parent.right == current)
					parent.right = current.right;
				else
					parent.left = current.right;
			}
			return;
		}
		else
		{
			Node newParent = current;
			Node newCurrent = current.right;
			
			while(newCurrent.left != null)
			{
				newParent = newCurrent;
				newCurrent = newCurrent.left;
			}
			
			current.value = newCurrent.value;
			
			if (newParent.left == newCurrent)
				newParent.left = newCurrent.right;
			else
				newParent.right = newCurrent.right;
		}
	}
	
	public int findMinSumRoot() throws Exception {
        long minSum = Long.MAX_VALUE;
        long minLen = Long.MAX_VALUE;
        long minCenter = Long.MAX_VALUE;
        long minRootValue = Long.MAX_VALUE;
        boolean found = false;

        for (Node currentRoot : forks) {
            long[] sumAndCount = new long[]{currentRoot.value, 1};
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(currentRoot.value);

            traverseBranch(currentRoot.left, arr, sumAndCount, true);
            traverseBranch(currentRoot.right, arr, sumAndCount, false);


            long center = arr.get((arr.size() - 1) / 2);
            long length = sumAndCount[1];
            long sum = sumAndCount[0];
            System.out.println("Currentroot value - " + currentRoot.value + ", sum - " + sum + ", count  - " + length + ", center - " + center);
            System.out.println("Current arr: ");
            for (int i : arr)
            {
            	System.out.print(i + " ");
            }
            System.out.println(" "); 

            if (
                (length < minLen ||
                 (length == minLen && sum < minSum) ||
                 (length == minLen && sum == minSum && currentRoot.value  < minRootValue))){
            	minLen = length;
                	 if (length % 2 == 1) {
                		 	minSum = sum;
                		 	minCenter = center;
                		 	minRootValue = currentRoot.value;
                		 	found = true;
                	 }
            }
        }

        if (found) return (int) minCenter;
        else throw new Exception("Минимальный центр не найден");
    }

    private static void traverseBranch(Node start, ArrayList<Integer> arr, long[] sumAndCount, boolean ex) {
        Node current = start;
        while (current != null) {
            sumAndCount[0] += current.value;
            sumAndCount[1]++;
            arr.add(current.value);

            if ((current.left == null && current.right != null) ||
                (current.left != null && current.right != null && current.left.high > current.right.high))
                current = current.right;
            else
                current = current.left;
        }
        if (ex)
        {
            Collections.reverse(arr);
        }
    }
		
}


public class Fifth {
	public static void main(String[] args) throws Exception
	{
		BinaryTree first = new BinaryTree();
		Scanner objIn = new Scanner(new File("in.txt"));
		String str;
		while(objIn.hasNextLine())
		{
			str = objIn.nextLine().trim();
			if (str.isEmpty()) continue;
			first.insert(Integer.parseInt(str));
		}
		objIn.close();
		first.postOrder();
		try
		{
			first.rightDelete(first.findMinSumRoot());
			}
		catch(Exception e)
		{}
		FileWriter writer = new FileWriter("out.txt");
		first.preOrder(writer);
		writer.close();
	}
}
