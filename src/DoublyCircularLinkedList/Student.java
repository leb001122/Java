package DataStructure.DoublyCircularLinkedList;

class Student {
    private int id;
    private String name;

    Student(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    void setId(int id) {this.id = id;}
    void setName(String name) {this.name = name;}
    int getId() {return id; }
    String getName() {return name;}

    public String toString()
    {
        return id+" "+name;
    }
}

