package DataStructure.DoublyCircularLinkedList;

public class DoublyCircularLinkedList<E> {
    Node<E> head;
    int size;

    public DoublyCircularLinkedList()
    {
        this.head = new Node<>(null);
        head.setNext(head);
        head.setPrev(head);
        this.size = 0;
    }

    void addNext(E data, Node<E> prevNode)
    {
        Node<E> newNode = new Node(prevNode, data, prevNode.getNext());
        prevNode.getNext().setPrev(newNode);
        prevNode.setNext(newNode);
        size++;
    }

    void remove(Node<E> node)
    {
        Node<E> prevNode = node.getPrev();
        Node<E> nextNode = node.getNext();
        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);

        node.setData(null);
        node.setPrev(null);
        node.setNext(null);
        size--;
    }

    int size()
    {
        return size;
    }

    boolean isEmpty()
    {
        return size==0;
    }
}
