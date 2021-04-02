package DataStructure.SinglyLinkedList;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentList sList = new StudentList();

        try {
            File file = new File(args[0]);
            if (!file.exists())
                file.createNewFile();

            readFile(file, sList);
            displayMenu();

            while(true)
            {
                System.out.print("원하는 기능을 선택하세요. : ");
                int menu = sc.nextInt();
                sc.nextLine();
                handleMenu(file, menu, sc, sList);
                if(menu==4)
                    return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }

    public static void displayMenu()
    {

        System.out.println("==========================");
        System.out.println("(1) 새 학생 입력");
        System.out.println("(2) 학생 삭제");
        System.out.println("(3) 학번 순으로 전체 출력");
        System.out.println("(4) 파일에 저장하고 종료");
        System.out.println("==========================");
    }

    public static void handleMenu(File file, int menu, Scanner sc, StudentList sList)
    {
        switch (menu)
        {
            case 1:
                System.out.print("학번, 이름, 동아리들을 입력하세요. : ");
                generateStudent(sList, sc.nextLine());
                break;
            case 2:
                removeStudent(sList, sc);
                break;
            case 3:
                retrieveAll(sList);
                break;
            case 4:
                saveFile(file, sList);
                break;
            default:
                System.out.println("올바른 메뉴 번호를 입력해주세요.");
        }
    }

    public static void saveFile(File file, StudentList sList)
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(String.valueOf(sList));  // why??????????????????????
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(File file, StudentList sList)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while((line = br.readLine()) != null)
                generateStudent(sList, line);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void retrieveAll(StudentList sList)
    {
        if(sList.isEmpty())
            System.out.println("학생 리스트가 비어있습니다.");
        else
            System.out.println(sList);
    }

    public static void generateStudent(StudentList sList, String input)
    {
        try {
            String[] array;
            array = input.split(" ");

            if(array.length<2) // 학번만 입력된 경우
                throw new InputMismatchException();

            Student student = new Student(array[0], array[1]);
            sList.add(student);

            if(array.length>2) //20200881 이은빈    (공백들)   -> 이렇게 받아졌을땐..? 디버깅 해봤는데 문제없음.
                generateClubList(array, student);

        } catch (DuplicatedStudentIdException e) {
            System.out.println("중복된 학번의 학생이 존재합니다.");
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다.");
        }
    }

    public static void generateClubList(String [] array, Student student)
    {
        String str = "";
        for(int i=2; i<array.length; str+=array[i]+" ", i++);

        student.setClub(new ClubList(str));
    }

    public static void removeStudent(StudentList sList, Scanner sc)
    {
        try {
            System.out.print("학번을 입력하세요. : ");
            sList.remove(sc.nextLine());
        }catch (NoSuchStudentIdException e){
            System.out.println("해당 학번의 학생이 존재하지 않습니다.");
        }

    }
}
