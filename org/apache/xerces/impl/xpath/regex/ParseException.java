package org.apache.xerces.impl.xpath.regex;

public class ParseException extends RuntimeException {
  static final long serialVersionUID = -7012400318097691370L;
  
  final int location;
  
  public ParseException(String paramString, int paramInt) {
    super(paramString);
    this.location = paramInt;
  }
  
  public int getLocation() {
    return this.location;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xpath/regex/ParseException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */