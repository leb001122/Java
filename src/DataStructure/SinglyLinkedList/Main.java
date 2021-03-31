package DataStructure.SinglyLinkedList;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentList stuList = new StudentList();
        displayMenu();
        int menu;
        do {
            System.out.print("원하는 기능을 선택하세요. : ");
            menu = sc.nextInt();
            handleMenu(args, menu, sc, stuList);
        } while(menu != 4);
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

    public static void handleMenu(String[] args, int menu, Scanner sc, StudentList sList)
    {
        if(menu == 1)
        {
            sc.nextLine();
            System.out.print("학번, 이름, 동아리들을 입력하세요. : ");
            String input = sc.nextLine(); // 한 줄 읽기
            generateStudent(sc, sList, input);
        }
        else if(menu == 2)
        {
            System.out.print("학번을 입력하세요. :");
            String id = sc.next();
            System.out.println(sList.search(id));
        }
        else if(menu == 3)
        {
           retrieveAll(args, sList, sc);
        }
        else if(menu == 4)
        {
            saveFile(args, sList);
        }
        else
            System.out.println("올바른 메뉴 번호를 입력해주세요.");
    }

    public static void saveFile(String []args, StudentList sList)
    {
        try
        {
            File file = new File(args[0]);
            if(!file.exists())
                file.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(String.valueOf(sList));
            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void retrieveAll(String []args, StudentList sList, Scanner sc)
    {
        try
        {
            File file = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while((line = br.readLine()) != null)
            {
                generateStudent(sc, sList, line);
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void generateStudent(Scanner sc, StudentList sList, String input)
    {
        try {
            input = input.trim();  // 앞뒤 공백 제거

            String [] array;
            array = input.split(" "); // 공백을 기준으로 배열에 원소 저장

            Integer.parseInt(array[0]); // 0번째(학번)가 숫자 형태의 문자열이 아닐 경우 NumberFormatException 발생

            Student student = new Student(array[0], array[1]);
            sList.add(student); // 만약 중복된 학생이 있다면 DuplicatedStudentException 발생할 것임.

            if(array.length == 3) // 동아리가 입력된 경우
                student.setClub(generateClubList(array[2]));

        } catch (NumberFormatException e) {
            System.out.println("Warning : 잘못된 학번");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException");
        }
    }

    public static ClubList generateClubList(String clubs)
    {
        ClubList clubList = new ClubList();
        String [] array;
        array = clubs.split("/");

        for(int i=0; i<array.length; i++)
             clubList.addFirst(array[i]);
        return clubList;
    }
}

