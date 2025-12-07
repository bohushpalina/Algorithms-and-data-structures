package bohush.searchtrees;

import java.util.Scanner;
import java.util.Stack;
import java.io.*;


class Node
{
	int value;
	Node left;
	Node right;
	
	Node (int value)
	{
		this.value = value;
		left = null;
		right = null;
	}
}


class BinaryTree
{
	private Node root;
	
	BinaryTree()
	{
		this.root = null;
	}
	
	public void insert(int value)
	{		
		if (root == null)
		{
			root = new Node(value);
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
	
	
}

public class BuildTree {
	public static void main(String[] args) throws Exception
	{
		BinaryTree first = new BinaryTree();
		Scanner objIn = new Scanner(new File("input.txt"));
		String str;
		while(objIn.hasNextLine())
		{
			str = objIn.nextLine().trim();
			if (str.isEmpty()) continue;
			first.insert(Integer.parseInt(str));
		}
		objIn.close();
		FileWriter writer = new FileWriter("output.txt");
		
		first.preOrder(writer);
		writer.close();
	}
}