package calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            try {
                Queue<Token> infixQueue = new Queue<Token>();

                System.out.print("식을 입력하세요.(종료는 quit) : ");
                String str = sc.nextLine();
                if(str.equals("quit"))
                    break;

                str = str.trim();
                for (String oper : str.split(" "))
                    infixQueue.enqueue(new Token(oper));

                Calculator calculator = new Calculator();

                int result = calculator.startCalculation(infixQueue);
                System.out.println("----postfix notaion은 : "+ calculator.getPostfixStr());
                System.out.println("-----결과는 "+result+"\n");
            } catch (Exception e) {
                System.out.println(e.getMessage()+"\n");
            }
        }
        System.out.println("****종료합니다****");
    }
}
