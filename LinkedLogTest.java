import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * This class tests the ArrayLog class
 * 
 * @author Mr. Bredemeier
 * @version September 30, 2012
 */

public class LinkedLogTest<T> extends LogTest<T>
{
    private static Boolean failed = false;
    private static ArrayList<String> methodNames = new ArrayList<String>();
    private static LinkedLog<String> sl;
    private static String message;
    private static String methodName;
    private static Field fieldLog, fieldName, fieldSize;
    private static Class<?> c;

    public static void main(String [] args)
    {
        failed = testInterface();
        failed = testLinkedLog();
        if(failed)
            System.out.println("\nSorry, try again.");
        else
            System.out.println("\nCongratulations!  You have completed the LinkedLog assignment");
    }
    
    private static boolean testLinkedLog()
    {
        //********** LinkedLog Class Test **************************************
        // Instantiate a new LinkedLog object
        System.out.println("Now testing your LinkedLog class: \n");
        try 
        {
            sl = new LinkedLog<String>("Test");
            c = Class.forName("LinkedLog");
        }
        catch(ClassNotFoundException e)
        {
            failure("Epic Failure: missing class LinkedLog");
            return failed;
        }
        catch(NoClassDefFoundError e)
        {
            failure("Epic Failure: missing LinkedLog class");
            return failed;
        }
        catch (NoSuchMethodError e)
        {
            failure("Failed: missing constructor LinkedLog(String name)");
            return failed;
        }        
        
        // make sure that LinkedLog implements EnhancedLogInterface
        if(!(sl instanceof EnhancedLogInterface))
        {
            failure("Failed: LinkedLog does not implement EnhancedLogInterface");
            return failed;
        }
        System.out.println("Passed LinkedLog implements EnhancedLogInterface test");

        // verify that 'log' LLNode instance variable exists
        try
        {
            fieldLog = c.getDeclaredField("log");         
            fieldLog.setAccessible(true); 
        }
        catch(NoSuchFieldException e)
        {
            failure("missing 'log' LLNode instance variable");
            return failed;
        }
        
        // validate that 'log' LLNode has been instantiated
        try
        {
            LLNode log = (LLNode)fieldLog.get(sl); 
            if(log != null)
            {
                failure("'log' LLNode instance variable not instantiated");
                return failed;
            }
        }
        catch(IllegalAccessException e)
        {
            failure("you do not have rights to access private field data");
            return failed;
        }

        
        // verify that 'name' String instance variable exists
        try
        {
            fieldName = c.getDeclaredField("name");         
            fieldName.setAccessible(true); 
        }
        catch(NoSuchFieldException e)
        {
            failure("missing 'name' String instance variable");
            return failed;
        }
        
        // validate that 'name' String instance variable is initialized properly
        try
        {
            String name = (String)fieldName.get(sl); 
            if(name == null)
            {
                failure("'name' instance variable not initialized");
                return failed;
            }
            if(!name.equals("Test"))
            {
                failure("constructor does not properly initialize 'name' instance variable");
                return failed;
            }
        }
        catch(IllegalAccessException e)
        {
            failure("you do not have rights to access private field data");
            return failed;
        }

        // verify that size integer instance variable exists
        try
        {
            fieldSize = c.getDeclaredField("size");         
            fieldSize.setAccessible(true); 
        }
        catch(NoSuchFieldException e)
        {
            failure("missing 'size' integer instance variable");
            return failed;
        }

        // validate that size instance variable is initialized properly
        try
        {
            int size = (int)fieldSize.get(sl); 
            if(size != 0)
            {
                failure("constructor does not properly initialize 'size' instance variable");
                return failed;
            }
        }
        catch(IllegalAccessException e)
        {
            failure("you do not have rights to access private field data");
            return failed;
        }        
        System.out.println("Passed LinkedLog constructor test");

        // validate getName method
        if (! sl.getName().equals("Test"))
        {
            failure("Failed: incorrect getName method");
            return failed;
        }
        System.out.println("Passed getName method test");

        // validate getSize method
        if (sl.size() != 0)
        {
            failure("Failed: incorrect getSize method");
            return failed;
        }

        // validate isEmpty method
        if (!sl.isEmpty())
        {
            failure("Failed: incorrect isEmpty method");
            return failed;
        }
        
        // test add(element) method
        sl.add("This is the first test entry");
        try
        {
            LLNode log = (LLNode)fieldLog.get(sl);
            if(log == null || log.getLink() != null)
            {
                failure("add(T) method does not properly set links");
                return failed;
            }
            if(!log.getInfo().equals("This is the first test entry"))
            {
                failure("add(T) method does not properly set info values");
                return failed;
            }
        }
        catch(IllegalAccessException e)
        {
           failure("you do not have rights to access private field data");
           return failed;
        }
        
        // validate that size instance variable gets incremented
        try
        {
            int size = (int)fieldSize.get(sl); 
            if(size != 1)
            {
                failure("'size' instance variable not incremented properly");
                return failed;
            }
        }
        catch(IllegalAccessException e)
        {
            failure("you do not have rights to access private field data");
            return failed;
        }        
        
        // validate that size method is correct
        if (sl.size() != 1)
        {
            failure("Failed: incorrect getSize method");
            return failed;
        }
        System.out.println("Passed size method test");

        // continued validation of isEmpty method (no longer empty)
        if (sl.isEmpty())
        {
            failure("Failed: incorrect isEmpty method");
            return failed;
        }
        
        // add another element and test
        sl.add("This is the second test entry");
        try
        {
            LLNode log = (LLNode)fieldLog.get(sl); 
            if(log == null || log.getLink() == null || log.getLink().getLink() != null)
            {
                failure("add(T) method does not properly set links");
                return failed;
            }
            if(!log.getLink().getInfo().equals("This is the second test entry"))
            {
                failure("add(T) method does not properly set info values");
                return failed;
            }
        }
        catch(IllegalAccessException e)
        {
           failure("you do not have rights to access private field data");
           return failed;
        }
        
        // add a third entry and test
        sl.add("This is the third test entry");
        try
        {
            LLNode log = (LLNode)fieldLog.get(sl); 
            if(log == null || log.getLink() == null || log.getLink().getLink() == null ||
               log.getLink().getLink().getLink() != null)
            {
                failure("add(T) method does not properly set links");
                return failed;
            }
            if(!log.getLink().getLink().getInfo().equals("This is the third test entry"))
            {
                failure("add(T) method is not correct");
                return failed;
            }
        }
        catch(IllegalAccessException e)
        {
           failure("you do not have rights to access private field data");
           return failed;
        }
        System.out.println("Passed add(T) method test");
        
        // test get method
        if(!sl.get(0).equals("This is the first test entry"))
        {
            failure("get(int) method is not correct");
            return failed;
        }
        if(!sl.get(1).equals("This is the second test entry"))
        {
            failure("get(int) method is not correct");
            return failed;
        }
        if(!sl.get(2).equals("This is the third test entry"))
        {
            failure("get(int) method is not correct");
            return failed;
        }
        System.out.println("Passed get(int) method test");
        
        // continued test of isFull method (list is not full)
        if(sl.isFull())
        {
            failure("isFull method is not correct");
            return failed;
        }
        
        // continued test of isEmpty method (list is not full)
        if(sl.isEmpty())
        {
            failure("isEmpty method is not correct");
            return failed;
        }
        System.out.println("Passed isEmpty method test");

        // add a fourth element
        sl.add("This is the fourth test entry");
        if(sl.isFull())
        {
            failure("isFull method is not correct");
            return failed;
        }
        System.out.println("Passed isFull method test");
        
        // test indexOf method
        if(sl.indexOf("This is the third test entry") != 2 ||
           sl.indexOf("This is the first test entry") != 0 ||
           sl.indexOf("klajsldkfjaskjf") != -1)
        {
            failure("indexOf(T) method is not correct");
            return failed;
        }
        System.out.println("Passed indexOf(T) method test");
        
        // test contains method
        if(!sl.contains("This is the first test entry") ||
           !sl.contains("This is the fourth test entry") ||
           sl.contains("oiqoiuqworuoeiuroi"))
        {
            failure("contains(T) method is not correct");
            return failed;
        }
        System.out.println("Passed contains(T) method test");
        
        // test toString method
        String result = "Log: Test\n" +
                        "1. This is the first test entry\n" +
                        "2. This is the second test entry\n" +
                        "3. This is the third test entry\n" +
                        "4. This is the fourth test entry\n";
        if(!sl.toString().equals(result))
        {
            failure("toString method is not correct");
            return failed;
        }
        System.out.println("Passed toString method test");
                        
        sl.add("This is the fifth test entry");
        // continued validation of size method
        if(sl.size() != 5)
        {
            failure("'size' instance variable is not correct");
            return failed;
        }

        // continued validation of isFull method
        if(sl.isFull())
        {
            failure("isFull method is not correct");
            return failed;
        }
        // continued test indexOf method
        if(sl.indexOf("This is the third test entry") != 2 ||
           sl.indexOf("This is the first test entry") != 0 ||
           sl.indexOf("This is the fifth test entry") != 4 ||
           sl.indexOf("klajsldkfjaskjf") != -1)
        {
            failure("indexOf(T) method is not correct");
            return failed;
        }
        
        // test set method
        if(!sl.set(1,"This is the replaced second test entry").equals("This is the second test entry") ||
           !sl.set(3,"This is the replaced fourth test entry").equals("This is the fourth test entry"))
        {
            failure("set(int, T) method does not return the correct previous element");
            return failed;
        }
           
        if(!sl.get(1).equals("This is the replaced second test entry") ||
           !sl.get(3).equals("This is the replaced fourth test entry"))
        {
            failure("set(int, T) method is not correct");
            return failed;
        }
        System.out.println("Passed set method test");
        
        // test add(int, T method)
        sl.add(2,"This is an inserted third test entry");
        if(!sl.get(0).equals("This is the first test entry") ||
           !sl.get(1).equals("This is the replaced second test entry") ||
           !sl.get(2).equals("This is an inserted third test entry") ||
           !sl.get(3).equals("This is the third test entry") ||
           !sl.get(4).equals("This is the replaced fourth test entry") ||
           !sl.get(5).equals("This is the fifth test entry"))
        {
            failure("add(int, T) method is not correct");
            return failed;
        }
        if(sl.size() != 6)
        {
            failure("size instance variable not incremented in add(int, T) method");
            return failed;
        }
        System.out.println("Passed add(int, T) method test");
        
        // test remove(int) method
        if(sl.remove(3) == null || 
          !sl.remove(2).equals("This is an inserted third test entry") ||
          !sl.remove(0).equals("This is the first test entry"))
        {
            failure("remove(int) method is not correct");
            return failed;
        } 
        if(!sl.get(0).equals("This is the replaced second test entry") ||
           !sl.get(1).equals("This is the replaced fourth test entry") ||
           !sl.get(2).equals("This is the fifth test entry"))
        {
            failure("remove(int) method is not correct");
            return failed;
        }
        if(sl.size() != 3)
        {
            failure("size instance variable not decremented in remove(int) method");
            return failed;
        }
        System.out.println("Passed remove(int) method test");

        if(!sl.remove("This is the replaced second test entry") ||
           !sl.remove("This is the fifth test entry") ||
            sl.remove("m,zxn,mnm,n"))
        {
            failure("remove(T) method is not correct");
            return failed;
        }
            
        if(!sl.get(0).equals("This is the replaced fourth test entry"))
        {
            failure("remove(T) method is not correct");
            return failed;
        }
        if(sl.size() != 1)
        {
            failure("size instance variable not decremented in remove(T) method");
            return failed;
        }
        System.out.println("Passed remove(T) method test");
        
        // test clear method
        sl.clear();
        if(sl.size() != 0)
        {
            failure("incorrect clear method (did not set size to zero)");
            return failed;
        }
        // continued validation of isFull method
        if(sl.isFull())
        {
            failure("isFull method is not correct");
            return failed;
        }
        // continued validation of isEmpty method
        if(!sl.isEmpty())
        {
            failure("isEmpty method is not correct");
            return failed;
        }
        System.out.println("Passed clear method test");
        
        return failed;
    }
    
    private static void failure(String str)
    {
        System.out.println("*** " + str);
        failed = true;
    }
}
