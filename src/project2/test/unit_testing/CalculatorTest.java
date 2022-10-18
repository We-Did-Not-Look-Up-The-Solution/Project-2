package project2.test.unit_testing;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.InvalidParameterException;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.InputMismatchException;

import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;

import project2.stacks.Calculator;
import project2.stacks.LinkedStack;
import project2.stacks.ResizableArrayStack;

/**
 * Unit testing for the Calculator.class methods
 * @author wwwyv
 *
 */
public class CalculatorTest {

	// Tests should only be made to code made by us
		/*
		 * List of code made by us:
		 * Class: Calculator
		 * 		Methods: convertToPostFix(LinkedStack<Character) infix)
		 * 				 getPrecedenceOf(char unaryOperator)
		 * 				 evaluatePostfix(ResizableArrayStack<Character> postfix)
		 * 				 evaluatePostfix(ResizableArrayStack<Character> postfix, Map<Character, Integer> userVariableMap)
		 * 				 getValueOf(char opperandChar)
		 * 				 getValueOf(Map<Character, Integer> valueMap, char opperandChar)
		 * 				 perfromOperation(char opperationChar, int opperandOne, int opperandTwo)
		 * 
		 * Notes from Project 1 feedback: Implement corner cases (Cases where boundary values are tested). 
		 * 
		 * 
		 * Example of Edge case: A case that requires special handling. Like the method getCar(int numWheels) gets a car that has the passed number of wheels. 
		 * An edge case would be when it fails to get a car for 3 wheels or with 1 wheel (since cars don't usually have 1, 3, or 5 wheels, therefore an extreme case).
		 * 
		 * Example of Boundary case: An edge case tests extreme valid inputs, so a boundary checks the valid inputs around the edge case values (in the getCar() case,
		 * would test inputs 0, 2, 4, 6)
		 * 
		 * Example of corner case: cases testing multiple extreme values, but still within the vaild input
		 */
	
	/**
	 * Test converToPostfix with and without a map
	 */
	@Test
	 void testConvertToPostfix() {
		// single operand expressions (a+b, c/d, etc.)
		LinkedStack<Character> inFixStack = new LinkedStack<Character>();
		String infixExpression = "a+b*c"; // hardcode
		String postfixExpression = "";
		char[] infixExpressArray = infixExpression.toCharArray();
		
		// Fill it backwards to read left to right
		for (int index = infixExpressArray.length - 1; index > -1; index--) {
			inFixStack.push(infixExpressArray[index]);
		}
		
		assertEquals("abc*+", Calculator.convertToPostFix(inFixStack), "Infix Test");
		
		// test when filled the wring way (as if right to left)
		for (int index = 0; index > infixExpressArray.length - 1; index++) {
			inFixStack.push(infixExpressArray[index]);
		}
		assertNotEquals("abc*+", Calculator.convertToPostFix(inFixStack), "Infix Test");
		
		// an empty expression
		assertEquals("", Calculator.convertToPostFix(new LinkedStack<Character>()), "Infix Test for Empty stacks");
		
		// really long expression
		infixExpression = "(a+b+c+d+e) * (e/d ^ c)";
		infixExpressArray = infixExpression.toCharArray();
		for (int index = infixExpressArray.length - 1; index > -1; index--) {
			inFixStack.push(infixExpressArray[index]);
		}
		
		assertEquals("ab+c+d+e+edc^/*", Calculator.convertToPostFix(inFixStack), "Infix Test");
		
		// test incomplete expression with unclosed parenthesis (or '{' '[' ...)
		infixExpression = "(a+b*c";
		infixExpressArray = infixExpression.toCharArray();
		for (int index = infixExpressArray.length - 1; index > -1; index--) {
			inFixStack.push(infixExpressArray[index]);
		}
		assertNotEquals("abc*+", Calculator.convertToPostFix(inFixStack), "Infix Test for unclosed parens");
		
		
		// expression entirely made up of operators
		infixExpression = "++--//**";
		infixExpressArray = infixExpression.toCharArray();
		for (int index = infixExpressArray.length - 1; index > -1; index--) {
			inFixStack.push(infixExpressArray[index]);
		}
		assertEquals("++-//**-", Calculator.convertToPostFix(inFixStack), "Infix Test with just operators");
		
		// expression with modulo
		infixExpression = "d%b";
		infixExpressArray = infixExpression.toCharArray();
		for (int index = infixExpressArray.length - 1; index > -1; index--) {
			inFixStack.push(infixExpressArray[index]);
		}
		assertEquals("db", Calculator.convertToPostFix(inFixStack), "Infix Test with invalid operator");
	 }
	
	
	/**
	 * Test getPrecedenceOf(char operator)
	 */
	@Test
	void testGetPrecedenceOf() {
		// test any operators not defined
		assertEquals(Calculator.getPrecedenceOf('%'), -1);
		
		// test same operator in a row
		assertEquals(Calculator.getPrecedenceOf('+'), 3);
		assertEquals(Calculator.getPrecedenceOf('-'), 3);
		
		assertEquals(Calculator.getPrecedenceOf('*'), 4);
		assertEquals(Calculator.getPrecedenceOf('/'), 4);
		
		assertEquals(Calculator.getPrecedenceOf('^'), 5);
	}
	
	
	/**
	 * Test evaluatePostFix, with and without a map
	 */
	@Test
	void testEvaluatePostfix() {
		HashMap<Character, Integer> varMap = new HashMap<Character, Integer>(26);
		varMap.put('a', 5);
		varMap.put('b', 4);
		varMap.put('c', 3);
		varMap.put('d', 4);
		varMap.put('e', 1);
		// test with invalid postfix
		String correctPostfix = "ab*ca-/de*+";
		String postFix = "a*b/(c-a)+d*e";
		
		char[] correctPF = correctPostfix.toCharArray();
		char[] wrongPF = postFix.toCharArray();
		
		ResizableArrayStack<Character> correctRASStack = new ResizableArrayStack<Character>();
		ResizableArrayStack<Character> wrongRASStack = new ResizableArrayStack<Character>();
		
		for (int index = correctPF.length - 1; index > -1; index--) {
			correctRASStack.push(correctPF[index]);
		}
		
		for (int index = wrongPF.length - 1; index > -1; index--) {
			wrongRASStack.push(wrongPF[index]);
		}
		// test with a map
		assertEquals(Calculator.evaluatePostfix(correctRASStack, varMap), 20);
		
		// refill the correct stack to try without the map
		for (int index = correctPF.length - 1; index > -1; index--) {
			correctRASStack.push(correctPF[index]);
		}
		assertEquals(Calculator.evaluatePostfix(correctRASStack), 33);
		
		ThrowingRunnable badStack = new ThrowingRunnable() {
			
			@Override
			public void run() throws Throwable {
				assertNotEquals(Calculator.evaluatePostfix(wrongRASStack), 33);
			}
		};
		
		assertThrows(EmptyStackException.class, badStack);
		
		
	}
	
	/**
	 * Test perfromOperation(operator, opperand, opperand)
	 */
	@Test
	void testPerfromOperation() {
		// test with invalid operation
		assertNotEquals(Calculator.perfromOperation('%', 5, 2), (5%2));
		assertEquals(Calculator.perfromOperation('%', 2, 3), 0);
		
		// test with really big integers
		assertEquals(Calculator.perfromOperation('+', Integer.MAX_VALUE, Integer.MAX_VALUE), -2);
		
		// test with really small integers
		assertEquals(Calculator.perfromOperation('-', Integer.MIN_VALUE, Integer.MIN_VALUE), 0);
		
	}
	
	/**
	 * Test getValueOf() with or without a map
	 */
	@Test
	void testGetValueOf() {
		HashMap<Character, Integer> varMap = new HashMap<Character, Integer>(26);
		ThrowingRunnable testForPunct = new ThrowingRunnable() {
			
			@Override
			public void run() throws Throwable {
				Calculator.getValueOf('?');
			}
		};
		
		// The hardcoded values of a, b, c, d, e are 2, 3, 4, 5, 6. Any other value is 0 or throws an exception.
		
		assertEquals(Calculator.getValueOf('a'), 2); // test if a returns 2
		assertEquals(Calculator.getValueOf('b'), 3); // test if b returns 3
		
		assertEquals(Calculator.getValueOf('A'), 0); // test if a returns 2
		assertEquals(Calculator.getValueOf('B'), 0); // test if a returns 2
		
		assertEquals(Calculator.getValueOf('Ä'), 0); // Sort of difficlt to type this char. Could be an edge case
		assertEquals(Calculator.getValueOf('ß'), 0); // Sort of difficlt to type this char. Could be an edge case. Counts as lowercase letter
		assertEquals(Calculator.getValueOf('ػ'), 0); // Sort of difficlt to type this char. Could be an edge case Still counts as a letter
		
		assertThrows(InvalidParameterException.class, testForPunct);
		
		// Other Method
		varMap.put('a', 5);
		varMap.put('b', 4);
		varMap.put('c', 3);
		varMap.put('d', 4);
		varMap.put('e', 1);
		
		varMap.put('f', -1);
		varMap.put('g', -2);
		varMap.put('h', -3);
		
		varMap.put('i', null);
		varMap.put('j', null);
		
		varMap.put('K', 1);
		varMap.put('L', 2);
		
		varMap.put('M', 5);
		varMap.put('N', 5);
		
		varMap.put('M', -5);
		
		varMap.put('n', Integer.MAX_VALUE);
		varMap.put('o', Integer.MIN_VALUE);
		
		varMap.put('Ä', 10);
		
		ThrowingRunnable testForUndefined = new ThrowingRunnable() {
			@Override
			public void run() throws Throwable {
				Calculator.getValueOf(varMap, 'i');
			}
		};
		
		// test with a map
		assertEquals(Calculator.getValueOf(varMap, 'a'), 5);
		assertEquals(Calculator.getValueOf(varMap, 'b'), 4);
		assertEquals(Calculator.getValueOf(varMap, 'c'), 3);
		
		// test with negatives
		assertEquals(Calculator.getValueOf(varMap, 'f'), -1);
		assertEquals(Calculator.getValueOf(varMap, 'g'), -2);
		
		// user can't directly make a variable null; Extreme case
		assertThrows(InputMismatchException.class, testForUndefined);
		
		// test with uppercase letters
		assertEquals(Calculator.getValueOf(varMap, 'K'), 1);
		assertEquals(Calculator.getValueOf(varMap, 'M'), -5);
		
		// test with a really big integer value
		assertEquals(Calculator.getValueOf(varMap, 'n'), Integer.MAX_VALUE);
		
		
	}
}
