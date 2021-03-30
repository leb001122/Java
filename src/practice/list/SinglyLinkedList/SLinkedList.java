package practice.list.SinglyLinkedList;

import Interface_form.List;

import java.util.NoSuchElementException;

public class SLinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SLinkedList()
    {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /** 특정 위치의 노드를 반환하는 메소드
     * 특정 위치의 데이터를 삽입, 삭제, 검색하기 위해서는
     * 처음 노드(head)부터 next변수를 통해 특정 위치까지 찾아가야 한다.
     */
    private Node<E> search(int index)
    {
        // 범위 밖(잘못된 위치)일 경우 예외 던지기
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<E> temp = head;

        for(int i=0; i<index; i++)
            temp = temp.next; // temp 노드의 다음 노드를 temp에 저장

        return temp;
    }

    /**
     * addFirst(E value)
     * 데이터를 이동시키는 것이 아닌 새로운 노드를 생성하고
     * 새 노드의 레퍼런스 변수(next)가 head 노드를 가리키도록 해준다.
     */
    public void addFirst(E value)
    {
        Node<E> newNode = new Node<>(value); // 새 노드 생성
        newNode.next = head; // 새 노드의 다음 노드로 head 노드를 연결
        head = newNode; // head가 가리키는 노드를 새 노드로 변경
        size++;

        /**
         * 다음에 가리킬 노드가 없는 경우 (데이터가 새 노드밖에 없는 경우)
         * 데이터가 한 개(새 노드)밖에 없으므로 새 노드는 처음 시작 노드이자
         * 마지막 노드이다. 즉 tail = head이다.
         */
        if(head.next == null)
            tail = head;
    }

    /**
     * 기본 삽입 : add(E value) & addLast(E value)
     * add()의 기본 값은 addLast()이다.
     * LinkedList API를 보면 add메소드를 호출할 경우 add()는 addLast()를 호출.
     * size가 0일 경우(아무런 노드가 없는 경우)는 결국 처음으로 데이터를
     *      추가한다는 뜻이므로 addFirst()를 호출해준다.
     */

    @Override
    public boolean add(E value)
    {
        addLast(value);
        return true;
    }

    public void addLast(E value)
    {
        Node<E> newNode = new Node<>(value); // 새 노드 생성

        if(size == 0)  // 처음 넣는 노드일 경우 addFirst로 추가
        {
            addFirst(value);
            return;
        }

        /**
         * 마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 하고
         * tail이 가리키는 노드를 새 노드로 바꿔준다.
         */
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    /**
     * add(int index, E value)
     * 넣으려는 위치의 이전 노드를 prevNode라고 하고,
     * 넣으려는 위치의 기존 노드를 nextNode라고 할때,
     * 앞서 만든 search()를 사용하여 넣으려는 위치의 -1위치의 노드(prevNode)를
     * 찾아내고, nextNode는 prevNode.next를 통해 찾는다.
     *
     * 그리고 prevNode의 링크를 새로 추가하려는 노드로 변경하고,
     * 새로 추가하려는 노드의 링크는 nextNode로 변경해준다.
     *
     * index 변수가 잘못된 위치를 참조할 수 있으므로
     * 이에 대한 예외처리로 IndexOutOfBoundsException을 한다.
     */
    @Override
    public void add(int index, E value)
    {
        // 잘못된 인덱스를 참조할 경우 예외 발생
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        // 추가하려는 index가 가장 앞에 추가하려는 경우 addFirst 호출
        if(index == 0)
        {
            addFirst(value);
            return;
        }
        // 추가하려는 index가 마지막 위치일 경우 addLast 호출
        if(index == size-1)
        {
            addLast(value);
            return;
        }

        // 추가하려는 위치의 이전 노드
        Node<E> prevNode = search(index-1);
        // 추가하려는 위치의 노드
        Node<E> nextNode = prevNode.next;
        // 추가하려는 노드
        Node<E> newNode = new Node<>(value);

        prevNode.next = null;
        prevNode.next = newNode;
        newNode.next = nextNode;
        size++;
    }

    /**
     * remove() : 가장 앞의 요소 제거
     * head가 가리키는 노드의 링크와 데이터를 null로 지워준 뒤
     * head를 다음 노드로 업데이트 해준다.
     * 그리고 삭제하려는 노드가 리스트에서 유일한 노드였을 경우
     * 해당 노드를 삭제하면 tail이 가리키는 노드 또한 없어지게 된다.
     * 이에 대해서도 정확하게 처리를 해주어야 한다.
     */
    public E remove()
    {
        if(head == null)
            throw new NoSuchElementException();

        // 삭제된 노드를 반환하기 위한 임시 변수
        E element = head.data;

        // head의 다음 노드
        Node<E> nextNode = head.next;

        // head 노드의 데이터들을 모두 삭제
        head.data = null;
        head.next = null;

        // head가 다음 노드를 가리키도록 업데이트
        head = nextNode;
        size--;

        /**
         * 삭제된 요소가 리스트의 유일한 요소였을 경우
         * 그 요소는 head이자 tail이었으므로
         * 삭제되면서 tail도 가리킬 요소가 없기 때문에
         * size가 0일 경우 tail도 null로 반환
         */
        if(size == 0)
            tail = null;

        return element;
    }

    /**
     * remove(int index) 메소드
     * 삭제하려는 노드의 이전 노드의 next변수를
     * 삭제하려는 노드의 다음 노드를 가리키도록 해주면 된다.
     */
    @Override
    public E remove(int index)
    {
        // 삭제하려는 노드가 첫 번째 원소일 경우
        if(index == 0)
            return remove();

        // 삭제하려는 노드가 리스트의 마지막 원소일 경우
        if(index == size-1)
            return removeLast();

        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<E> prevNode = search(index-1);    // 삭제할 노드의 이전 노드
        Node<E> removeNode = prevNode.next;          // 삭제할 노드
        Node<E> nextNode = removeNode.next;          // 삭제할 노드의 다음 노드

        E element = removeNode.data;    // 삭제되는 노드의 데이터를 반환하기 위한 임시변수

        prevNode.next = nextNode;   // 이전 노드가 가리키는 노드를 삭제하려는 노드의 다음 노드로 변경

        removeNode.data = null;     // 데이터 삭제제
        removeNode.next = null;
        size--;

        return element;
    }

    /**
     * removeLast() 메소드
     * tail 노드를 null로 만들어주고
     * tail을 삭제하려는 노드의 이전 노드로 바꾸어준다.
     */
    public E removeLast()
    {
        Node<E> removeNode = tail;

        E element = removeNode.data;

        Node<E> prevNode = search(size-2);
        tail = prevNode;

        removeNode.data = null;
        removeNode.next = null;
        size--;

        return element;
    }

    /**
     * remove(Object value) 메소드
     * remove(int index)와 동일한 메커니즘으로 작동한다.
     * 다만 고려해야할 점은 '삭제하려는 요소가 존재하는지' 를 염두에 두어야 한다.
     * 삭제하려는 요소를 찾지 못했을 경우 false를 반환, 찾았을 경우 remove(int index)와
     * 동일한 삭제 방식을 사용한다.
     */
    @Override
    public boolean remove(Object value)
    {
        Node<E> prevNode = head;
        boolean hasValue = false;
        Node<E> temp = head; // removeNode

        // value와 일치하는 노드 찾기
        for(; temp!=null; temp = temp.next)
        {
            if(value.equals(temp.data))
            {
                hasValue = true;
                break;
            }
            prevNode = temp;
        }

        // 만약 삭제하려는 노드가 head라면 기존 remove() 사용
        if(temp.equals(head))
        {
            remove();
            return true;
        }

        // 일치하는 노드가 없을 경우 false 반환
        else if(!hasValue)
            return false;

        else
        {
            // 이전 노드의 링크를 삭제하려는 노드의 다음 노드로 연결
            prevNode.next = temp.next;
            temp.data = null;
            temp.next = null;
            size--;
            return true;
        }
    }

    /**
     * get(int index) 메소드
     * search() 메소드를 이용하여 구현
     * search()는 '노드'를 반환
     * get()은 '노드의 데이터'를 반환
     * search() 내부에서 잘못된 위치일 경우 예외를 던지므로 따로 예외처리를 해줄 필요 없음.
     */
    @Override
    public E get(int index)
    {
        return search(index).data;
    }

    /**
     * set(int index, E value) 메소드
     * set메소드는 기존에 index에 위치한 데이터를 새로운 데이터(value)로 교체하는 것이다.
     * search()를 사용하여 노드를 찾아내고,
     * 해당 노드의 데이터만 새로운 데이터로 변경해준다.
     * 잘못된 인덱스를 참조하고 있진 않는지는 search() 안에서 인덱스 검사를 해주므로 구현 X
     */
    @Override
    public void set(int index, E value)
    {
        Node<E> replaceNode = search(index);
        replaceNode.data = null;
        replaceNode.data = value;
    }

    /**
     * indexOf(Object value) 메소드
     * 찾고자 하는 요소(value)의 위치(index)를 반환하는 메소드
     * 찾고자 하는 요소가 중복될 경우 가장 먼저 마주치는 요소의 인덱스를 반환한다.
     * 찾고자 하는 요소가 없다면 -1을 반환한다.
     */
    @Override
    public int indexOf(Object value)
    {
        int index = 0;

        for(Node<E> temp = head; temp!=null; temp = temp.next)
        {
            if(temp.equals(value))
                return index;
            index++;
        }

        // 찾고자 하는 요소를 찾지 못했을 경우 -1반환
        return -1;
    }

    /**
     * contains(Object value) 메소드
     * 찾고자 하는 요소(value)가 존재 하는지 안하는지를 반환하는 메소드이다.
     * 있다면 true, 없다면 false를 반환한다.
     * indexOf()를 사용하여
     * 만약 음수가 아닌 수가 반환되었다면 요소가 존재한다는 뜻이고,
     * -1이 나왔다면 존재하지 않는다는 뜻이다.
     */
    @Override
    public boolean contains(Object value)
    {
        return indexOf(value) >= 0;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * clear() 메소드는 모든 요소들을 비워버리는 작업이다.
     * 리스트에 요소를 담아두었다가 초기화가 필요할 때 유용하다.
     * 이때 객체 자체를 null 해주기 보단
     * 모든 노드를 하나하나 null 해주는 것이
     * 자바의 가비지 컬렉터가 명시적으로 해당 메모리를 안쓴다고 인지하므로
     * 메모리 관리 효율 측면에서 조금이나마 더 좋다.
     */
    @Override
    public void clear()
    {
        for(Node<E> temp = head; temp != null;)
        {
            Node<E> nextNode = temp.next;
            temp.data = null;
            temp.next = null;
            temp = nextNode;
        }
        head = tail = null;
        size = 0;
    }

    /**
     * clone() 메소드
     * 사용자가 사용하고 있던 LinkedList를 새로 하나 복제하고 싶을 때 쓰는 메소드이다.
     * 단순히 = 연산자로 객체를 복사하게 되면 '주소'를 복사하는 것이므로
     * 복사한 객체에서 데이터를 조작할 경우
     * 원본 객체에까지 영향을 미친다.
     * 즉, 얕은 복사가 된다.
     * 이러한 것을 방지하기 위해 깊은 복사를 하는데, 이 때 필요한 것이 clone()이다.
     * Object에 있는 메소드이지만 접근 제어자가 protected로 되어 있어
     * 우리가 만든 것처럼 사용자 클래스의 경우 Cloneable 인터페이스를 implement해야한다.
     */
    public Object clone() throws CloneNotSupportedException
    {
        @SuppressWarnings("uncheked")
        SLinkedList<? super E> clone = (SLinkedList<? super E>) super.clone();
        // super.clone() 자체가 생성자 비슷한 역할이고
        // shallow copy를 통해 사실상 new SLinkedList()를 호출하는 격이라
        // 완벽하게 복제하려면 clone한 리스트의 array 또한 새로 생성해서 copy 해주어야 한다.

        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for(Node<E> temp = head; temp!=null; temp = temp.next)
            clone.addLast(temp.data);

        return clone;
    }

//    /**
//     * sort() 메소드
//     * 객체 배열의 경우 Collections.sort()를 사용하게 되는데
//     * Collections.sort()도 내부에서는 Arrays.sort()를 쓴다.
//     * 원리는 해당 리스트를 Object[] 배열로 변환시켜 Arrays.sort()를 통해 정렬한 뒤,
//     * 정렬된 데이터를 다시 리스트의 노드에서 셋팅을 해주는 것이다.
//     *
//     * 만약 Wrapper 클래스 타입 (ex.Integer, String, Double ...) 이라면
//     * 따로 Comparator을 구현해주지 않아도 되지만,
//     * 사용자 클래스 같은 경우는 해당 객체에 Comparable를 구현해주거나
//     * 또는 Comparator를 구현해주어 파라미터로 넘겨주어야 한다.
//     *
//     * 즉, sort() 메소드를 만들 때, 기본적으로 두가지 경우를 생각한다.
//     * 첫 번째는 객체에 Comparable이 구현되어 있어 따로 파라미터로 Comparator를 넘겨주지 않는 경우고,
//     * 두 번째는 Comparator를 넘겨주어 정의된 정렬 방식을 사용하는 경우다.
//     */
//    public void sort()
//    {
//        /**
//         * Comparator를 넘겨주지 않는 경우 해당 객체의 Comparable에 구현된 정렬 방식 사용
//         * 만약 구현되어 있지 않다면 cannot be cast to class java.lang.Comparable 에러 발생.
//         * 만약 구현되어 있을 경우 null로 파라미터를 넘기면
//         * Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬한다.
//         */
//        sort(null);
//    }
//
//    @SuppressWarnings({"unchecked", "rawtypes"})
//    public void sort(Comparable<? super E> c)
//    {
//        Object[] a = this.toArray();
//        Arrays.sort(a, (Comparator) c);
//
//        int i = 0;
//        for (Node<E> x = head; x != null; x = x.next, i++) {
//            x.data = (E) a[i];
//    }
}
























