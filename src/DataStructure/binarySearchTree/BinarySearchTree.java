package binarySearchTree;

public class BinarySearchTree <Key extends Comparable<Key>>{
	private Node<Key> root;
	
	public BinarySearchTree(Key newName)
	{
		root = new Node<>(newName);
	}
	
	public Node<Key> getRoot() { return root; }

	public void put(Key name) 
	{
		root = put(root, name); 
	}
	
	public Node<Key> put(Node<Key> node, Key name)
	{
		if(node == null)
			return new Node<>(name);
		
		int t = node.getKey().compareTo(name);
		
		if(t > 0)
			node.setLeft(put(node.getLeft(), name));
		else if(t < 0)
			node.setRight(put(node.getRight(), name));
		// 같으면 그냥 node 리턴
		
		return node;
	}
	
	public void printTree()
	{
		printTree(root, "", "");
	}
	
	public void printTree(Node<Key> node, String front, String child)
	{
		if(node == null)
		{
			System.out.println(front + "X");
			return;
		}
		
		System.out.println(front + node.getKey());
		
		if(node.getRight() == null && node.getLeft() == null)
			return;
		
		printTree(node.getRight(), child + "├── ", child + "│   ");
		printTree(node.getLeft(), child + "└── ", child + "    ");
	}
}









