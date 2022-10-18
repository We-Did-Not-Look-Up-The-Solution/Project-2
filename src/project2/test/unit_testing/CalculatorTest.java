package project2.test.unit_testing;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.InputMismatchException;

import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;

import project2.stacks.Calculator;

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
		
	@Test
	 void testConvertToPostfix() {
		// single operand expressions (a+b, c/d, etc.), 
		// an empty expression 
		// really long expression
		
		// Can it handle all letters?
		
		// Test an invalid infix expression
		
		// test operator with same precedence back to back
		
		// test incomplete expression with unclosed parenthesis (or '{' '[' ...)
		
		// expression entirely made up of operators
		
		// expression with modulo
	 }
	
	@Test
	void testGetPrecedenceOf() {
		// test any operators not defined
		
		// test same operator in a row
		
	}
	
	@Test
	void testEvaluatePostfix() {
		// test with invalid postfix
		
		// test with really long postfix
		
		// test with a map
		
	}
	
	@Test
	void testPerfromOperation() {
		// test with invalid operation
		
		// test with really big integers
		
		// test with really small integers
		
	}
	
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
