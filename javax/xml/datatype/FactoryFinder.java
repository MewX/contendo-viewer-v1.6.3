package javax.xml.datatype;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Properties;

final class FactoryFinder {
  private static final String b = "javax.xml.datatype.FactoryFinder";
  
  private static boolean c = false;
  
  private static Properties d = new Properties();
  
  private static boolean e = true;
  
  private static final int f = 80;
  
  static Class a;
  
  private static void b(String paramString) {
    if (c)
      System.err.println("javax.xml.datatype.FactoryFinder:" + paramString); 
  }
  
  private static ClassLoader a() throws ConfigurationError {
    ClassLoader classLoader = SecuritySupport.a();
    if (c)
      b("Using context class loader: " + classLoader); 
    if (classLoader == null) {
      classLoader = ((a == null) ? (a = a("javax.xml.datatype.FactoryFinder")) : a).getClassLoader();
      if (c)
        b("Using the class loader of FactoryFinder: " + classLoader); 
    } 
    return classLoader;
  }
  
  static Object a(String paramString, ClassLoader paramClassLoader) throws ConfigurationError {
    try {
      Class clazz;
      if (paramClassLoader == null) {
        clazz = Class.forName(paramString);
      } else {
        clazz = paramClassLoader.loadClass(paramString);
      } 
      if (c)
        b("Loaded " + paramString + " from " + a(clazz)); 
      return clazz.newInstance();
    } catch (ClassNotFoundException classNotFoundException) {
      throw new ConfigurationError("Provider " + paramString + " not found", classNotFoundException);
    } catch (Exception exception) {
      throw new ConfigurationError("Provider " + paramString + " could not be instantiated: " + exception, exception);
    } 
  }
  
  static Object a(String paramString1, String paramString2) throws ConfigurationError {
    ClassLoader classLoader = a();
    try {
      String str = SecuritySupport.a(paramString1);
      if (str != null && str.length() > 0) {
        if (c)
          b("found " + str + " in the system property " + paramString1); 
        return a(str, classLoader);
      } 
    } catch (SecurityException securityException) {}
    try {
      String str1 = SecuritySupport.a("java.home");
      String str2 = str1 + File.separator + "lib" + File.separator + "jaxp.properties";
      String str3 = null;
      if (e)
        synchronized (d) {
          if (e) {
            File file = new File(str2);
            e = false;
            if (SecuritySupport.b(file)) {
              if (c)
                b("Read properties file " + file); 
              d.load(SecuritySupport.a(file));
            } 
          } 
        }  
      str3 = d.getProperty(paramString1);
      if (c)
        b("found " + str3 + " in $java.home/jaxp.properties"); 
      if (str3 != null)
        return a(str3, classLoader); 
    } catch (Exception exception) {
      if (c)
        exception.printStackTrace(); 
    } 
    Object object = c(paramString1);
    if (object != null)
      return object; 
    if (paramString2 == null)
      throw new ConfigurationError("Provider for " + paramString1 + " cannot be found", null); 
    if (c)
      b("loaded from fallback value: " + paramString2); 
    return a(paramString2, classLoader);
  }
  
  private static Object c(String paramString) throws ConfigurationError {
    BufferedReader bufferedReader;
    String str1 = "META-INF/services/" + paramString;
    InputStream inputStream = null;
    ClassLoader classLoader = SecuritySupport.a();
    if (classLoader != null) {
      inputStream = SecuritySupport.a(classLoader, str1);
      if (inputStream == null) {
        classLoader = ((a == null) ? (a = a("javax.xml.datatype.FactoryFinder")) : a).getClassLoader();
        inputStream = SecuritySupport.a(classLoader, str1);
      } 
    } else {
      classLoader = ((a == null) ? (a = a("javax.xml.datatype.FactoryFinder")) : a).getClassLoader();
      inputStream = SecuritySupport.a(classLoader, str1);
    } 
    if (inputStream == null)
      return null; 
    if (c)
      b("found jar resource=" + str1 + " using ClassLoader: " + classLoader); 
    try {
      bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 80);
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 80);
    } 
    String str2 = null;
    try {
      str2 = bufferedReader.readLine();
    } catch (IOException iOException) {
      return null;
    } finally {
      try {
        bufferedReader.close();
      } catch (IOException iOException) {}
    } 
    if (str2 != null && !"".equals(str2)) {
      if (c)
        b("found in resource, value=" + str2); 
      return a(str2, classLoader);
    } 
    return null;
  }
  
  private static String a(Class paramClass) {
    try {
      URL uRL;
      String str = paramClass.getName().replace('.', '/') + ".class";
      ClassLoader classLoader = paramClass.getClassLoader();
      if (classLoader != null) {
        uRL = classLoader.getResource(str);
      } else {
        uRL = ClassLoader.getSystemResource(str);
      } 
      if (uRL != null)
        return uRL.toString(); 
    } catch (VirtualMachineError virtualMachineError) {
      throw virtualMachineError;
    } catch (ThreadDeath threadDeath) {
      throw threadDeath;
    } catch (Throwable throwable) {
      if (c)
        throwable.printStackTrace(); 
    } 
    return "unknown location";
  }
  
  static Class a(String paramString) {
    try {
      return Class.forName(paramString);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new NoClassDefFoundError(classNotFoundException.getMessage());
    } 
  }
  
  static {
    try {
      String str = SecuritySupport.a("jaxp.debug");
      c = (str != null && !"false".equals(str));
    } catch (Exception exception) {
      c = false;
    } 
  }
  
  static class ConfigurationError extends Error {
    private static final long a = -3644413026244211347L;
    
    private Exception b;
    
    ConfigurationError(String param1String, Exception param1Exception) {
      super(param1String);
      this.b = param1Exception;
    }
    
    Exception a() {
      return this.b;
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/datatype/FactoryFinder.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */