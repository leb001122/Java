package DataStructure.Polynominal;

public class Polynominal
{
    private static final int INIT_SIZE = 3;
    private Term[] termArray;
    private int terms;

    public Polynominal() {
        this(INIT_SIZE);
    }

    public Polynominal(int size)
    {
        termArray = new Term[size];
        terms = 0;
    }

    public int getTerms()
    {
        return terms;
    }

    private void resize(int newSize)
    {
        Term [] tmp = new Term[newSize];
        for(int i=0; i<terms; i++)
            tmp[i] = termArray[i];
        termArray = tmp;
    }

    public void add(Term term)
    {
        if(terms == termArray.length)
            resize(terms*2);

        int count = 0;
        for(int i=0; i<terms; i++)
        {
            count++;
            int ret = termArray[i].compareExp(term);

            // 새로 들어오는 항의 지수가
            if(ret == 1) // 크면 새로 들어오는 항의 지수보다 작은 지수를 가진 항들은 배열에서 뒤로 밀려남.
            {
                insert(term, i);
                return;
            }
            else if(ret == 0) // 같으면 계수 더해줌
             {
                 double newCoef = termArray[i].getCoef() + term.getCoef();
                 if(newCoef != 0)
                     termArray[i] = new Term(newCoef, term.getExp());
                 else // 더한 결과가 0인 경우
                     delete(i);
                 return;
             }
             else if(ret == -1) // 작으면 배열의 다음 항 확인
                continue;
        }
        // termArray의 마지막 항까지 다 본 경우. 즉, 새로 들어오려는 항의 지수가 가장 작은 경우
        if(terms == count)
        {
            termArray[terms] = term;
            terms++;
        }
    }

    private void insert(Term t, int index)
    {
        if(terms == termArray.length)
            resize(terms*2);

        for(int i=terms-1; i>=index; i--)
            termArray[i+1] = termArray[i];
        termArray[index] = t;
        terms++;
    }

    private void delete(int index)
    {
        for(int i=index; i<terms-1; i++)
            termArray[i] = termArray[i+1];
        termArray[terms-1] = null;
        terms--;

        if(terms > 0 && terms == termArray.length / 4)
            resize(termArray.length / 2);
    }

    public static Polynominal multiply(Polynominal poly1, Polynominal poly2)
    {
        double coef;
        int exp;

        Polynominal res = new Polynominal();
        Term [] array1 = poly1.termArray;
        Term [] array2 = poly2.termArray;

        for(int i=0; i<poly1.terms; i++)
        {
            for(int j=0; j<poly2.terms; j++)
            {
                coef = array1[i].getCoef() * array2[j].getCoef();
                exp = array1[i].getExp() + array2[j].getExp();
                res.add(new Term(coef, exp));
            }
        }
        return res;
    }

    public String toString()
    {
        if(terms == 0)
            return "0";

        String res = "";

        for(int i=0; i<terms; i++)
        {
            if(termArray[i].getCoef() > 0)
            {
                if(i!=0)
                    res += " + " + termArray[i];
                else
                    res += termArray[i];
                continue;
            }
            // 계수가 음수인 경우
            res += " - " + termArray[i];
        }
        return res;
    }
}
