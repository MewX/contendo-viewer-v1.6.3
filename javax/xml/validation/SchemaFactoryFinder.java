package javax.xml.validation;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;

final class SchemaFactoryFinder {
  private static final String b = "http://www.w3.org/XML/XMLSchema/v1.0";
  
  private static final String c = "http://www.w3.org/XML/XMLSchema/v1.1";
  
  private static boolean d = false;
  
  private static Properties e = new Properties();
  
  private static boolean f = true;
  
  private static final int g = 80;
  
  private final ClassLoader h;
  
  private static final Class i = (a == null) ? (a = b("javax.xml.validation.SchemaFactory")) : a;
  
  private static final String j = "META-INF/services/" + i.getName();
  
  static Class a;
  
  private static void c(String paramString) {
    if (d)
      System.err.println("JAXP: " + paramString); 
  }
  
  public SchemaFactoryFinder(ClassLoader paramClassLoader) {
    this.h = paramClassLoader;
    if (d)
      b(); 
  }
  
  private void b() {
    try {
      if (this.h == SecuritySupport.a()) {
        c("using thread context class loader (" + this.h + ") for search");
        return;
      } 
    } catch (VirtualMachineError virtualMachineError) {
      throw virtualMachineError;
    } catch (ThreadDeath threadDeath) {
      throw threadDeath;
    } catch (Throwable throwable) {}
    if (this.h == ClassLoader.getSystemClassLoader()) {
      c("using system class loader (" + this.h + ") for search");
      return;
    } 
    c("using class loader (" + this.h + ") for search");
  }
  
  public SchemaFactory newFactory(String paramString) {
    if (paramString == null)
      throw new NullPointerException(); 
    SchemaFactory schemaFactory = d(paramString);
    if (d)
      if (schemaFactory != null) {
        c("factory '" + schemaFactory.getClass().getName() + "' was found for " + paramString);
      } else {
        c("unable to find a factory for " + paramString);
      }  
    return schemaFactory;
  }
  
  private SchemaFactory d(String paramString) {
    String str1 = i.getName() + ":" + paramString;
    try {
      if (d)
        c("Looking up system property '" + str1 + "'"); 
      String str = SecuritySupport.a(str1);
      if (str != null && str.length() > 0) {
        if (d)
          c("The value is '" + str + "'"); 
        SchemaFactory schemaFactory = a(str);
        if (schemaFactory != null)
          return schemaFactory; 
      } else if (d) {
        c("The property is undefined.");
      } 
    } catch (VirtualMachineError virtualMachineError) {
      throw virtualMachineError;
    } catch (ThreadDeath threadDeath) {
      throw threadDeath;
    } catch (Throwable throwable) {
      if (d) {
        c("failed to look up system property '" + str1 + "'");
        throwable.printStackTrace();
      } 
    } 
    String str2 = SecuritySupport.a("java.home");
    String str3 = str2 + File.separator + "lib" + File.separator + "jaxp.properties";
    String str4 = null;
    try {
      if (f)
        synchronized (e) {
          if (f) {
            File file = new File(str3);
            f = false;
            if (SecuritySupport.b(file)) {
              if (d)
                c("Read properties file " + file); 
              e.load(SecuritySupport.a(file));
            } 
          } 
        }  
      str4 = e.getProperty(str1);
      if (d)
        c("found " + str4 + " in $java.home/jaxp.properties"); 
      if (str4 != null) {
        SchemaFactory schemaFactory = a(str4);
        if (schemaFactory != null)
          return schemaFactory; 
      } 
    } catch (Exception exception) {
      if (d)
        exception.printStackTrace(); 
    } 
    Iterator iterator = c();
    while (iterator.hasNext()) {
      URL uRL = iterator.next();
      if (d)
        c("looking into " + uRL); 
      try {
        SchemaFactory schemaFactory = a(paramString, uRL.toExternalForm(), SecuritySupport.a(uRL));
        if (schemaFactory != null)
          return schemaFactory; 
      } catch (IOException iOException) {
        if (d) {
          c("failed to read " + uRL);
          iOException.printStackTrace();
        } 
      } 
    } 
    if (paramString.equals("http://www.w3.org/2001/XMLSchema") || paramString.equals("http://www.w3.org/XML/XMLSchema/v1.0")) {
      if (d)
        c("attempting to use the platform default XML Schema 1.0 validator"); 
      return a("org.apache.xerces.jaxp.validation.XMLSchemaFactory");
    } 
    if (paramString.equals("http://www.w3.org/XML/XMLSchema/v1.1")) {
      if (d)
        c("attempting to use the platform default XML Schema 1.1 validator"); 
      return a("org.apache.xerces.jaxp.validation.XMLSchema11Factory");
    } 
    if (d)
      c("all things were tried, but none was found. bailing out."); 
    return null;
  }
  
  SchemaFactory a(String paramString) {
    try {
      Class clazz;
      if (d)
        c("instanciating " + paramString); 
      if (this.h != null) {
        clazz = this.h.loadClass(paramString);
      } else {
        clazz = Class.forName(paramString);
      } 
      if (d)
        c("loaded it from " + a(clazz)); 
      Object object = clazz.newInstance();
      if (object instanceof SchemaFactory)
        return (SchemaFactory)object; 
      if (d)
        c(paramString + " is not assignable to " + i.getName()); 
    } catch (VirtualMachineError virtualMachineError) {
      throw virtualMachineError;
    } catch (ThreadDeath threadDeath) {
      throw threadDeath;
    } catch (Throwable throwable) {
      c("failed to instanciate " + paramString);
      if (d)
        throwable.printStackTrace(); 
    } 
    return null;
  }
  
  private Iterator c() {
    if (this.h == null)
      return new SingleIterator(this) {
          static Class a;
          
          private final SchemaFactoryFinder b;
          
          protected Object a() {
            ClassLoader classLoader = ((a == null) ? (a = a("javax.xml.validation.SchemaFactoryFinder")) : a).getClassLoader();
            return SecuritySupport.a(classLoader, SchemaFactoryFinder.a());
          }
          
          static Class a(String param1String) {
            try {
              return Class.forName(param1String);
            } catch (ClassNotFoundException classNotFoundException) {
              throw new NoClassDefFoundError(classNotFoundException.getMessage());
            } 
          }
        }; 
    try {
      Enumeration enumeration = SecuritySupport.b(this.h, j);
      if (d && !enumeration.hasMoreElements())
        c("no " + j + " file was found"); 
      return new Iterator(this, enumeration) {
          private final Enumeration a;
          
          private final SchemaFactoryFinder b;
          
          public void remove() {
            throw new UnsupportedOperationException();
          }
          
          public boolean hasNext() {
            return this.a.hasMoreElements();
          }
          
          public Object next() {
            return this.a.nextElement();
          }
        };
    } catch (IOException iOException) {
      if (d) {
        c("failed to enumerate resources " + j);
        iOException.printStackTrace();
      } 
      return (new ArrayList()).iterator();
    } 
  }
  
  private SchemaFactory a(String paramString1, String paramString2, InputStream paramInputStream) {
    BufferedReader bufferedReader;
    if (d)
      c("Reading " + paramString2); 
    try {
      bufferedReader = new BufferedReader(new InputStreamReader(paramInputStream, "UTF-8"), 80);
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      bufferedReader = new BufferedReader(new InputStreamReader(paramInputStream), 80);
    } 
    String str = null;
    SchemaFactory schemaFactory = null;
    while (true) {
      try {
        str = bufferedReader.readLine();
      } catch (IOException iOException) {
        break;
      } 
      if (str != null) {
        int i = str.indexOf('#');
        if (i != -1)
          str = str.substring(0, i); 
        str = str.trim();
        if (str.length() == 0)
          continue; 
        try {
          SchemaFactory schemaFactory1 = a(str);
          if (schemaFactory1.isSchemaLanguageSupported(paramString1)) {
            schemaFactory = schemaFactory1;
            break;
          } 
        } catch (Exception exception) {}
        continue;
      } 
      break;
    } 
    try {
      bufferedReader.close();
    } catch (IOException iOException) {}
    return schemaFactory;
  }
  
  private static String a(Class paramClass) {
    return a(paramClass.getName(), paramClass.getClassLoader());
  }
  
  private static String a(String paramString, ClassLoader paramClassLoader) {
    String str = paramString.replace('.', '/') + ".class";
    if (paramClassLoader == null)
      paramClassLoader = ClassLoader.getSystemClassLoader(); 
    URL uRL = SecuritySupport.a(paramClassLoader, str);
    return (uRL != null) ? uRL.toString() : null;
  }
  
  static String a() {
    return j;
  }
  
  static Class b(String paramString) {
    try {
      return Class.forName(paramString);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new NoClassDefFoundError(classNotFoundException.getMessage());
    } 
  }
  
  static {
    try {
      String str = SecuritySupport.a("jaxp.debug");
      d = (str != null && !"false".equals(str));
    } catch (Exception exception) {
      d = false;
    } 
  }
  
  private static abstract class SingleIterator implements Iterator {
    private boolean a = false;
    
    private SingleIterator() {}
    
    public final void remove() {
      throw new UnsupportedOperationException();
    }
    
    public final boolean hasNext() {
      return !this.a;
    }
    
    public final Object next() {
      if (this.a)
        throw new NoSuchElementException(); 
      this.a = true;
      return a();
    }
    
    protected abstract Object a();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/validation/SchemaFactoryFinder.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */