package org.apache.xml.utils;

public abstract class XMLStringFactory {
  public abstract XMLString newstr(String paramString);
  
  public abstract XMLString newstr(FastStringBuffer paramFastStringBuffer, int paramInt1, int paramInt2);
  
  public abstract XMLString newstr(char[] paramArrayOfchar, int paramInt1, int paramInt2);
  
  public abstract XMLString emptystr();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/XMLStringFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */