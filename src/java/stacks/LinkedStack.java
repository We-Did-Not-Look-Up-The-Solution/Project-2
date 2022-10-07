package java.stacks;

import java.util.EmptyStackException;
/**
    A class of stacks whose entries are stored in a chain of nodes.
    @author Frank M. Carrano and Timothy M. Henry
    @version 5.0
*/
public final class LinkedStack<T> implements StackInterface<T>
{
	private Node topNode; // References the first node in the chain
  
   public LinkedStack()
   {
      topNode = null;
   } // end default constructor
  
//  < Implementations of the stack operations go here. >
//  . . .
   
   public void clear() {};
   
   @Override
   public boolean isEmpty() {
	   // TODO Auto-generated method stub
	   return false;
   }
   
   @Override
   public T peek() {
	   // TODO Auto-generated method stub
	   return null;
   }
   
   @Override
   public T pop() {
	   // TODO Auto-generated method stub
	   return null;
   }
   
   @Override
   public void push(T newEntry) {
	   // TODO Auto-generated method stub
   }
   
   // End Methods for LinkedStack

	private class Node
	{
      private T    data; // Entry in stack
      private Node next; // Link to next node
      
      private Node(T dataPortion)
      {
         this(dataPortion, null);
      } // end constructor
      
      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;
      } // end constructor
      
      private T getData()
      {
         return data;
      } // end getData
      
      private void setData(T newData)
      {
         data = newData;
      } // end setData
      
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node
} // end LinkedStack
