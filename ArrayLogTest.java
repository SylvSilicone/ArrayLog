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

public class ArrayLogTest<T> extends LogTest<T>
{
    private static Boolean failed = false;
    private static ArrayList<String> methodNames = new ArrayList<String>();
    private static ArrayLog<String> asl;
    private static String message;
    private static String methodName;
    private static Field fieldLog, fieldName, fieldSize;
    private static Class<?> c;

    public static void main(String [] args)
    {
        failed = testInterface();
        failed = testArrayLog();
        if(failed)
            System.out.println("\nSorry, try again.");
        else
            System.out.println("\nCongratulations!  You have completed the ArrayLog assignment");
    }
    
    private static boolean testArrayLog()
    {
        //********** ArrayLog Class Test **************************************
        // Instantiate a new ArrayLog object
        System.out.println("Now testing your ArrayLog class: \n");
        try 
        {
            asl = new ArrayLog<String>("Test");
            c = Class.forName("ArrayLog");
        }
        catch(ClassNotFoundException e)
        {
            failure("Epic Failure: missing ArrayLog class");
            return failed;
        }
        catch(NoClassDefFoundError e)
        {
            failure("Epic Failure: missing ArrayLog class");
            return failed;
        }
        catch (NoSuchMethodError e)
        {
            failure("Failed: missing constructor ArrayLog(String name)");
            return failed;
        }        
        
        // make sure that ArrayLog implements EnhancedLogInterface
        if(!(asl instanceof EnhancedLogInterface))
        {
            failure("Failed: ArrayLog does not implement EnhancedLogInterface");
            return failed;
        }
        System.out.println("Passed ArrayLog implements EnhancedLogInterface test");

        // verify that 'log' array instance variable exists
        try
        {
            fieldLog = c.getDeclaredField("log");         
            fieldLog.setAccessible(true); 
        }
        catch(NoSuchFieldException e)
        {
            failure("missing 'log' array instance variable");
            return failed;
        }
        
        // validate that 'log' array has been instantiated and only 4 elements
        try
        {
            Object[] log = (Object[])fieldLog.get(asl); 
            if(log == null)
            {
                failure("'log' array instance variable not instantiated");
                return failed;
            }
            if(log.length != 4)
            {
                failure("constructor does not create a new array of length 4");
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
            String name = (String)fieldName.get(asl); 
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
            int size = (int)fieldSize.get(asl); 
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
        System.out.println("Passed ArrayLog constructor test");

        // validate getName method
        if (! asl.getName().equals("Test"))
        {
            failure("Failed: incorrect getName method");
            return failed;
        }
        System.out.println("Passed getName method test");

        // validate getSize method
        if (asl.size() != 0)
        {
            failure("Failed: incorrect getSize method");
            return failed;
        }

        // validate isEmpty method
        if (!asl.isEmpty())
        {
            failure("Failed: incorrect isEmpty method");
            return failed;
        }
        
        // test add(element) method
        asl.add("This is the first test entry");
        try
        {
            Object[] log = (Object[])fieldLog.get(asl);
            if(log[0] == null || !log[0].equals("This is the first test entry"))
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
        
        // validate that size instance variable gets incremented
        try
        {
            int size = (int)fieldSize.get(asl); 
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
        if (asl.size() != 1)
        {
            failure("Failed: incorrect getSize method");
            return failed;
        }
        System.out.println("Passed size method test");

        // continued validation of isEmpty method (no longer empty)
        if (asl.isEmpty())
        {
            failure("Failed: incorrect isEmpty method");
            return failed;
        }
        
        // add another element and test
        asl.add("This is the second test entry");
        try
        {
            Object[] log = (Object[])fieldLog.get(asl); 
            if(log[1] == null || !log[1].equals("This is the second test entry"))
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
        
        // add a third entry and test
        asl.add("This is the third test entry");
        try
        {
            Object[] log = (Object[])fieldLog.get(asl); 
            if(log[2] == null || !log[2].equals("This is the third test entry"))
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
        if(asl.get(0) == null || !asl.get(0).equals("This is the first test entry"))
        {
            failure("get(int) method is not correct");
            return failed;
        }
        if(!asl.get(1).equals("This is the second test entry"))
        {
            failure("get(int) method is not correct");
            return failed;
        }
        if(!asl.get(2).equals("This is the third test entry"))
        {
            failure("get(int) method is not correct");
            return failed;
        }
        System.out.println("Passed get(int) method test");
        
        // continued test of isFull method (array is not full)
        if(asl.isFull())
        {
            failure("isFull method is not correct");
            return failed;
        }
        
        // continued test of isEmpty method (array is not full)
        if(asl.isEmpty())
        {
            failure("isEmpty method is not correct");
            return failed;
        }
        System.out.println("Passed isEmpty method test");

        // add a fourth element and check isFull method (array is now full)
        asl.add("This is the fourth test entry");
        if(!asl.isFull())
        {
            failure("isFull method is not correct");
            return failed;
        }
        System.out.println("Passed isFull method test");
        
        // test indexOf method
        if(asl.indexOf("This is the third test entry") != 2 ||
           asl.indexOf("This is the first test entry") != 0 ||
           asl.indexOf("klajsldkfjaskjf") != -1)
        {
            failure("indexOf(T) method is not correct");
            return failed;
        }
        System.out.println("Passed indexOf(T) method test");
        
        // test contains method
        if(!asl.contains("This is the first test entry") ||
           !asl.contains("This is the fourth test entry") ||
           asl.contains("oiqoiuqworuoeiuroi"))
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
        if(!asl.toString().equals(result))
        {
            failure("toString method is not correct");
            return failed;
        }
        System.out.println("Passed toString method test");
                        
        // test dynamic doubling of ArrayLog
        System.out.println("\nNow testing dynamic doubling of ArrayLog");
        asl.add("This is the fifth test entry");
        try
        {
            Object[] log = (Object[])fieldLog.get(asl); 
            if(log.length != 8)
            {
                failure("Array did not double in size");
                return failed;
            }
        }
        catch(IllegalAccessException e)
        {
            failure("you do not have rights to access private field data");
            return failed;
        }

        // continued validation of size method
        if(asl.size() != 5)
        {
            failure("'size' instance variable is not correct");
            return failed;
        }

        // continued validation of isFull method
        if(asl.isFull())
        {
            failure("isFull method is not correct");
            return failed;
        }
        // continued test indexOf method
        if(asl.indexOf("This is the third test entry") != 2 ||
           asl.indexOf("This is the first test entry") != 0 ||
           asl.indexOf("This is the fifth test entry") != 4 ||
           asl.indexOf("klajsldkfjaskjf") != -1)
        {
            failure("indexOf(T) method is not correct");
            return failed;
        }
        System.out.println("Passed dynamic doubling of array");
        
        // test set method
        if(!asl.set(1,"This is the replaced second test entry").equals("This is the second test entry") ||
           !asl.set(3,"This is the replaced fourth test entry").equals("This is the fourth test entry"))
        {
            failure("set(int, T) method does not return the correct previous element");
            return failed;
        }
           
        if(!asl.get(1).equals("This is the replaced second test entry") ||
           !asl.get(3).equals("This is the replaced fourth test entry"))
        {
            failure("set(int, T) method is not correct");
            return failed;
        }
        System.out.println("Passed set method test");
        
        // test add(int, T method)
        asl.add(2,"This is an inserted third test entry");
        if(!asl.get(0).equals("This is the first test entry") ||
           !asl.get(1).equals("This is the replaced second test entry") ||
           !asl.get(2).equals("This is an inserted third test entry") ||
           !asl.get(3).equals("This is the third test entry") ||
           !asl.get(4).equals("This is the replaced fourth test entry") ||
           !asl.get(5).equals("This is the fifth test entry"))
        {
            failure("add(int, T) method is not correct");
            return failed;
        }
        if(asl.size() != 6)
        {
            failure("size instance variable not incremented in add(int, T) method");
            return failed;
        }
        System.out.println("Passed add(int, T) method test");
        
        // test remove(int) method
        if(asl.remove(3) == null || 
          !asl.remove(2).equals("This is an inserted third test entry") ||
          !asl.remove(0).equals("This is the first test entry"))
        {
            failure("remove(int) method is not correct");
            return failed;
        } 
        if(!asl.get(0).equals("This is the replaced second test entry") ||
           !asl.get(1).equals("This is the replaced fourth test entry") ||
           !asl.get(2).equals("This is the fifth test entry") ||
           asl.get(3) != null ||
           asl.get(4) != null)
        {
            failure("remove(int) method is not correct");
            return failed;
        }
        if(asl.size() != 3)
        {
            failure("size instance variable not decremented in remove(int) method");
            return failed;
        }
        System.out.println("Passed remove(int) method test");

        if(!asl.remove("This is the replaced second test entry") ||
           !asl.remove("This is the fifth test entry") ||
            asl.remove("m,zxn,mnm,n"))
        {
            failure("remove(T) method is not correct");
            return failed;
        }
            
        if(!asl.get(0).equals("This is the replaced fourth test entry") ||
            asl.get(1) != null)
        {
            failure("remove(T) method is not correct");
            return failed;
        }
        if(asl.size() != 1)
        {
            failure("size instance variable not decremented in remove(T) method");
            return failed;
        }
        System.out.println("Passed remove(T) method test");
        
        // test clear method
        asl.clear();
        for(int i = 0; i < asl.size(); i++)
            if(asl.get(i) != null)
            {
                failure("incorrect clear method");
                return failed;
            }
            
        if(asl.size() != 0)
        {
            failure("incorrect clear method");
            return failed;
        }
        // continued validation of isFull method
        if(asl.isFull())
        {
            failure("isFull method is not correct");
            return failed;
        }
        // continued validation of isEmpty method
        if(!asl.isEmpty())
        {
            failure("isEmpty method is not correct");
            return failed;
        }
        System.out.println("Passed clear method test");
        
        // test dynamic halving of ArrayLog
        System.out.println("\nNow testing dynamic halving of ArrayLog");
        try
        {
            Object[] log = (Object[])fieldLog.get(asl); 
            if(log.length != 4)
            {
                failure("Array did not halve in size");
                return failed;
            }
        }
        catch(IllegalAccessException e)
        {
            failure("you do not have rights to access private field data");
            return failed;
        }
        // continued validation of size method
        if(asl.size() != 0)
        {
            failure("'size' instance variable is not correct");
            return failed;
        }
        System.out.println("Passed dynamic halving of array");
        return failed;
    }
    
    private static void failure(String str)
    {
        System.out.println("*** " + str);
        failed = true;
    }
}
