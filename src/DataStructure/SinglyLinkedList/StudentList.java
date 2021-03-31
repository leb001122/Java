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

        Node<Student> now = head, prevNode = null;

        for(String id; now!=null; prevNode=now, now=now.getNext())
        {
            id = now.getData().getId();
            int difference = id.compareTo(newData.getId());

            // 중복된 학번인 경우
            if(difference == 0)
                throw new DuplicatedStudentIdException();
            if(difference > 0)
            {
                if(prevNode == null) // 새로 들어오는 학번이 가장 작은 경우
                    addFirst(newData);
                else // 중간에 끼워 넣을 경우
                {
                    prevNode.setNext(new Node<Student>(newData, now));
                    size++;
                }
                return;
            }
            // difference < 0인 경우 다음 노드 확인인
        }
        // 새로 들오는 학번이 가장 큰 경우
        prevNode.setNext(new Node<Student>(newData));
        size++;
    }

    // 학생의 학번으로 학생 찾기
    public Node<Student> search(String id)
    {
        for(Node<Student> temp = head; temp!=null; temp=temp.getNext())
        {
            if(temp.getData().getId().equals(id))
                return temp;
        }
        System.out.println("일치하는 학번이 존재하지 않습니다.");
        return null;
    }

    public String toString()
    {
        if(isEmpty())
            return null;

        String str = null;
        Node<Student> studentNode = head;

        for(int i=0; i<size; i++, studentNode=studentNode.getNext())
        {
            str += studentNode+"\n";
        }
        return str;
    }
}
/**
 * throws 사용
 * 명시적 에러 처리 - 실행을 하기 전 컴파일 단계에서 try-catch를
 * 선언하지 않으면 컴파일이 되지 않게 에러를 발생시킴
 */

/**
 * throws 사용 x
 * Exception을 상속 받아서 에러 클래스를 만들면 전부 명시적 에러처리 해야함.
 * RuntimeException을 상속 받으면 전부 묵시적 에러처리가 됨
 * 에러 클래스 계층 : Throwable - Exception - RuntimeException
 * 묵시적 에러 처리 - 에러를 발생시키지만 컴파일 하는데 전혀 문제가 없다.
 * 난 잘 동작하는데 가끔씩 에러가 발생해~
 */

class DuplicatedStudentIdException extends RuntimeException
{
    DuplicatedStudentIdException()
    {
        System.out.println("중복된 학번이 존재합니다.");
    }
}