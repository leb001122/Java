package calculator;

import java.util.NoSuchElementException;

public class Stack<E> { // LIFO
    private Node<E> top;
    private int size;

    public Stack()
    {
        top = null;
        size = 0;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0;}

    public void push(E data)
    {
        Node<E> newNode = new Node<>(data, top);
        top = newNode;
        size++;
    }

    public E peek()
    {
        if(isEmpty())
            throw new NoSuchElementException();
        return top.getData();
    }

    public E pop()
    {
        if(isEmpty())
            throw new NoSuchElementException();
        E data = top.getData();
        top = top.getNext();
        size--;
        return data;
    }
}
