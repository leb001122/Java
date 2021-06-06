package binarySearchTree;

public class Node <Key extends Comparable<Key>> {
	private Key name;
	private Node<Key> left;
	private Node<Key> right;
	
	public Node (Key newName)
	{
		name = newName;
		left = right = null;
	}
	
	public Key getKey() { return name; }
	
	public Node<Key> getLeft() { return left; }
	
	public Node<Key> getRight() { return right; }
	
	public void setKey(Key newName) { name = newName; }
	
	public void setLeft(Node<Key> newLeft) { left = newLeft; }
	
	public void setRight(Node<Key> newRight) { right = newRight; }
	
	
}
