package org.w3c.dom.bootstrap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.StringTokenizer;
import java.util.Vector;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DOMImplementationList;
import org.w3c.dom.DOMImplementationSource;

public final class DOMImplementationRegistry {
  public static final String PROPERTY = "org.w3c.dom.DOMImplementationSourceList";
  
  private static final int b = 80;
  
  private static final String c = "org.apache.xerces.dom.DOMXSImplementationSourceImpl";
  
  private Vector d;
  
  static Class a;
  
  private DOMImplementationRegistry(Vector paramVector) {
    this.d = paramVector;
  }
  
  public static DOMImplementationRegistry newInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ClassCastException {
    Vector vector = new Vector();
    ClassLoader classLoader = a();
    String str = b("org.w3c.dom.DOMImplementationSourceList");
    if (str == null || str.length() == 0)
      str = a(classLoader); 
    if (str == null)
      str = "org.apache.xerces.dom.DOMXSImplementationSourceImpl"; 
    if (str != null) {
      StringTokenizer stringTokenizer = new StringTokenizer(str);
      while (stringTokenizer.hasMoreTokens()) {
        String str1 = stringTokenizer.nextToken();
        Class clazz = null;
        if (classLoader != null) {
          clazz = classLoader.loadClass(str1);
        } else {
          clazz = Class.forName(str1);
        } 
        DOMImplementationSource dOMImplementationSource = (DOMImplementationSource)clazz.newInstance();
        vector.addElement(dOMImplementationSource);
      } 
    } 
    return new DOMImplementationRegistry(vector);
  }
  
  public DOMImplementation getDOMImplementation(String paramString) {
    int i = this.d.size();
    Object object = null;
    for (byte b = 0; b < i; b++) {
      DOMImplementationSource dOMImplementationSource = this.d.elementAt(b);
      DOMImplementation dOMImplementation = dOMImplementationSource.getDOMImplementation(paramString);
      if (dOMImplementation != null)
        return dOMImplementation; 
    } 
    return null;
  }
  
  public DOMImplementationList getDOMImplementationList(String paramString) {
    Vector vector = new Vector();
    int i = this.d.size();
    for (byte b = 0; b < i; b++) {
      DOMImplementationSource dOMImplementationSource = this.d.elementAt(b);
      DOMImplementationList dOMImplementationList = dOMImplementationSource.getDOMImplementationList(paramString);
      for (byte b1 = 0; b1 < dOMImplementationList.getLength(); b1++) {
        DOMImplementation dOMImplementation = dOMImplementationList.item(b1);
        vector.addElement(dOMImplementation);
      } 
    } 
    return new DOMImplementationList(this, vector) {
        private final Vector a;
        
        private final DOMImplementationRegistry b;
        
        public DOMImplementation item(int param1Int) {
          if (param1Int >= 0 && param1Int < this.a.size())
            try {
              return this.a.elementAt(param1Int);
            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
              return null;
            }  
          return null;
        }
        
        public int getLength() {
          return this.a.size();
        }
      };
  }
  
  public void addSource(DOMImplementationSource paramDOMImplementationSource) {
    if (paramDOMImplementationSource == null)
      throw new NullPointerException(); 
    if (!this.d.contains(paramDOMImplementationSource))
      this.d.addElement(paramDOMImplementationSource); 
  }
  
  private static ClassLoader a() {
    try {
      ClassLoader classLoader = c();
      if (classLoader != null)
        return classLoader; 
    } catch (Exception exception) {
      return ((a == null) ? (a = a("org.w3c.dom.bootstrap.DOMImplementationRegistry")) : a).getClassLoader();
    } 
    return ((a == null) ? (a = a("org.w3c.dom.bootstrap.DOMImplementationRegistry")) : a).getClassLoader();
  }
  
  private static String a(ClassLoader paramClassLoader) {
    String str = "META-INF/services/org.w3c.dom.DOMImplementationSourceList";
    try {
      InputStream inputStream = a(paramClassLoader, str);
      if (inputStream != null) {
        BufferedReader bufferedReader;
        try {
          bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 80);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
          bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 80);
        } 
        String str1 = null;
        try {
          str1 = bufferedReader.readLine();
        } finally {
          bufferedReader.close();
        } 
        if (str1 != null && str1.length() > 0)
          return str1; 
      } 
    } catch (Exception exception) {
      return null;
    } 
    return null;
  }
  
  private static boolean b() {
    try {
      Class clazz = Class.forName("java.security.AccessController");
      return false;
    } catch (Exception exception) {
      return true;
    } 
  }
  
  private static ClassLoader c() {
    return b() ? null : AccessController.<ClassLoader>doPrivileged(new PrivilegedAction() {
          public Object run() {
            ClassLoader classLoader = null;
            try {
              classLoader = Thread.currentThread().getContextClassLoader();
            } catch (SecurityException securityException) {}
            return classLoader;
          }
        });
  }
  
  private static String b(String paramString) {
    return b() ? System.getProperty(paramString) : AccessController.<String>doPrivileged(new PrivilegedAction(paramString) {
          private final String a;
          
          public Object run() {
            return System.getProperty(this.a);
          }
        });
  }
  
  private static InputStream a(ClassLoader paramClassLoader, String paramString) {
    if (b()) {
      InputStream inputStream;
      if (paramClassLoader == null) {
        inputStream = ClassLoader.getSystemResourceAsStream(paramString);
      } else {
        inputStream = paramClassLoader.getResourceAsStream(paramString);
      } 
      return inputStream;
    } 
    return AccessController.<InputStream>doPrivileged(new PrivilegedAction(paramClassLoader, paramString) {
          private final ClassLoader a;
          
          private final String b;
          
          public Object run() {
            InputStream inputStream;
            if (this.a == null) {
              inputStream = ClassLoader.getSystemResourceAsStream(this.b);
            } else {
              inputStream = this.a.getResourceAsStream(this.b);
            } 
            return inputStream;
          }
        });
  }
  
  static Class a(String paramString) {
    try {
      return Class.forName(paramString);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new NoClassDefFoundError(classNotFoundException.getMessage());
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/bootstrap/DOMImplementationRegistry.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */