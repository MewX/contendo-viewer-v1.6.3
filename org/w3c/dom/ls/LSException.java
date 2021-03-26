package org.w3c.dom.ls;

public class LSException extends RuntimeException {
  public short code;
  
  public static final short PARSE_ERR = 81;
  
  public static final short SERIALIZE_ERR = 82;
  
  public LSException(short paramShort, String paramString) {
    super(paramString);
    this.code = paramShort;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/ls/LSException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */