package project2.stacks;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Make an algorithim for Task 1 with the base algorithims in Task 2 and 4
 * @author wwwyv
 *
 */
public class Calculator {
	public static Map<Character, Integer> variableMap;

	/**
	 * Converts an infix expression to an equivalent postfix expression.
	 * @param infix
	 */
	public static String convertToPostFix(LinkedStack<Character> infix) {
		StackInterface<Character> operatorStack = new LinkedStack<Character>();
		String postFix = "";
		LinkedStack<Character> infixCopy = infix;
		while (!infixCopy.isEmpty()) { // while infix has chars to parse
			char nextCharacter = infixCopy.pop(); // next nonblank char of infix
			switch (nextCharacter) {
			case 'a': case 'b': case 'c':
				postFix += nextCharacter;
				break;
			case '^':
				operatorStack.push(nextCharacter);
				break;
			case '+': case '-': case '*': case '/': // Right to left precedence, must save operator before appending
				while (!operatorStack.isEmpty() && (getPrecedenceOf(nextCharacter) <= getPrecedenceOf(operatorStack.peek()))) {
					postFix += operatorStack.peek();
					operatorStack.pop();
				}
				operatorStack.push(nextCharacter);
				break;
			case '(':
				operatorStack.push(nextCharacter);
				break;
			case ')': // Stack is not empty if infix expression is valid
				char topOperator = operatorStack.pop();
				while (topOperator != '(' && !operatorStack.isEmpty()) {
					postFix += topOperator;
					topOperator = operatorStack.pop();
				}
				break;
			default: // will also handle variables (as single letters; also handles numbers as single digits)
				
				if (Character.isLetter(nextCharacter))
					postFix += nextCharacter;
				if (Character.isDigit(nextCharacter))
					postFix += nextCharacter;
				
				break;
			}
		}
		
		while (!operatorStack.isEmpty()) {
			char topOperator = operatorStack.pop();
			postFix = postFix + topOperator;
		}
		return postFix;
	}
	
	public static int getPrecedenceOf(char unaryOperator) {
		switch (unaryOperator) {
		case '^':
			return 5;
		case '*': case '/':
			return 4;
		case '-': case '+':
			return 3;
		default:
			return -1;
			
		}
	}
	
	/**
	 * Evaluates a postfix expression
	 * @param postfix
	 * @return
	 */
	public static int evaluatePostfix(ResizableArrayStack<Character> postfix) {
		ResizableArrayStack<Integer> valueStack = new ResizableArrayStack<Integer>();
		ResizableArrayStack<Character> postfixCopy = postfix;
		while (!postfixCopy.isEmpty()) { // while postfix has chars to parse
			char nextCharacter = postfixCopy.pop(); // next nonblank char of infix
			switch (nextCharacter) {
			case 'a': case 'b': case 'c':
				valueStack.push(getValueOf(nextCharacter));
				break;
			case '+': case '-': case '*': case '/': case '^':
				int opperandTwo = (valueStack.pop());
				int opperandOne = (valueStack.pop());
				int result = perfromOperation(nextCharacter, opperandOne, opperandTwo);
				valueStack.push(result);
				break;
			default:
				if (Character.isLetter(nextCharacter))
					valueStack.push(getValueOf(nextCharacter));
				break; // ignore unexpected chars
			}
		}
		return valueStack.peek();
	}
	
	/**
	 * Evaluates a postfix expression
	 * @param postfix
	 * @return
	 */
	public static int evaluatePostfix(ResizableArrayStack<Character> postfix, Map<Character, Integer> userVariableMap) {
		ResizableArrayStack<Integer> valueStack = new ResizableArrayStack<Integer>();
		ResizableArrayStack<Character> postfixCopy = postfix;
		while (!postfixCopy.isEmpty()) { // while postfix has chars to parse
			char nextCharacter = postfixCopy.pop(); // next nonblank char of infix
			switch (nextCharacter) {
			case 'a': case 'b': case 'c':
				valueStack.push(getValueOf(userVariableMap, nextCharacter));
				break;
			case '+': case '-': case '*': case '/': case '^':
				int opperandTwo = (valueStack.pop());
				int opperandOne = (valueStack.pop());
				int result = perfromOperation(nextCharacter, opperandOne, opperandTwo);
				valueStack.push(result);
				break;
			default:
				if (Character.isLetter(nextCharacter))
					valueStack.push(getValueOf(nextCharacter));
				break; // ignore unexpected chars
			}
		}
		return valueStack.peek();
	}
	
	/**
	 * get the int value of a char, with preassigned values for those characters
	 * @param opperandChar
	 * @return
	 */
	public static int getValueOf(char opperandChar) {
		switch(opperandChar) {
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
	
	/**
	 * Returns the value of the passed variable from the passed map.
	 * Requires the map to have an entry for the char and non empty!
	 * @param valueMap
	 * @param opperandChar
	 * @return
	 */
	public static int getValueOf(Map<Character, Integer> valueMap, char opperandChar) {
		if (valueMap.get(opperandChar) == null) {
			System.out.println("The variable '" + opperandChar + "' does not have a value. Using 0.");
			return 0;
		} else {
			return valueMap.get(opperandChar);
		}
	}
	
	/**
	 * Perform the opperation of the passed char to the opperands
	 * @param opperationChar
	 * @param opperandOne
	 * @param opperandTwo
	 * @return
	 */
	public static int perfromOperation(char opperationChar, int opperandOne, int opperandTwo) {
		switch(opperationChar) {
		case '+':
			// System.out.println("Adding " + opperandOne + " and " + opperandTwo);
			return opperandOne + opperandTwo;
		case '-':
			// System.out.println("Subtracting " + opperandOne + " and " + opperandTwo);
			return opperandOne - opperandTwo;
		case '*':
			// System.out.println("Multiplying " + opperandOne + " and " + opperandTwo);
			return opperandOne * opperandTwo;
		case '/':
			// System.out.println("Dividing " + opperandOne + " and " + opperandTwo);
			return opperandOne / opperandTwo;
		case '^':
			// System.out.println("Perfroming " + opperandOne + " to the power of " + opperandTwo);
			return opperandOne ^ opperandTwo;
		default:
			// System.out.println("Invalid or Unknow operation; Returning 0");
			return 0;
		}
	}
	
	/**
	 * Test conversion with hardcorded vars. Needs to input infix backwards, else results are incorrect
	 * @param args
	 
	public static void main(String[] args) {
		LinkedStack<Character> inFixStack = new LinkedStack<Character>();
		ResizableArrayStack<Character> postFixStack = new ResizableArrayStack<Character>();
		Scanner scnr = new Scanner(System.in);
		String infixExpression = "a+b*c"; // hardcode
		System.out.println("Please type in your infix expression (Can have spaces): ");
		if (scnr.hasNext()) infixExpression = scnr.next();
		System.err.println("Variable values are hardcoded, please make sure they are correct (otherwise, they are 0)");
		String postfixExpression = "";
		char[] infixExpressArray = infixExpression.toCharArray();
		
		for (int index = infixExpressArray.length - 1; index > -1; index--) {
			inFixStack.push(infixExpressArray[index]);
		}
		
		postfixExpression = convertToPostFix(inFixStack);
		System.out.println("The postFix form infix: " + postfixExpression);
		
		char[] postfixExpressArray = postfixExpression.toCharArray();
		for (int index = postfixExpressArray.length - 1; index > -1; index--) {
			postFixStack.push(postfixExpressArray[index]);
		}
		System.out.println("The result of evaluating the postFix: " + evaluatePostfix(postFixStack));
		scnr.close();
	}*/
	
	/**
	 * Test Calculator with an infix expression given by user, then asking if user wants to define variables before evaluating, then evaluate
	 * @param args
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
		
		postfixExpression = convertToPostFix(inFixStack);
		System.out.println("The postFix form infix: " + postfixExpression);
		
		char[] postfixExpressArray = postfixExpression.toCharArray();
		for (int index = postfixExpressArray.length - 1; index > -1; index--) {
			postFixStack.push(postfixExpressArray[index]);
		}
		// show current var values
		System.out.println("These are the variable values that will be used for evaluation:");
		for (int i = 0; i + 1 < alphaArray.length - 1; i += 2) {
			System.out.println(alphaArray[i] + " = " + getValueOf(alphaArray[i]) + "	" + alphaArray[i + 1] + " = " + getValueOf(alphaArray[i + 1]));
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
			System.out.println("The result of evaluating the postFix: " + evaluatePostfix(postFixStack, userValues));
		} else {
			System.out.println("The result of evaluating the postFix: " + evaluatePostfix(postFixStack));
		}
		
		scnr.close();
	}
	
}
