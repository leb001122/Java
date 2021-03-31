package DataStructure.SinglyLinkedList;

public class ClubList extends SingleList<String>
{
    public String toString()
    {
        if(size == 0)
            return null;

        String str = null;
        Node<String> club = head;
        for(int i=0; i<size; i++, club=club.getNext())
        {
            if(i==size-1)
                str += club;
            else
                str += club+"/";
        }
        return str;
    }
}
