public class Postfix
{
    public static String convertToPostfix(String infix)
    {
        StackInterface<Character> operatorStack = new LinkedStack<>();

        // use a StringBuilder object rather than a String, since appending is much more efficient.
        // To add to the StringBuilder object 'postfix':
        // postfix.append(stringToAppend);
        StringBuilder postfix = new StringBuilder();
        int length = infix.length();
        //start the loop to go through the string
        for(int i = 0; i < length; i++)
        {
            //get next character in the string
            char nextCharacter = infix.charAt(i);
            //if it is a letter add it to the postfix
            if(isVariable(nextCharacter))
            {
                postfix.append(nextCharacter);
            }
            else
            {
                switch(nextCharacter)
                {
                    case '^'://add exponent to stack
                        operatorStack.push(nextCharacter);
                        break;
                    case '+': case '-': case'*': case '/'://if the top of stack is higher precedence pop it to postfix
                        while (!operatorStack.isEmpty() && getPrecedence(nextCharacter)<=getPrecedence(operatorStack.peek()))
                        {
                            postfix.append(operatorStack.peek());
                            operatorStack.pop();
                        }//then push the character onto stack
                        operatorStack.push(nextCharacter);
                        break;
                    case '('://push beginning parenthesis onto stack
                        operatorStack.push(nextCharacter);
                        break;
                    case ')'://keep popping the stack until the pair parenthesis comes
                        char topOperator = operatorStack.pop();
                        while(topOperator != '(')
                        {
                            postfix.append(topOperator);
                            topOperator = operatorStack.pop();
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        //add the rest of stack to the postfix when the loop finishes going through the string
        while (!operatorStack.isEmpty())
        {
            postfix.append(operatorStack.pop());
        }
        return postfix.toString();
    }

    public static int evaluatePostfix(String postfix)
    {
        StackInterface<Integer> valueStack = new LinkedStack<>();
        int length = postfix.length();
        //go through the string
        for (int i = 0;i < length;i++)
        {
            char nextCharacter = postfix.charAt(i);
            //if character is a letter push the number value to stack
            if(isVariable(nextCharacter))
            {
                valueStack.push(getValue(nextCharacter));
            } else {//if a operation comes then pop two values and perform operation
                switch (nextCharacter) {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                    case '^':
                        int operandTwo = valueStack.pop();
                        int operandOne = valueStack.pop();
                        int result = performOperation(operandOne, operandTwo, nextCharacter);
                        //push the result onto the stack
                        valueStack.push(result);
                        break;
                    default: break;
                }
            }
        }
        //when the final result is in the stack peek it and return it
        return valueStack.peek();

    }

    private static int getValue(Character c)
    {
        switch(c)
        {
            case 'a':
                return 2;
            case 'b':
                return 3;
            case 'c':
                return 4;
            case 'd':
                return 5;
            case 'e':
                return 6;
            default:
                return 0;
        }
    }


    private static int performOperation(int operandOne, int operandTwo, char operator)
    {
        //returns the value of the operation
        switch (operator)
        {
            case '+':
                return operandOne + operandTwo;
            case '-':
                return operandOne - operandTwo;
            case '*':
                return operandOne * operandTwo;
            case '/':
                return operandOne / operandTwo;
            case '^':
                return (int)Math.pow(operandOne,operandTwo);
        }
        return 0;
    }

    private static int getPrecedence(char operator)
    {
        switch (operator)
        {
            case '(': case ')': return 0;
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^':           return 3;
        }
        return -1;
    }

    private static boolean isOperator(char c)
    {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
    }

    private static boolean isVariable(Character c)
    {
        return Character.isLetter(c);
    }
}