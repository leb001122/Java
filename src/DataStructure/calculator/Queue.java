package calculator;

import java.util.NoSuchElementException;

public class Queue<E> { // FIFO
    private Node<E> front;
    private Node<E> rear;
    private int size;

    public Queue()
    {
        front = rear = null;
        size = 0;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void enqueue(E data) // addLast
    {
        Node<E> newNode = new Node<>(data, null);
        if(isEmpty())
            front = newNode;
        else
            rear.setNext(newNode);
        rear = newNode;
        size++;
    }

    public E first()
    {
        if(isEmpty())
            throw new NoSuchElementException();
        return front.getData();
    }

    public E dequeue() // removeFirst
    {
        if(isEmpty())
            throw new NoSuchElementException();
        E data = front.getData();
        front = front.getNext();
        if(front == null)
            rear = null;
        size--;
        return data;
    }

    public E rear()
    {
        if(isEmpty())
            throw new NoSuchElementException();
        return rear.getData();
    }
}
