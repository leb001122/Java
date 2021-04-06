package practice.list.DoublyLinkedList;

import Interface_form.List;

import java.util.NoSuchElementException;

// 더미 헤드X, 원형 아님
public class DLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 특정 위치의 노드를 반환하는 메소드
    private Node<E> search(int index) {
        if(index > size || index < 0)
            throw new IndexOutOfBoundsException();

        Node<E> node;

        if(index < size/2) // head부터 탐색
        {
            node = head;
            for(int i=0; i<index; i++)
                node = node.next;
        }
        else // tail부터 탐색
        {
            node = tail;
            for(int i=size-1; i>index; i--)
                node = node.prev;
        }
        return node;
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<>(value);
        newNode.next = head;

        /**
         * head가 null이 아닐 경우에만 기존 head노드의 prev변수가
         * 새 노드를 가르키도록 한다.
         * 이유는 기존 head노드가 없는 경우(null)는 데이터가
         * 아무 것도 없던 상태였으므로 head.prev를 하면 잘못된 참조가 된다.
         */
        if(head!=null)
            head.prev = newNode;

        head = newNode;
        size++;

        /**
         * 다음에 가리킬 노드가 없는 경우(=데이터가 새 노드밖에 없는 경우)
         * 데이터가 한 개(새 노드)밖에 없으므로 새 노드는 처음 시작노드이자
         * 마지막 노드이다.
         */
        if(head.next == null)
            tail = head;
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {
        if(size==0) {
            addFirst(value);
            return;
        }

        Node<E> newNode = new Node<>(value);
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    @Override
    public void add(int index, E value) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        if(index == 0) {
            addFirst(value);
            return;
        }
        if(index == size) {
            addLast(value);
            return;
        }

        Node<E> newNode = new Node<>(value);
        Node<E> prevNode = search(index-1);
        Node<E> nextNode = prevNode.next;

        newNode.prev = prevNode;
        newNode.next = nextNode;
        prevNode.next = newNode;
        nextNode.prev = newNode;
        size++;

    }

    // 가장 앞에 있는 요소 제거
    public void remove() {
        if(head == null)
            throw new NoSuchElementException();

        Node<E> nextNode = head.next;

        head.data = null;
        head.next = null;
        size--;

        /**
         * nextNode가 null이 아닐 경우에만
         * prev 변수를 null로 업데이트 해주어야 한다.
         * 이유는 nextNode가 null인 경우
         * 데이터가 아무것도 없던 상태였으므로 nextNode.prev를 하면
         * 잘못된 참조가 된다.
         */
        if(nextNode!=null) {
            nextNode.prev = null;
        }
        head = nextNode;

        if(size==0)
            tail = null;
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        if(index==0) {
            E element = head.data;
            remove();
            return element;
        }

        Node<E> removeNode = search(index);
        Node<E> prevNode = removeNode.prev;
        Node<E> nextNode = removeNode.next;

        E element = removeNode.data;

        removeNode.data = null;
        removeNode.prev = null;
        removeNode.next = null;
        size--;

        prevNode.next = nextNode;

        if(nextNode!=null)
            nextNode.prev = prevNode;
        else
            tail = prevNode;

        return element;
    }

    @Override
    public boolean remove(Object value) {
        Node<E> prevNode = head;
        boolean hasValue = false;
        Node<E> removeNode = head;

        for(; removeNode!=null; removeNode=removeNode.next) {
            if(value.equals(removeNode.data)){
                hasValue = true;
                break;
            }
            prevNode = removeNode;
        }

        if(!hasValue)
            return false;

        if(removeNode == head)
            remove();
        else {
            Node<E> nextNode = removeNode.next;

            removeNode.data = null;
            removeNode.prev = null;
            removeNode.next = null;
            size--;

            prevNode.next = nextNode;
            if(nextNode!=null)
                nextNode.prev = prevNode;
            else
                tail = prevNode;
        }
        return true;
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    // set은 교체 메소드임
    @Override
    public void set(int index, E value) {
        Node<E> replaceNode = search(index);
        replaceNode.data = value;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(Object value) { //  정방향 탐색
        int index = 0;

        for(Node<E> node=head; node!=null; node=node.next) {
            if(value.equals(node.data))
                return index;
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object value) { // 역방향 탐색
        int index = size-1;

        for(Node<E> node=tail; node!=null; node=node.prev) {
            if(value.equals(node.data))
                return index;
            index--;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void clear() {
        for(Node<E> node=head; node!=null;) {
            Node<E> nextNode = node.next;
            node.data = null;
            node.next = null;
            node.prev = null;
            node = nextNode;
        }
        head = tail = null;
        size = 0;
    }
}
