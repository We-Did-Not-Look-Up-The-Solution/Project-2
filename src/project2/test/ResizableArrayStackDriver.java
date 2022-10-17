package project2.test;

import project2.stacks.*;

/**
 * Test the expression of Task 1, with the variables of task 4, using the
 * algorithim in Task 5
 * 
 * @author wwwyv
 *
 */
public class ResizableArrayStackDriver {
    public static void main(String[] args) {
        // creating the ResizableArrayStack with the postfix expression
        String postFixExpression = "ed*acb/a*-";
        char[] postFixArr = postFixExpression.toCharArray();
        ResizableArrayStack<Character> RAS = new ResizableArrayStack<Character>();
        for (char ch : postFixArr) {
            RAS.push(ch);
        }

        System.out.println("Postfix Expression: " + postFixExpression);
        System.out.println("Postfix Evaluation: " + Calculator.evaluatePostfix(RAS));
    }

}
