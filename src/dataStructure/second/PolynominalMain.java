package dataStructure.second;

import java.util.Scanner;

public class PolynominalMain
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("첫 번째 다항식을 계수-지수 쌍으로 입력하세요.(지수가 0으로 입력되면 종료)");
        Polynominal poly1 = new Polynominal();
        inputTerm(poly1, sc);
        System.out.println("다항식1 : "+poly1);

        System.out.println("두 번째 다항식을 계수-지수 쌍으로 입력하세요.(지수가 0으로 입력되면 종료)");
        Polynominal poly2 = new Polynominal();
        inputTerm(poly2, sc);//
        System.out.println("다항식2 : "+poly2);

        Polynominal res;
        res = Polynominal.multiply(poly1, poly2);
        // res.multiply(poly1, poly2);
        // res = poly1.multiply(ploy2);
        System.out.println("곱한 다항식 : "+res);

        sc.close();
    }

    public static void inputTerm(Polynominal poly, Scanner sc)
    {
        double coef;
        int exp;

        do
        {
            coef = sc.nextDouble(); exp = sc.nextInt();
            if(coef == 0 || exp < 0) continue; // 계수가 0이거나 지수가 음수인 경우 항 추가하지 않음.
            poly.add(new Term(coef, exp));

        }while(exp != 0);

        // sc.close();
        // 닫아 주니까 오류 났음.. 왜??
    }
}
