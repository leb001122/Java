package DataStructure.SinglyLinkedList;

import java.util.NoSuchElementException;

public class SingleList<E>
{
    Node<E> head;
    int size;

    public SingleList()
    {
        this.head = null;
        this.size = 0;
    }

    private void checkIndex(int index)
    {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    private Node<E> search(int index)
    {
        checkIndex(index); // 범위 밖일 경우 예외 던지기

        Node<E> temp = head;
        for(int i=0; i<index; i++)
            temp = temp.getNext();
        return temp;
    }

    public Node<E> search(E data)
    {
        for(Node<E> temp = head; temp!=null; temp=temp.getNext())
        {
            if(temp.getData().equals(data))
                return temp;
        }
        return null;
    }

    private Node<E> searchPrev(E data)
    {
        for(Node<E> now = head, prev = null;
             now != null;
            prev = now, now = now.getNext())
        {
            if(now.getData().equals(data))
            {
                return prev;   // 찾으려는 노드의 이전 노드 반환
            }
        }
        throw new NoSuchElementException();
    }

    // 특정 위치에 노드 추가
    public void add(E newData, int index)
    {
        if(index == 0) {
            addFirst(newData);
            return;
        }
        if(index == size-1)
        {
            addLast(newData);
            return;
        }
        checkIndex(index);
        insert(newData, index);
    }

    // 첫 번째에 노드 추가
    public void addFirst(E newData)
    {
        head = new Node<E>(newData, head);
        size++;
    }

    // 사이에 노드 추가
    public void insert(E newData, int index)
    {
        Node<E> prevNode = search(index-1); // 추가하려는 위치의 이전 노드
        Node<E> nextNode = prevNode.getNext(); // 추가하려는 위치의 노드
        Node<E> newNode = new Node<>(newData); // 추가히려는 노드

        prevNode.setNext(newNode);
        newNode.setNext(nextNode);
        size++;
    }

    // 마지막에 노드 추가
    public void addLast(E newData)
    {
        // search(int index) 메소드가 리스트의 마지막 노드 반환
        // 마지막 노드의 next를 새 노드로
        search(size-1).setNext(new Node<E>(newData));
        size++;
    }

    public void remove(E data)
    {
        if(searchPrev(data) == null)
        {
            removeFirst();
            return;
        }
        Node<E> prevNode = searchPrev(data);
        Node<E> now = prevNode.getNext();
        prevNode.setNext(now.getNext());
        now.setData(null);
        now.setNext(null);
        size--;
        return;
    }

    public void removeFirst()
    {
        Node<E> node = head;
        head = head.getNext();
        node.setData(null);
        node.setNext(null);
        size--;
        return;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }
}
