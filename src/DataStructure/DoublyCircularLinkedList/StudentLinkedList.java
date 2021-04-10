package DataStructure.DoublyCircularLinkedList;

public class StudentLinkedList extends DoublyCircularLinkedList<Student>{

    private Node<Student> searchPrev(int id)
    {
        Node<Student> prev = head;

        for(Node<Student> now=head.getNext();
            now.getData()!=null; // prev가 dummyHead를 마주칠 때까지
            prev=now, now=now.getNext())
        {
            int nowId = now.getData().getId();

            if(id==nowId) // 같은 학번이 존재하는 경우
                return null;
            if(id<nowId)
                return prev;
        }
        return prev; // 이때 반환되는 노드는 dummyHead의 prev가 가리키는 노드
    }

    private Node<Student> search(int id)
    {
        for(Node<Student> now=head.getNext();
            now.getData()!=null;
            now=now.getNext())
        {
            if(id==now.getData().getId())
                return now;
        }
        return null; // 해당 id를 가진 노드가 없을 경우 null 반환
    }

    public boolean add(Student st)
    {
        if(isEmpty())
            addNext(st, head);
        else
        {
            Node<Student> prevNode = searchPrev(st.getId());

            if(prevNode == null) // 이미 존재하는 학생인 경우
                return false;
            else
                addNext(st, prevNode);
        }
        return true;
    }

    public boolean remove(int id)
    {
        if(isEmpty())
            return false;

        Node <Student> removeNode = search(id);

        if(removeNode==null)
            return false;

        remove(removeNode);
        return true;
    }

    public String retriveReverseAll() // 학번 역순으로 전체 출력
    {
        String str = "";
        Node<Student> node = head.getPrev();
        for(; node.getData()!=null; node=node.getPrev())
            str += node.getData()+"\n";

        return str;
    }

    public String retrieveAllFromCertainId(int id) // 특정 학번부터 전체 출력
    {
        Node<Student> node = search(id);
        if(node == null)
            return "해당 학번의 학생이 없습니다.";

        String str = "";
        int cnt = 0 ;
        for(; node.getData()!=null; node=node.getNext()) { // 특정 학번부터 더미헤드 전까지
            str += node.getData()+"\n";
            cnt++;
        }

        node = head.getNext();
        for(int i=0; i<size-cnt; i++, node=node.getNext()) // 처음 노드(더미헤드X)부터 특정 학번 전까지
            str += node.getData()+"\n";

        return str;
    }

    public String toString()
    {
        String str = "";
        Node<Student> node = head.getNext();

        for(; node.getData()!=null; node=node.getNext())
            str += node.getData()+"\n";

        return str;
    }
}
