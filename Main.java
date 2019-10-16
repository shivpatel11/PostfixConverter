public class Main
{
    public static void main(String[] args)
    {
        Postfix one = new Postfix();
        String postfix = one.convertToPostfix("a + b / c");
        System.out.println(postfix);
        System.out.println(one.evaluatePostfix(postfix));

        Postfix two = new Postfix();
        String postfix1 = two.convertToPostfix("a + b * c");
        System.out.println(postfix1);
        System.out.println(two.evaluatePostfix(postfix1));

        Postfix three = new Postfix();
        String postfix2 = three.convertToPostfix("a/b*(c+(d-e))");
        System.out.println(postfix2);
        System.out.println(three.evaluatePostfix(postfix2));

        Postfix four = new Postfix();
        String postfix3 = four.convertToPostfix("(a+e)/(b-d)");
        System.out.println(postfix3);
        System.out.println(four.evaluatePostfix(postfix3));

        Postfix five = new Postfix();
        String postfix4 = five.convertToPostfix("a/(b-c)*d");
        System.out.println(postfix4);
        System.out.println(five.evaluatePostfix(postfix4));
    }
}
