package DataStructure.Polynominal;

public class Term {
    private double coef; //계수
    private int exp; //지수

    public Term(double coef, int exp)
    {
        this.coef = coef;
        this.exp = exp;
    }

    public double getCoef()  { return coef; }

    public int getExp() { return exp; }

    public int compareExp(Term term)
    {
        if(this.exp < term.exp)
            return 1;
        else if(this.exp == term.exp)
            return 0;
        else
            return -1;
    }

    public String toString()
    {
        double coef = this.coef;
        double fractionalParts = coef - (int)coef; // 소수부
        if(coef < 0)
            coef = -1*coef; // 예쁜 출력을 위한 코드 (연산자와 수를 띄어서 출력하기 위해 -> Polynominal 클래스의 toString)

        if(exp>1)
        {
            if(coef==1)
                return "x^"+exp;
            if(fractionalParts == 0)
                return (int)coef+"x^"+exp; // 계수가 1이 아니면서 소수부가 0인 경우
            return coef+"x^"+exp; // 계수가 1이 아니면서 소수부가 0이 아닌 경우
        }

        else if(exp==1)
        {
            if(coef==1)
                return "x";
            if(fractionalParts == 0)
                return (int)coef+"x";
            return coef+"x";
        }

        else if(exp==0)
        {
            if (fractionalParts == 0)
                return (int) coef + "";
            return coef + "";
        }

        return "";
    }
}


