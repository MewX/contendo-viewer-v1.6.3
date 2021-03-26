package org.xml.sax.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public final class XMLReaderFactory {
  private static final String b = "org.xml.sax.driver";
  
  private static final int c = 80;
  
  static Class a;
  
  public static XMLReader createXMLReader() throws SAXException {
    String str = null;
    ClassLoader classLoader = NewInstance.a();
    try {
      str = SecuritySupport.a("org.xml.sax.driver");
    } catch (Exception exception) {}
    if (str == null || str.length() == 0) {
      String str1 = "META-INF/services/org.xml.sax.driver";
      InputStream inputStream = null;
      str = null;
      ClassLoader classLoader1 = SecuritySupport.a();
      if (classLoader1 != null) {
        inputStream = SecuritySupport.a(classLoader1, str1);
        if (inputStream == null) {
          classLoader1 = ((a == null) ? (a = a("org.xml.sax.helpers.XMLReaderFactory")) : a).getClassLoader();
          inputStream = SecuritySupport.a(classLoader1, str1);
        } 
      } else {
        classLoader1 = ((a == null) ? (a = a("org.xml.sax.helpers.XMLReaderFactory")) : a).getClassLoader();
        inputStream = SecuritySupport.a(classLoader1, str1);
      } 
      if (inputStream != null) {
        BufferedReader bufferedReader;
        try {
          bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 80);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
          bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 80);
        } 
        try {
          str = bufferedReader.readLine();
        } catch (Exception exception) {
        
        } finally {
          try {
            bufferedReader.close();
          } catch (IOException iOException) {}
        } 
      } 
    } 
    if (str == null)
      str = "org.apache.xerces.parsers.SAXParser"; 
    if (str != null)
      return a(classLoader, str); 
    try {
      return new ParserAdapter(ParserFactory.makeParser());
    } catch (Exception exception) {
      throw new SAXException("Can't create default XMLReader; is system property org.xml.sax.driver set?");
    } 
  }
  
  public static XMLReader createXMLReader(String paramString) throws SAXException {
    return a(NewInstance.a(), paramString);
  }
  
  private static XMLReader a(ClassLoader paramClassLoader, String paramString) throws SAXException {
    try {
      return (XMLReader)NewInstance.a(paramClassLoader, paramString);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new SAXException("SAX2 driver class " + paramString + " not found", classNotFoundException);
    } catch (IllegalAccessException illegalAccessException) {
      throw new SAXException("SAX2 driver class " + paramString + " found but cannot be loaded", illegalAccessException);
    } catch (InstantiationException instantiationException) {
      throw new SAXException("SAX2 driver class " + paramString + " loaded but cannot be instantiated (no empty public constructor?)", instantiationException);
    } catch (ClassCastException classCastException) {
      throw new SAXException("SAX2 driver class " + paramString + " does not implement XMLReader", classCastException);
    } 
  }
  
  static Class a(String paramString) {
    try {
      return Class.forName(paramString);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new NoClassDefFoundError(classNotFoundException.getMessage());
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/helpers/XMLReaderFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */