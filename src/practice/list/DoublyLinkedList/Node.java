package practice.list.DoublyLinkedList;

class Node<E> {
    Node<E> prev;
    E data;
    Node<E> next;

    Node(E data)
    {
        this.prev = null;
        this.data = data;
        this.next = null;
    }

}
