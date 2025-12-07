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
	
	public void rightDelete(int value, FileWriter writer) throws Exception
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
		
		if (current.left == null && current.right == null) //для листа или корня
		{
			if (parent == null)
			{
				root = null;
			}
			else if (parent.left == current)
			{
				parent.left = null;
			}
			else if (parent.right == current)
			{
				parent.right = null;
			}
			return;
		}
		else if (current.left == null || current.right == null) //для одного поддерева
		{
			if (parent == null)
			{
				if (current.left != null)
				{
					root = current.left;
				}
				else
				{
					root = current.right;
				}
			}
			else if (current.left != null)
			{
				 if (parent.right == current)
					 parent.right = current.left;
				 else if (parent.left == current)
					 parent.left = current.left;
			}
			else if (current.right != null)
			{
				if (parent.right == current)
					 parent.right = current.right;
				else if (parent.left == current)
					 parent.left = current.right;
				else
				{
					
				}
			}
			return;
		}
		
		else  //если два поддерева
		{
			Node newParent = current;
			Node newCurrent = current.right;
			
			while(newCurrent.left != null)
			{
				newParent = newCurrent;
				newCurrent = newCurrent.left;
			}
			
			current.value = newCurrent.value;
			
			
			if (newParent.left == newCurrent) {
			    newParent.left = newCurrent.right;
			} else {
			    newParent.right = newCurrent.right;
			}
			return;
		}
		
	
	}
	
}

public class RemoveFromTree {
	public static void main(String[] args) throws Exception
	{
		BinaryTree first = new BinaryTree();
		Scanner objIn = new Scanner(new File("input.txt"));
		String str;
		int toDelete = objIn.nextInt();
		while(objIn.hasNextLine())
		{
			str = objIn.nextLine().trim();
			if (str.isEmpty()) continue;
			first.insert(Integer.parseInt(str));
		}
		objIn.close();
		FileWriter writer = new FileWriter("output.txt");
		
		first.rightDelete(toDelete, writer);
		first.preOrder(writer);
		writer.close();
	}
}