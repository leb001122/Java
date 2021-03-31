package DataStructure.SinglyLinkedList;

public class Student
{
    private String name;
    private String id;
    private ClubList club;

    public Student(String name, String id)
    {
        this.name = name;
        this.id = id;
        this.club = null;
    }

    public Student(String name, String id, ClubList club)
    {
        this.name = name;
        this.id = id;
        this.club = club;
    }

    public void setName(String name) { this.name = name; }
    public void setId(String id) { this.id = id; }
    public void setClub(ClubList club) { this.club = club; }
    public String getName() { return name; }
    public String getId() { return id; }
    public ClubList getClub() { return club; }

    public String toString()
    {
        return name+" "+id+" "+club;
    }

}
