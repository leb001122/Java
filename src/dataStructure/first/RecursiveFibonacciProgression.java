package dataStructure.first;

import java.util.Scanner;

public class RecursiveFibonacciProgression {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("몇 번째 피보나치 수? ");
        int n = sc.nextInt();

        System.out.print(n +"번째 피보나치 수 : "+fibonacci(n));
        sc.close();
    }

    public static int fibonacci(int n){
        if(n==0)
            return 0;
        else if(n<=2)
            return 1;
        else
            return fibonacci(n-1) + fibonacci(n-2);
    }
}
