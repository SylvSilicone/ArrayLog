
/**
 * This LinkedLog object represents a Log ADT implemented as
 * a LinkedList using the EnhancedLogInterface.
 * 
 * @author 
 * @version 
 */
public class LinkedLog<T> implements EnhancedLogInterface<T>
{
    private LLNode<T> log;
    private String name;
    private int size;

    public LinkedLog(String name)
    {
    }
    
    // returns the name of this StringLog
    public String getName()
    {
        return "";
    }

    // returns the logical size of this StringLog
    public int size()
    {
        return -1;
    }
    
    // returns true if this list contains no elements
    public boolean isEmpty()
    {
        return false;
    }
    
    // returns true if this list is completely full
    public boolean isFull()
    {
        return true;
    }

    // appends the specified element to the end of this list
    public void add(T element)
    {
    }
  
    // returns the element at the specified position in this list
    public T get(int index)
    {
        return null;
    }
    
    // returns the index of the first occurance of the specified element
    // in this list, or -1 if this list does not contain the element
    public int indexOf(T element)
    {
        return -1;
    }
    
    // returns true if this list contains the specified element
    public boolean contains(T element)
    {
        return false;
    }
    
    // returns a formatted string representation of this StringLog
    public String toString()
    {
        String result = "Log: " + name + "\n";
        LLNode<T> curNode = log;
        int count = 0;
    
        while (curNode != null)
        {
            count++;
            result += count + ". " + curNode.getInfo() + "\n";
            curNode = curNode.getLink();
        }
    return result;
    }
    
    // replaces the element at the specified position in this list
    // with the specified element
    public T set(int index, T element)
    {
        return null;
    }
    
    // inserts the specified element at the specified position in this list
    public void add(int index, T element)
    {
    }
    
    // removes the element at the specified position in this list
    public T remove(int index)
    {
        return null;
    }
    
    // removes the first occurance of the specified element from this
    // list, if it is present
    public boolean remove(T element)
    {
        return false;
    }
        
    // removes all of the elements from this list
    public void clear()
    {
    }
}
