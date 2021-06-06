package completeBinaryTree;

import java.util.NoSuchElementException;

public class CircularQueue<E> {
	private E [] queue;
	private int front;
	private int rear;
	private int size;
	
	public CircularQueue()
	{
		queue = (E[]) new Object[4];
		front = rear = size = 0;
	}
	
	public int getSize() 
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	public void resize(int newSize)
	{
		Object [] temp = new Object[newSize];
		for(int i = 1, j = front + 1; i < size + 1; i++, j++)
			temp[i] = queue[j % queue.length];
		
		front = 0;
		rear = size;
		queue = (E[]) temp;	
	}
	
	public void add(E newValue)
	{
		if((rear + 1) % queue.length == front)
			resize(2 * queue.length);
		rear = (rear + 1) % queue.length;
		queue[rear] = newValue;
		size++;
	}
	
	public E remove()
	{
		if(isEmpty())
			throw new NoSuchElementException();
		front = (front + 1) % queue.length;
		E item = queue[front];
		queue[front] = null;
		size--;
		if(size > 0 && size == queue.length / 4)
			resize(queue.length / 2);
		
		return item;
	}
}



























