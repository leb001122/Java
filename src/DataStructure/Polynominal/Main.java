package DataStructure.Polynominal;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        Polynominal poly1 = new Polynominal();
        Polynominal poly2 = new Polynominal();

        input(sc, poly1, 1);
        System.out.println("다항식1 : "+poly1);
        input(sc, poly2, 2);
        System.out.println("다항식2 : "+poly2);

        Polynominal poly3;
        poly3 = poly1.multiplyPoly(poly2);
        System.out.println("곱한 다항식 : "+poly3);

        Polynominal poly4;
        poly4 = poly1.addPoly(poly2);
        System.out.println("더한 다항식 : "+poly4);

        sc.close();
    }

    public static void input(Scanner sc, Polynominal poly, int num)
    {
        do
        {
            System.out.println(num+"번째 다항식을 계수-지수 쌍으로 입력하세요.(지수가 0으로 입력되면 종료)");
            inputTerm(sc, poly);
        } while(poly.getTerms()==0);  // 0만 입력되었을 경우 다시 다항식을 받도록 함.

    }

    public static void inputTerm(Scanner sc, Polynominal poly)
    {
        double coef;
        int exp;

        do
        {
            coef = sc.nextDouble(); exp = sc.nextInt();
            if(coef == 0 || exp < 0) continue; // 계수가 0이거나 지수가 음수인 경우 항 추가하지 않음.
            poly.addTerm(new Term(coef, exp));

        }while(exp != 0);
    }
}
