import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * This class tests contains the common methods that will test
 * either implementation of the the Log class
 * 
 * @author Mr. Bredemeier
 * @version September 30, 2012
 */

public abstract class LogTest<T>
{
    private static Boolean failed = false;
    private static ArrayList<String> methodNames = new ArrayList<String>();
    private static ArrayLog asl;
    private static String message;
    private static String methodName;
    private static Field fieldLog, fieldName, fieldSize;
    private static Class<?> c;

    public static boolean testInterface()
    {
        // make sure that EnhancedLogInterface<T> is untouched
        try 
        {
            c = Class.forName("EnhancedLogInterface");
            Member[] methods = c.getMethods();
            for (Member method : methods)
                methodNames.add(((Method)method).toGenericString());
        }
        catch(ClassNotFoundException e)
        {
            failure("Epic Failure: missing interface EnhancedLogInterface");
            return failed;
        }

        methodName = "public abstract void EnhancedLogInterface.add(T)";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);
        
        methodName = "public abstract void EnhancedLogInterface.add(int,T)";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);
            
        methodName = "public abstract T EnhancedLogInterface.remove(int)";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);
        
        methodName = "public abstract boolean EnhancedLogInterface.remove(T)";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract T EnhancedLogInterface.get(int)";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract java.lang.String EnhancedLogInterface.toString()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract int EnhancedLogInterface.indexOf(T)";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract void EnhancedLogInterface.clear()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract java.lang.String EnhancedLogInterface.getName()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract boolean EnhancedLogInterface.contains(T)";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract boolean EnhancedLogInterface.isEmpty()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract int EnhancedLogInterface.size()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract T EnhancedLogInterface.set(int,T)";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

         methodName = "public abstract boolean EnhancedLogInterface.isFull()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        if(failed)
        {
            System.out.println("\nCorrupted interface EnhancedLogInterface");
        }
        return failed;
    }
                
    private static void failure(String str)
    {
        System.out.println("*** " + str);
        failed = true;
    }
}
