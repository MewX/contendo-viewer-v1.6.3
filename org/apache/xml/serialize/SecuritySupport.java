package org.apache.xml.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

final class SecuritySupport {
  static ClassLoader getContextClassLoader() {
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
  
  static ClassLoader getSystemClassLoader() {
    return AccessController.<ClassLoader>doPrivileged(new PrivilegedAction() {
          public Object run() {
            ClassLoader classLoader = null;
            try {
              classLoader = ClassLoader.getSystemClassLoader();
            } catch (SecurityException securityException) {}
            return classLoader;
          }
        });
  }
  
  static ClassLoader getParentClassLoader(ClassLoader paramClassLoader) {
    return AccessController.<ClassLoader>doPrivileged(new PrivilegedAction(paramClassLoader) {
          private final ClassLoader val$cl;
          
          public Object run() {
            ClassLoader classLoader = null;
            try {
              classLoader = this.val$cl.getParent();
            } catch (SecurityException securityException) {}
            return (classLoader == this.val$cl) ? null : classLoader;
          }
        });
  }
  
  static String getSystemProperty(String paramString) {
    return AccessController.<String>doPrivileged(new PrivilegedAction(paramString) {
          private final String val$propName;
          
          public Object run() {
            return System.getProperty(this.val$propName);
          }
        });
  }
  
  static FileInputStream getFileInputStream(File paramFile) throws FileNotFoundException {
    try {
      return AccessController.<FileInputStream>doPrivileged(new PrivilegedExceptionAction(paramFile) {
            private final File val$file;
            
            public Object run() throws FileNotFoundException {
              return new FileInputStream(this.val$file);
            }
          });
    } catch (PrivilegedActionException privilegedActionException) {
      throw (FileNotFoundException)privilegedActionException.getException();
    } 
  }
  
  static InputStream getResourceAsStream(ClassLoader paramClassLoader, String paramString) {
    return AccessController.<InputStream>doPrivileged(new PrivilegedAction(paramClassLoader, paramString) {
          private final ClassLoader val$cl;
          
          private final String val$name;
          
          public Object run() {
            InputStream inputStream;
            if (this.val$cl == null) {
              inputStream = ClassLoader.getSystemResourceAsStream(this.val$name);
            } else {
              inputStream = this.val$cl.getResourceAsStream(this.val$name);
            } 
            return inputStream;
          }
        });
  }
  
  static boolean getFileExists(File paramFile) {
    return ((Boolean)AccessController.<Boolean>doPrivileged(new PrivilegedAction(paramFile) {
          private final File val$f;
          
          public Object run() {
            return this.val$f.exists() ? Boolean.TRUE : Boolean.FALSE;
          }
        })).booleanValue();
  }
  
  static long getLastModified(File paramFile) {
    return ((Long)AccessController.<Long>doPrivileged(new PrivilegedAction(paramFile) {
          private final File val$f;
          
          public Object run() {
            return new Long(this.val$f.lastModified());
          }
        })).longValue();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serialize/SecuritySupport.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */