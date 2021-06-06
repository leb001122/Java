package completeBinaryTree;

public class BinaryArrayTree {
	
	private int [] array;
	
	public BinaryArrayTree(int nodeNum)
	{
		array = new int[nodeNum + 1];
		array[1] = 1;
		for(int i = 2; i <= nodeNum; i += 2)
		{
			array[i] = i / 2;
			if(i+1 <= nodeNum)
				array[i+1] = i / 2;
		}
	}
	

	public int getLeft(int nodeNum)
	{
		return nodeNum * 2;
	}
	
	public int getRight(int nodeNum)
	{
		return nodeNum * 2 + 1;
	}
	
	public void preorder()
	{
		preorder(1);
		System.out.println();
	}
	
	public void preorder(int nodeNum)
	{
		if(nodeNum < array.length) 
		{
			System.out.print(nodeNum +" ");
			preorder(getLeft(nodeNum));
			preorder(getRight(nodeNum));
			
		}
	}
	
	public void inorder()
	{
		inorder(1);
		System.out.println();
	}
	
	public void inorder(int nodeNum)
	{
		if(nodeNum < array.length)
		{
			inorder(getLeft(nodeNum));
			System.out.print(nodeNum + " ");
			inorder(getRight(nodeNum));
		}
	}
	
	public void postorder()
	{
		postorder(1);
		System.out.println();
	}
	
	public void postorder(int nodeNum)
	{
		if(nodeNum < array.length)
		{
			postorder(getLeft(nodeNum));
			postorder(getRight(nodeNum));
			System.out.print(nodeNum + " ");
		}
		
	}
	
	public void levelorder()
	{
		levelorder(1);
		System.out.println();
	}
	
	public void levelorder(int nodeNum)
	{
		CircularQueue<Integer> queue = new CircularQueue<>();
		queue.add(nodeNum);
		while(!queue.isEmpty())
		{
			int num = queue.remove();
			System.out.print(num + " ");
			if(getLeft(num) < array.length)
				queue.add(getLeft(num));
			if(getRight(num) < array.length)
				queue.add(getRight(num));
		}
	}
	
	public boolean hasChild(int nodeNum)
	{
		if(getLeft(nodeNum) < array.length)
			return true;
		return false;
	}
	
	public void printTree()
	{
		printTree(1, "", "");
	}
	
	public void printTree(int idx, String front, String child)
	{
		if(idx >= array.length)
		{	
			if(idx - 1 < array.length)
				System.out.println(front + "X");			
			return;
		}
		
		System.out.println(front + idx);
		
		printTree(2 * idx + 1, child + "戍式式 ", child + "弛  ");
		printTree(2 * idx, child + "戌式式 ", child + "   ");
	}
}






























