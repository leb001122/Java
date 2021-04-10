package DataStructure.DoublyCircularLinkedList;

class Node<E> {
    private Node<E> prev;
    private E data;
    private Node<E> next;

    Node(E data)
    {
        this.data = data;
    }

    Node(Node<E> prev, E data, Node<E> next)
    {
        this.prev = prev;
        this.data = data;
        this.next = next;
    }

    void setPrev(Node<E> prev) {this.prev = prev;}
    void setData(E data) { this.data = data; }
    void setNext(Node<E> next) {this.next = next;}
    Node<E> getPrev() {return prev;}
    E getData() {return data;}
    Node<E> getNext() {return next;}

    public String toString() { return data.toString(); }
}
