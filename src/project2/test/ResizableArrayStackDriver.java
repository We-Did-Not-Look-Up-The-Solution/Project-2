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
	
	/**
	 * Test a ResizableArrayStack
	 * @param args arguments
	 */
    public static void main(String[] args) {
        // creating the ResizableArrayStack with the postfix expression
        String postFixExpression = "ab*ca-/de*+";
        char[] postFixArr = postFixExpression.toCharArray();
        ResizableArrayStack<Character> RAS = new ResizableArrayStack<Character>();
        for (int index = postFixArr.length - 1; index > -1; index--)
        	RAS.push(postFixArr[index]);

        System.out.println("Postfix Expression: " + postFixExpression);
        System.out.println("Postfix Evaluation: " + Calculator.evaluatePostfix(RAS));
    }
}
