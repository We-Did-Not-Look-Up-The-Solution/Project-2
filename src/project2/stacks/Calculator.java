package project2.stacks;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.InputMismatchException;
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
			if (!Character.isLetter(opperandChar)) { // Only gets values of letter variables
				if (Character.isUpperCase(opperandChar))
					System.err.println("Expected " + opperandChar + " to be a lowercase letter");
				else
					throw new InvalidParameterException("A variable was not a lowercase letter when attempting to get its value");
			}
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
			throw new InputMismatchException("User defined values did not contain a definition for (or was null): " + opperandChar);
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
}
