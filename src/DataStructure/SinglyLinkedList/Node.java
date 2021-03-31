package DataStructure.SinglyLinkedList;

public class Node<E> {
    private E data;
    private Node<E> next; // 다음 노드 객체를 가리키는 레퍼런스 변수

    Node(E data)
    {
        this.data = data;
        this.next = null;
    }
    Node(E data, Node<E> next)
    {
        this.data = data;
        this.next = next;
    }

    public E getData() { return data; }
    public Node<E> getNext() { return next; }
    public void setData(E data) { this.data = data; }
    public void setNext(Node next) { this.next = next; }

    public String toString()
    {
        return data.toString();
    }
}