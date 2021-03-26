package org.xml.sax.helpers;

import org.xml.sax.Parser;

public class ParserFactory {
  public static Parser makeParser() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NullPointerException, ClassCastException {
    String str = SecuritySupport.a("org.xml.sax.parser");
    if (str == null)
      throw new NullPointerException("No value for sax.parser property"); 
    return makeParser(str);
  }
  
  public static Parser makeParser(String paramString) throws ClassNotFoundException, IllegalAccessException, InstantiationException, ClassCastException {
    return (Parser)NewInstance.a(NewInstance.a(), paramString);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/helpers/ParserFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */