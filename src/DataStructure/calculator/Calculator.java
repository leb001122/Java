package calculator;

public class Calculator {
    private static final String errMsg = "이해할 수 없는 수식";
    private String postfixStr = "";

    public Calculator() {}

    public int startCalculation(Queue<Token> infix) throws Exception
    {
        Queue<Token> postfix = infixToPostfix(infix);

        int result = getResult(postfix);
        return result;
    }

    public String getPostfixStr()
    {
        return postfixStr;
    }

    public Queue infixToPostfix(Queue<Token> infix) throws Exception
    {
        Stack<Token> stack = new Stack<>();
        Queue<Token> postfix = new Queue<>();
        Token preToken = null;
        Token curToken = null;
        boolean isVisited = false;

        try {
            while(!infix.isEmpty())
            {
                curToken = nextToken(infix);

                if(!curToken.isMyOperator())// 만약 현재 토큰이 연산자가 아니라 숫자이면
                {
                    if(preToken != null && !preToken.isMyOperator())
                        throw new Exception(errMsg);

                    postfix.enqueue(curToken); // 큐에 저장
                    postfixStr += curToken.getOperand()+" ";
                    preToken = curToken;
                }

                else if(curToken.getOperator().equals(")")) // 닫는 괄호를 만나면
                {
                    for(; !stack.peek().getOperator().equals("("); ) //여는 괄호를 만날때까지 스택에서 팝
                    {
                        postfix.enqueue(stack.peek());  // 큐에 저장
                        postfixStr += stack.pop().getOperator()+" ";
                    }
                    stack.pop();   // 여는 괄호 빼내기
                }

                else
                {
                    isVisited = true;
                    if(curToken.getOperator().equals("-"))
                    {
                        if(preToken == null || preToken.isMyOperator())
                            curToken = new Token("m");
                    }

                    if(!stack.isEmpty())
                    {
                        for(; !stack.isEmpty() && isp(stack.peek()) <= icp(curToken); )
                        {
                            if(isUnaryOperator(stack.peek()) && isUnaryOperator(curToken))
                                break;
                            else
                            {
                                postfix.enqueue(stack.peek());
                                postfixStr += stack.pop().getOperator()+" ";
                            }
                        }
                    }
                    preToken = curToken;
                    stack.push(curToken);
                }
            }

            if(!isVisited)
                throw new Exception(errMsg);

            while(!stack.isEmpty())
            {
                postfix.enqueue(stack.peek());
                postfixStr += stack.pop().getOperator()+" ";
            }


        } catch (Exception e) {
            throw new Exception(errMsg);
        }
        return postfix;
    }

    public int getResult(Queue<Token> postfix) throws Exception
    {
        Stack<Token> stack = new Stack<>();
        Token curToken = null;
        try {
            while(!postfix.isEmpty())
            {
                curToken = nextToken(postfix);
                if(curToken.isMyOperator())
                {
                    int resultVal;
                    if(isUnaryOperator(curToken))
                        resultVal = calculateUnary(stack.pop().getOperand(), curToken);
                    else
                    {
                        int second = stack.pop().getOperand();
                        int first = stack.pop().getOperand();
                        resultVal = calculateBinary(first, second, curToken);
                    }
                    stack.push(new Token(resultVal));
                }
                else // 피연산자일때
                {
                    stack.push(curToken);
                }
            }
            int result = stack.pop().getOperand();
            if(stack.isEmpty())
                return result;

        }catch (Exception e) {
            throw new Exception(errMsg);
        }
        throw new Exception(errMsg);
    }

    public Token nextToken(Queue<Token> infix)
    {
        if(infix.isEmpty())
            return null;
        return (Token) infix.dequeue();
    }

    public int isp(Token operator) // in Stack Priority
    {
        switch (operator.getOperator())
        {
            case "(" :                                    return 8;
            case "m" : case "~" :                         return 1;
            case "*" : case "/" : case "%" :              return 2;
            case "+" : case "-" :                         return 3;
            case ">>" : case "<<" :                       return 4;
            case "&" :                                    return 5;
            case "^" :                                    return 6;
            case "|" :                                    return 7;
            default :                                     return -1;
        }
    }

    public int icp(Token operator) // in Coming Priority
    {
        switch (operator.getOperator())
        {
            case "(" :                                    return 0;
            case "m" : case "~" :                         return 1;
            case "*" : case "/" : case "%" :              return 2;
            case "+" : case "-" :                         return 3;
            case ">>" : case "<<" :                       return 4;
            case "&" :                                    return 5;
            case "^" :                                    return 6;
            case "|" :                                    return 7;
            default :                                     return -1;
        }
    }

    public int calculateUnary(int num, Token token) throws Exception
    {
        String operator = token.getOperator();
        if (operator.equals("m"))
            return -num;
        else if (operator.equals("~"))
            return ~(int)num;
        throw new Exception(errMsg);
    }

    public int calculateBinary(int n1, int n2, Token token) throws Exception
    {
        String operator = token.getOperator();
        if(operator.equals("*"))
            return n1 * n2;
        else if(operator.equals("/"))
            return n1 / n2;
        else if(operator.equals("%"))
            return n1 % n2;
        else if(operator.equals("+"))
            return n1 + n2;
        else if(operator.equals("-"))
            return n1 - n2;
        else if(operator.equals("&"))
            return (int)n1 & (int)n2;
        else if(operator.equals("^"))
            return (int)n1 ^ (int)n2;
        else if(operator.equals("|"))
            return (int)n1 | (int)n2;
        else if(operator.equals(">>"))
            return (int)n1 >> (int)n2;
        else if(operator.equals("<<"))
            return (int)n1 << (int)n2;
        throw new Exception(errMsg);
    }

    public boolean isUnaryOperator(Token token)
    {
        String operator = token.getOperator();
        if(operator.equals("m") || operator.equals("~"))
            return true;
        return false;
    }
}
