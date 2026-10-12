/**
 * This ArrayLog object represents a Log ADT implemented as
 * a generic data type array using the EnhancedLogInterface.
 *
 * @author
 * @version
 */
@SuppressWarnings("unchecked")
public class ArrayLog<T> implements EnhancedLogInterface<T>
{
    private T[] log;
    private String name;
    private int size;

    public ArrayLog(String name)
    {
        this.log = (T[])new Object[4];
        this.name = name;
        this.size = 0;
    }

    public String getName()
    {
        return name;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean isFull()
    {
        return size >= log.length;
    }

    public void add(T element)
    {
        if (size == log.length)
        {
            T[] newArray = (T[])new Object[log.length * 2];

            for (int i = 0; i < size; i++)
            {
                newArray[i] = log[i];
            }

            log = newArray;
        }

        log[size++] = element;
    }

    @Override
    public T get(int index) 
    {
    if (index < 0 || index >= log.length) 
    {
        throw new IndexOutOfBoundsException("Index out of bounds");
    }
    return log[index];
    }

    public int indexOf(T element)
    {
        for (int i = 0; i < size; i++)
        {
            if (element == null)
            {
                if (log[i] == null)
                {
                    return i;
                }
            }
            else if (element.equals(log[i]))
            {
                return i;
            }
        }

        return -1;
    }

    public boolean contains(T element)
    {
        return indexOf(element) != -1;
    }

    public String toString()
    {
        String result = "Log: " + name + "\n";

        for (int i = 0; i < size; i++)
        {
            result += (i + 1) + ". " + log[i] + "\n";
        }

        return result;
    }

    public T set(int index, T element)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        T oldElement = log[index];
        log[index] = element;

        return oldElement;
    }

    public void add(int index, T element)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (size == log.length)
        {
            T[] newArray = (T[])new Object[log.length * 2];

            for (int i = 0; i < size; i++)
            {
                newArray[i] = log[i];
            }

            log = newArray;
        }

        for (int i = size; i > index; i--)
        {
            log[i] = log[i - 1];
        }

        log[index] = element;
        size++;
    }

    public T remove(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        T removedElement = log[index];

        for (int i = index; i < size - 1; i++)
        {
            log[i] = log[i + 1];
        }

        size--;
        log[size] = null;

        return removedElement;
    }

    public boolean remove(T element)
    {
        int index = indexOf(element);

        if (index != -1)
        {
            remove(index);
            return true;
        }

        return false;
    }

    public void clear()
    {
        for (int i = 0; i < size; i++)
        {
            log[i] = null;
        }

        size = 0;

        while (log.length > 4)
        {
            log = (T[])new Object[log.length / 2];
        }
    }
}
