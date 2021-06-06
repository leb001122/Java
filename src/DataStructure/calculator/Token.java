package calculator;

public class Token
{
    private String operator; // 연산자
    private int operand; // 피연산자
    private final static String errMsg = "연산자를 찾을 수 없음";

    public Token(String oper) throws Exception
    {
        operator = null;

        if(isOperator(oper))
            operator = oper;
        else
        {
            try {
                operand = Integer.parseInt(oper);
            }catch(Exception e) {
                throw new Exception(errMsg);
            }
        }
    }

    public Token(int operand)
    {
        operator = null;
        this.operand = operand;
    }

    public String getOperator() { return operator; }
    public int getOperand() { return operand; }

    public boolean isMyOperator(){
        if (operator == null)
            return false;
        return true;
    }

    static public boolean isOperator(String oper)
    {
        switch (oper)
        {
            case "(" : case ")" :
            case "m" : case "~" :
            case "*" : case "/" : case "%" :
            case "+" : case "-" :
            case ">>" : case "<<" :
            case "&" :
            case "^" :
            case "|" : return true;
            default : return false;
        }
    }
}
