package project2.stacks;

import java.util.Arrays;
import java.util.EmptyStackException;

public final class ResizableArrayStack<T> implements StackInterface<T>
{
	private T[] stack;    // Array of stack entries
	private int topIndex; // Index of top entry
	private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
  
   /**
    * Makes an ArrayStack with the default capacity
    */
   public ResizableArrayStack()
   {
      this(DEFAULT_CAPACITY);
   } // end default constructor
  
   /**
    * Make a ArrayStack with a desired capacity
    * @param initialCapacity
    */
   public ResizableArrayStack(int initialCapacity)
   {
      integrityOK = false;
      checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[])new Object[initialCapacity];
      stack = tempStack;
		topIndex = -1;
      integrityOK = true;
  } // end constructor
  
   /** Throws an exception if this object is not initialized.*/
   private void checkIntegrity() {
      if (!integrityOK)
         throw new SecurityException("ArrayBag object is corrupt.");
   } // end checkIntegrity
   
   /**
	 * Check if the passed capacity is within the acceptable maximum capacity
	 * @param capacity The desired capacity to check
	 */
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException(
					"Attempted to make a bag whose capacity exceeds allowed max of " + MAX_CAPACITY);
	}

	/**
	 * Adds the newEntry into this stack. Will call ensureCapacity() if array should be resized.
	 * Is also O(1), but can become O(n) if array needs to be resized
	 */
	@Override
	public void push(T newEntry) {
		checkIntegrity();
		ensureCapacity();
		stack[topIndex + 1] = newEntry;
		topIndex++;
	} // end push
	
	/**
	 * Resizes the array stack to double its original size and verifies it
	 */
	private void ensureCapacity() {
		if (topIndex == stack.length - 1) { // If array is full, double its size
			int newLength = 2 * stack.length;
			checkCapacity(newLength);
			stack = Arrays.copyOf(stack,  newLength);
		} // end if
	} // end ensureCapacity

	/**
	 * Removes the top entry of this stack. Will throw an EmptyStackException if this is empty
	 * Is also O(1)
	 */
	@Override
	public T pop() {
		checkIntegrity();
		if (isEmpty())
			throw new EmptyStackException();
		else {
			T top = peek();
			stack[topIndex] = null;
			topIndex--;
			return top;
		} // end if
	} // end pop

	/**
	 * Returns the top entry of this stack without changing it; Passed as a refrence
	 * Is also O(1)
	 * 
	 */
	@Override
	public T peek() {
		checkIntegrity();
		if (isEmpty())
			throw new EmptyStackException();
		else
			return stack[topIndex];
	} // end peek

	/**
	 * Returns wheter this stack is empty or not
	 * @return true if empty; false if still contains entries
	 */
	@Override
	public boolean isEmpty() {
		return topIndex < 0;
	}

	/**
	 * Clears this stack by making all entries null
	 */
	@Override
	public void clear() {
		while (!isEmpty())
		  	pop();
		 
	}
	
} // end ArrayStack
