package DataStructure.SinglyLinkedList;

public class Student
{
    private String name;
    private String id;
    private ClubList club;

    public Student(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Student(String id, String name, ClubList club)
    {
        this.id = id;
        this.name = name;
        this.club = club;
    }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setClub(ClubList club) { this.club = club; }
    public String getId() { return id; }
    public String getName() { return name; }
    public ClubList getClub() { return club; }

    public String toString()
    {
        if(club==null)
            return id+" "+name;
        else
            return id+" "+name+" "+club;
    }

}
