package project2.test.unit_testing;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;

import project2.stacks.ResizableArrayStack;
/**
 * Test every method that was written by us, not the ones provided in the SourceCode.zip
 * 
 */
class ResizableArrayTest {

	@Test
	void test() {
		// test the clear method, to see if it actually clears the stack
		
		ResizableArrayStack<String> testArrStack = new ResizableArrayStack<String>();
		testArrStack.push("Coding");
		testArrStack.push("is");
		testArrStack.push("fun!");
		
		// assert that the first entry is "fun!", not "Coding"
		assertEquals(testArrStack.peek(), "fun!");
		
		testArrStack.clear();
		
		ThrowingRunnable clearTest = new ThrowingRunnable() {
			
			@Override
			public void run() throws Throwable {
				testArrStack.peek(); // should throw exception since the stack should have been cleared
			}
		};
		
		// Assert that an exception is fired
		assertThrows(EmptyStackException.class, clearTest);
		
		// test if called again after cleared
		testArrStack.clear(); // should not throw exception since the stack is empty and uses the isEmpty() method
		
	}

}
