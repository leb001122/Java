package DataStructure.Algorithm;

import java.util.Scanner;

public class LoopFibonacciProgression {
    public static void main(String [] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("몇 번째 피보나치 수? ");
        int n = sc.nextInt();

        if(n == 0)
            System.out.print("0");
        else if(n == 1)
            System.out.print("1");
        else
        {
            int first = 0;
            int second = 1;
            int sum = 0;

            for(int i=0; i<n-1; i++)
            {
                sum = first+second;
                first = second;
                second = sum;
            }
            System.out.print(n +"번째 피보나치 수 : "+sum);
        }
        sc.close();
    }
}
