package javax.xml.validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Enumeration;

final class SecuritySupport {
  static ClassLoader a() {
    return AccessController.<ClassLoader>doPrivileged(new PrivilegedAction() {
          public Object run() {
            ClassLoader classLoader = null;
            try {
              classLoader = Thread.currentThread().getContextClassLoader();
            } catch (SecurityException securityException) {}
            return classLoader;
          }
        });
  }
  
  static String a(String paramString) {
    return AccessController.<String>doPrivileged(new PrivilegedAction(paramString) {
          private final String a;
          
          public Object run() {
            return System.getProperty(this.a);
          }
        });
  }
  
  static FileInputStream a(File paramFile) throws FileNotFoundException {
    try {
      return AccessController.<FileInputStream>doPrivileged(new PrivilegedExceptionAction(paramFile) {
            private final File a;
            
            public Object run() throws FileNotFoundException {
              return new FileInputStream(this.a);
            }
          });
    } catch (PrivilegedActionException privilegedActionException) {
      throw (FileNotFoundException)privilegedActionException.getException();
    } 
  }
  
  static InputStream a(URL paramURL) throws IOException {
    try {
      return AccessController.<InputStream>doPrivileged(new PrivilegedExceptionAction(paramURL) {
            private final URL a;
            
            public Object run() throws IOException {
              return this.a.openStream();
            }
          });
    } catch (PrivilegedActionException privilegedActionException) {
      throw (IOException)privilegedActionException.getException();
    } 
  }
  
  static URL a(ClassLoader paramClassLoader, String paramString) {
    return AccessController.<URL>doPrivileged(new PrivilegedAction(paramClassLoader, paramString) {
          private final ClassLoader a;
          
          private final String b;
          
          public Object run() {
            URL uRL;
            if (this.a == null) {
              uRL = ClassLoader.getSystemResource(this.b);
            } else {
              uRL = this.a.getResource(this.b);
            } 
            return uRL;
          }
        });
  }
  
  static Enumeration b(ClassLoader paramClassLoader, String paramString) throws IOException {
    try {
      return AccessController.<Enumeration>doPrivileged(new PrivilegedExceptionAction(paramClassLoader, paramString) {
            private final ClassLoader a;
            
            private final String b;
            
            public Object run() throws IOException {
              Enumeration enumeration;
              if (this.a == null) {
                enumeration = ClassLoader.getSystemResources(this.b);
              } else {
                enumeration = this.a.getResources(this.b);
              } 
              return enumeration;
            }
          });
    } catch (PrivilegedActionException privilegedActionException) {
      throw (IOException)privilegedActionException.getException();
    } 
  }
  
  static InputStream c(ClassLoader paramClassLoader, String paramString) {
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
  
  static boolean b(File paramFile) {
    return ((Boolean)AccessController.<Boolean>doPrivileged(new PrivilegedAction(paramFile) {
          private final File a;
          
          public Object run() {
            return this.a.exists() ? Boolean.TRUE : Boolean.FALSE;
          }
        })).booleanValue();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/validation/SecuritySupport.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */