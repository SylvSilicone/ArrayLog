
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
        this.log = null;
        this.name = name;
        this.size = 0;
    }
    
    // returns the name of this StringLog
    public String getName()
    {
        return name;
    }

    // returns the logical size of this StringLog
    public int size()
    {
        return size;
    }
    
    // returns true if this list contains no elements
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    // returns true if this list is completely full
    public boolean isFull()
    {
        return false;
    }

    // appends the specified element to the end of this list
    public void add(T element)
    {
        LLNode<T> newNode = new LLNode<T>(element);

        if (log == null)
        {
            log = newNode;
        }
        else
        {
            LLNode<T> curNode = log;
            while (curNode.getLink() != null)
            {
                curNode = curNode.getLink();
            }
            curNode.setLink(newNode);
        }

        size++;
    }
  
    // returns the element at the specified position in this list
    public T get(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        LLNode<T> curNode = log;
        for (int i = 0; i < index; i++)
        {
            curNode = curNode.getLink();
        }

        return curNode.getInfo();
    }
    
    // returns the index of the first occurance of the specified element
    // in this list, or -1 if this list does not contain the element
    public int indexOf(T element)
    {
        LLNode<T> curNode = log;
        for (int i = 0; i < size; i++)
        {
            if (element == null ? curNode.getInfo() == null : element.equals(curNode.getInfo()))
            {
                return i;
            }
            curNode = curNode.getLink();
        }

        return -1;
    }
    
    // returns true if this list contains the specified element
    public boolean contains(T element)
    {
        return indexOf(element) != -1;
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
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        LLNode<T> curNode = log;
        for (int i = 0; i < index; i++)
        {
            curNode = curNode.getLink();
        }

        T oldElement = curNode.getInfo();
        curNode.setInfo(element);
        return oldElement;
    }
    
    // inserts the specified element at the specified position in this list
    public void add(int index, T element)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        LLNode<T> newNode = new LLNode<T>(element);
        if (index == 0)
        {
            newNode.setLink(log);
            log = newNode;
        }
        else
        {
            LLNode<T> curNode = log;
            for (int i = 0; i < index - 1; i++)
            {
                curNode = curNode.getLink();
            }
            newNode.setLink(curNode.getLink());
            curNode.setLink(newNode);
        }

        size++;
    }
    
    // removes the element at the specified position in this list
    public T remove(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        LLNode<T> removedNode;
        if (index == 0)
        {
            removedNode = log;
            log = log.getLink();
        }
        else
        {
            LLNode<T> curNode = log;
            for (int i = 0; i < index - 1; i++)
            {
                curNode = curNode.getLink();
            }
            removedNode = curNode.getLink();
            curNode.setLink(removedNode.getLink());
        }

        size--;
        return removedNode.getInfo();
    }
    
    // removes the first occurance of the specified element from this
    // list, if it is present
    public boolean remove(T element)
    {
        int index = indexOf(element);
        if (index == -1)
        {
            return false;
        }

        remove(index);
        return true;
    }
        
    // removes all of the elements from this list
    public void clear()
    {
        log = null;
        size = 0;
    }
}
