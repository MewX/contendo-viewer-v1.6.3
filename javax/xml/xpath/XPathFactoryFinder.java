package javax.xml.xpath;

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

final class XPathFactoryFinder {
  private static boolean b = false;
  
  private static final int c = 80;
  
  private static Properties d = new Properties();
  
  private static boolean e = true;
  
  private final ClassLoader f;
  
  private static final Class g = (a == null) ? (a = b("javax.xml.xpath.XPathFactory")) : a;
  
  private static final String h = "META-INF/services/" + g.getName();
  
  static Class a;
  
  private static void c(String paramString) {
    if (b)
      System.err.println("JAXP: " + paramString); 
  }
  
  public XPathFactoryFinder(ClassLoader paramClassLoader) {
    this.f = paramClassLoader;
    if (b)
      b(); 
  }
  
  private void b() {
    try {
      if (this.f == SecuritySupport.a()) {
        c("using thread context class loader (" + this.f + ") for search");
        return;
      } 
    } catch (VirtualMachineError virtualMachineError) {
      throw virtualMachineError;
    } catch (ThreadDeath threadDeath) {
      throw threadDeath;
    } catch (Throwable throwable) {}
    if (this.f == ClassLoader.getSystemClassLoader()) {
      c("using system class loader (" + this.f + ") for search");
      return;
    } 
    c("using class loader (" + this.f + ") for search");
  }
  
  public XPathFactory newFactory(String paramString) {
    if (paramString == null)
      throw new NullPointerException(); 
    XPathFactory xPathFactory = d(paramString);
    if (b)
      if (xPathFactory != null) {
        c("factory '" + xPathFactory.getClass().getName() + "' was found for " + paramString);
      } else {
        c("unable to find a factory for " + paramString);
      }  
    return xPathFactory;
  }
  
  private XPathFactory d(String paramString) {
    String str1 = g.getName() + ":" + paramString;
    try {
      if (b)
        c("Looking up system property '" + str1 + "'"); 
      String str = SecuritySupport.a(str1);
      if (str != null && str.length() > 0) {
        if (b)
          c("The value is '" + str + "'"); 
        XPathFactory xPathFactory = a(str);
        if (xPathFactory != null)
          return xPathFactory; 
      } else if (b) {
        c("The property is undefined.");
      } 
    } catch (VirtualMachineError virtualMachineError) {
      throw virtualMachineError;
    } catch (ThreadDeath threadDeath) {
      throw threadDeath;
    } catch (Throwable throwable) {
      if (b) {
        c("failed to look up system property '" + str1 + "'");
        throwable.printStackTrace();
      } 
    } 
    String str2 = SecuritySupport.a("java.home");
    String str3 = str2 + File.separator + "lib" + File.separator + "jaxp.properties";
    String str4 = null;
    try {
      if (e)
        synchronized (d) {
          if (e) {
            File file = new File(str3);
            e = false;
            if (SecuritySupport.b(file)) {
              if (b)
                c("Read properties file " + file); 
              d.load(SecuritySupport.a(file));
            } 
          } 
        }  
      str4 = d.getProperty(str1);
      if (b)
        c("found " + str4 + " in $java.home/jaxp.properties"); 
      if (str4 != null) {
        XPathFactory xPathFactory = a(str4);
        if (xPathFactory != null)
          return xPathFactory; 
      } 
    } catch (Exception exception) {
      if (b)
        exception.printStackTrace(); 
    } 
    Iterator iterator = c();
    while (iterator.hasNext()) {
      URL uRL = iterator.next();
      if (b)
        c("looking into " + uRL); 
      try {
        XPathFactory xPathFactory = a(paramString, uRL.toExternalForm(), SecuritySupport.a(uRL));
        if (xPathFactory != null)
          return xPathFactory; 
      } catch (IOException iOException) {
        if (b) {
          c("failed to read " + uRL);
          iOException.printStackTrace();
        } 
      } 
    } 
    if (paramString.equals("http://java.sun.com/jaxp/xpath/dom")) {
      if (b)
        c("attempting to use the platform default W3C DOM XPath lib"); 
      return a("org.apache.xpath.jaxp.XPathFactoryImpl");
    } 
    if (b)
      c("all things were tried, but none was found. bailing out."); 
    return null;
  }
  
  XPathFactory a(String paramString) {
    try {
      Class clazz;
      if (b)
        c("instanciating " + paramString); 
      if (this.f != null) {
        clazz = this.f.loadClass(paramString);
      } else {
        clazz = Class.forName(paramString);
      } 
      if (b)
        c("loaded it from " + a(clazz)); 
      Object object = clazz.newInstance();
      if (object instanceof XPathFactory)
        return (XPathFactory)object; 
      if (b)
        c(paramString + " is not assignable to " + g.getName()); 
    } catch (VirtualMachineError virtualMachineError) {
      throw virtualMachineError;
    } catch (ThreadDeath threadDeath) {
      throw threadDeath;
    } catch (Throwable throwable) {
      if (b) {
        c("failed to instanciate " + paramString);
        throwable.printStackTrace();
      } 
    } 
    return null;
  }
  
  private XPathFactory a(String paramString1, String paramString2, InputStream paramInputStream) {
    BufferedReader bufferedReader;
    if (b)
      c("Reading " + paramString2); 
    try {
      bufferedReader = new BufferedReader(new InputStreamReader(paramInputStream, "UTF-8"), 80);
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      bufferedReader = new BufferedReader(new InputStreamReader(paramInputStream), 80);
    } 
    String str = null;
    XPathFactory xPathFactory = null;
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
          XPathFactory xPathFactory1 = a(str);
          if (xPathFactory1.isObjectModelSupported(paramString1)) {
            xPathFactory = xPathFactory1;
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
    return xPathFactory;
  }
  
  private Iterator c() {
    if (this.f == null)
      return new SingleIterator(this) {
          static Class a;
          
          private final XPathFactoryFinder b;
          
          protected Object a() {
            ClassLoader classLoader = ((a == null) ? (a = a("javax.xml.xpath.XPathFactoryFinder")) : a).getClassLoader();
            return SecuritySupport.a(classLoader, XPathFactoryFinder.a());
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
      Enumeration enumeration = SecuritySupport.b(this.f, h);
      if (b && !enumeration.hasMoreElements())
        c("no " + h + " file was found"); 
      return new Iterator(this, enumeration) {
          private final Enumeration a;
          
          private final XPathFactoryFinder b;
          
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
      if (b) {
        c("failed to enumerate resources " + h);
        iOException.printStackTrace();
      } 
      return (new ArrayList()).iterator();
    } 
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
    return h;
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
      b = (str != null && !"false".equals(str));
    } catch (Exception exception) {
      b = false;
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/xpath/XPathFactoryFinder.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */