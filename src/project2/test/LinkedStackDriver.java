package project2.test;

import project2.stacks.*;

/**
 * Test the expression of Task 1 with the algorithim implemented in Task 2
 * 
 * @author wwwyv
 *
 */
public class LinkedStackDriver {

    public static void main(String[] args) {
        // creating the LinkedStack with the infix expression
        String infixExpression = "a*b/c-a)+d*e";
        char[] infixArr = infixExpression.toCharArray();
        LinkedStack<Character> LS = new LinkedStack<Character>();
        for (char ch : infixArr) {
            LS.push(ch);
        }
        String postFixExpression = Calculator.convertToPostFix(LS);

        System.out.println("Infix Expression: " + infixExpression);
        System.out.println("Postfix Expression: " + postFixExpression);
    }
}
