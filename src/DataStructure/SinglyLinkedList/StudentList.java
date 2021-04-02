package DataStructure.SinglyLinkedList;

public class StudentList extends SingleList<Student>
{
    public void add(Student newData)
    {
        if(isEmpty())
        {
            addFirst(newData);
            return;
        }

        Node<Student> now = head;
        Node<Student> prevNode = null;

        for(String id; now!=null; prevNode=now, now=now.getNext())
        {
            id = now.getData().getId();
            int difference = id.compareTo(newData.getId());

            if(difference == 0)  // 중복된 학번인 경우
                throw new DuplicatedStudentIdException();
            if(difference > 0)
            {
                if(prevNode == null) // 새로 들어오는 학번이 가장 작은 경우
                    addFirst(newData);
                else // 중간에 끼워 넣을 경우
                {
                    prevNode.setNext(new Node<>(newData, now));
                    size++;
                }
                return;
            }
            // difference < 0인 경우 다음 노드 확인인
        }
        // 새로 들어오는 학번이 가장 큰 경우
        prevNode.setNext(new Node<>(newData));
        size++;
    }

    private Node<Student> searchPrev(String id)
    {

        for(Node<Student> now = head, prev = null;
            now != null;
            prev = now, now = now.getNext())
        {
            if(now.getData().getId().equals(id))
            {
                return prev;   // 찾으려는 노드의 이전 노드 반환
            }
        }
        throw new NoSuchStudentIdException();
    }

    public void remove(String id)
    {
        Node<Student> prevNode = searchPrev(id);

        if(prevNode==null)
        {
            removeFirst();
            return;
        }

        Node<Student> now = prevNode.getNext();
        prevNode.setNext(now.getNext());
        now.setData(null);
        now.setNext(null);
        size--;
    }

    public String toString()
    {
        if(isEmpty())
            return "";

        String str = "";
        Node<Student> stNode = head;

        for(int i=0; i<size-1; i++, stNode=stNode.getNext())
            str += stNode + "\n";

        str += stNode;
        return str;
    }
}

class DuplicatedStudentIdException extends RuntimeException {
}

class NoSuchStudentIdException extends  RuntimeException {
}