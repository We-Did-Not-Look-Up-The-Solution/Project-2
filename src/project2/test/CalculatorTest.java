package project2.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import project2.stacks.Calculator;
import project2.stacks.LinkedStack;
import project2.stacks.ResizableArrayStack;

/**
 * Tests the algorithims in {@link Calculator}
 * @author wwwyv
 *
 */
public class CalculatorTest {

	/**
	 * Test Calculator with an infix expression given by user, then asking if user wants to define variables before evaluating, then evaluate
	 * @param args arguments
	 */
	public static void main(String[] args) {
		LinkedStack<Character> inFixStack = new LinkedStack<Character>();
		ResizableArrayStack<Character> postFixStack = new ResizableArrayStack<Character>();
		Scanner scnr = new Scanner(System.in);
		String infixExpression = "a+b*c"; // hardcode
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		char alphaArray[] = alphabet.toCharArray();
		String showVarValues = "";
		boolean provideValues = false;
		
		// Gather input
		System.out.println("Please type in your infix expression (Can have spaces): ");
		if (scnr.hasNext()) infixExpression = scnr.next();
		System.err.println("Variable values are hardcoded, please make sure they are correct (otherwise, they are 0)");
		
		String postfixExpression = "";
		char[] infixExpressArray = infixExpression.toCharArray();
		
		for (int index = infixExpressArray.length - 1; index > -1; index--) {
			inFixStack.push(infixExpressArray[index]);
		}
		
		postfixExpression = Calculator.convertToPostFix(inFixStack);
		System.out.println("The postFix form infix: " + postfixExpression);
		
		char[] postfixExpressArray = postfixExpression.toCharArray();
		for (int index = postfixExpressArray.length - 1; index > -1; index--) {
			postFixStack.push(postfixExpressArray[index]);
		}
		// show current var values
		System.out.println("These are the variable values that will be used for evaluation:");
		for (int i = 0; i + 1 < alphaArray.length - 1; i += 2) {
			System.out.println(alphaArray[i] + " = " + Calculator.getValueOf(alphaArray[i]) + "	" + alphaArray[i + 1] + " = " + Calculator.getValueOf(alphaArray[i + 1]));
		}
		
		System.out.println("Do you want to specify variable values? Y/N: ");
		if (scnr.hasNext()) showVarValues = scnr.next();
		provideValues = showVarValues.contains("Y") || showVarValues.contains("y") ? true : false;
		
		if (provideValues) {
			String userInputForMap = "";
			boolean isUserDone = false;
			Map<Character, Integer> userValues = new HashMap<Character, Integer>(26);
			System.out.println("Type \"Done\" when you are prompted for a letter to indicate you are done.");
			while (!isUserDone) {
				int varValue = 0;
				char varKey = '?';
				System.out.println("Please type the variable identifier (one letter): ");
				userInputForMap = scnr.next();
				if (userInputForMap.equals("Done") || userInputForMap.equals("done")) {
					 isUserDone = true;
				} else {
					varKey = userInputForMap.charAt(0);
					System.out.println("Please enter the value for \"" + varKey + "\":");
					varValue = scnr.nextInt();
					userValues.put(varKey, varValue);
				}
				
			}
			System.out.println("The result of evaluating the postFix: " + Calculator.evaluatePostfix(postFixStack, userValues));
		} else {
			System.out.println("The result of evaluating the postFix: " + Calculator.evaluatePostfix(postFixStack));
		}
		
		scnr.close();
	}
}
