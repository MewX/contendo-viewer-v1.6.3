package javax.xml.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

final class FactoryFinder {
  private static boolean b = false;
  
  private static Properties c = new Properties();
  
  private static boolean d = true;
  
  private static final int e = 80;
  
  static Class a;
  
  private static void b(String paramString) {
    if (b)
      System.err.println("JAXP: " + paramString); 
  }
  
  private static Object a(String paramString, ClassLoader paramClassLoader, boolean paramBoolean) throws ConfigurationError {
    try {
      Class clazz;
      if (paramClassLoader == null) {
        clazz = Class.forName(paramString);
      } else {
        try {
          clazz = paramClassLoader.loadClass(paramString);
        } catch (ClassNotFoundException classNotFoundException) {
          if (paramBoolean) {
            paramClassLoader = ((a == null) ? (a = a("javax.xml.stream.FactoryFinder")) : a).getClassLoader();
            if (paramClassLoader != null) {
              clazz = paramClassLoader.loadClass(paramString);
            } else {
              clazz = Class.forName(paramString);
            } 
          } else {
            throw classNotFoundException;
          } 
        } 
      } 
      Object object = clazz.newInstance();
      if (b)
        b("created new instance of " + clazz + " using ClassLoader: " + paramClassLoader); 
      return object;
    } catch (ClassNotFoundException classNotFoundException) {
      throw new ConfigurationError("Provider " + paramString + " not found", classNotFoundException);
    } catch (Exception exception) {
      throw new ConfigurationError("Provider " + paramString + " could not be instantiated: " + exception, exception);
    } 
  }
  
  static Object a(String paramString1, String paramString2) throws ConfigurationError {
    ClassLoader classLoader = SecuritySupport.a();
    if (classLoader == null)
      classLoader = ((a == null) ? (a = a("javax.xml.stream.FactoryFinder")) : a).getClassLoader(); 
    return a(paramString1, classLoader, paramString2);
  }
  
  static Object a(String paramString1, ClassLoader paramClassLoader, String paramString2) throws ConfigurationError {
    if (b)
      b("find factoryId =" + paramString1); 
    try {
      String str = SecuritySupport.a(paramString1);
      if (str != null && str.length() > 0) {
        if (b)
          b("found system property, value=" + str); 
        return a(str, paramClassLoader, true);
      } 
    } catch (SecurityException securityException) {}
    try {
      String str1 = SecuritySupport.a("java.home");
      String str2 = str1 + File.separator + "lib" + File.separator + "stax.properties";
      String str3 = null;
      if (d)
        synchronized (c) {
          if (d) {
            File file = new File(str2);
            d = false;
            if (SecuritySupport.b(file)) {
              if (b)
                b("Read properties file " + file); 
              c.load(SecuritySupport.a(file));
            } 
          } 
        }  
      str3 = c.getProperty(paramString1);
      if (str3 != null) {
        if (b)
          b("found in $java.home/stax.properties, value=" + str3); 
        return a(str3, paramClassLoader, true);
      } 
    } catch (Exception exception) {
      if (b)
        exception.printStackTrace(); 
    } 
    Object object = c(paramString1);
    if (object != null)
      return object; 
    if (paramString2 == null)
      throw new ConfigurationError("Provider for " + paramString1 + " cannot be found", null); 
    if (b)
      b("loaded from fallback value: " + paramString2); 
    return a(paramString2, paramClassLoader, true);
  }
  
  private static Object c(String paramString) throws ConfigurationError {
    BufferedReader bufferedReader;
    String str1 = "META-INF/services/" + paramString;
    InputStream inputStream = null;
    ClassLoader classLoader = SecuritySupport.a();
    if (classLoader != null) {
      inputStream = SecuritySupport.a(classLoader, str1);
      if (inputStream == null) {
        classLoader = ((a == null) ? (a = a("javax.xml.stream.FactoryFinder")) : a).getClassLoader();
        inputStream = SecuritySupport.a(classLoader, str1);
      } 
    } else {
      classLoader = ((a == null) ? (a = a("javax.xml.stream.FactoryFinder")) : a).getClassLoader();
      inputStream = SecuritySupport.a(classLoader, str1);
    } 
    if (inputStream == null)
      return null; 
    if (b)
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
      if (b)
        b("found in resource, value=" + str2); 
      return a(str2, classLoader, false);
    } 
    return null;
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
      b = (str != null && !"false".equals(str));
    } catch (SecurityException securityException) {
      b = false;
    } 
  }
  
  static class ConfigurationError extends Error {
    private static final long a = 1L;
    
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/FactoryFinder.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */