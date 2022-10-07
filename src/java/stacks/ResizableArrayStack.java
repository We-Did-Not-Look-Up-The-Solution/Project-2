package java.stacks;

public final class ResizableArrayStack<T> implements StackInterface<T>
{
	private T[] stack;    // Array of stack entries
	private int topIndex; // Index of top entry
   private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
  
   public ResizableArrayStack()
   {
      this(DEFAULT_CAPACITY);
   } // end default constructor
  
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
  
//  < Implementations of the stack operations go here. >
//  < Implementations of the private methods go here
   
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
//  . . .

	@Override
	public void push(T newEntry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
} // end ArrayStack
