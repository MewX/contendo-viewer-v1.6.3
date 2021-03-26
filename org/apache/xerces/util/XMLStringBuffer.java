package org.apache.xerces.util;

import org.apache.xerces.xni.XMLString;

public class XMLStringBuffer extends XMLString {
  public static final int DEFAULT_SIZE = 32;
  
  public XMLStringBuffer() {
    this(32);
  }
  
  public XMLStringBuffer(int paramInt) {
    this.ch = new char[paramInt];
  }
  
  public XMLStringBuffer(char paramChar) {
    this(1);
    append(paramChar);
  }
  
  public XMLStringBuffer(String paramString) {
    this(paramString.length());
    append(paramString);
  }
  
  public XMLStringBuffer(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    this(paramInt2);
    append(paramArrayOfchar, paramInt1, paramInt2);
  }
  
  public XMLStringBuffer(XMLString paramXMLString) {
    this(paramXMLString.length);
    append(paramXMLString);
  }
  
  public void clear() {
    this.offset = 0;
    this.length = 0;
  }
  
  public void append(char paramChar) {
    if (this.length + 1 > this.ch.length) {
      int i = this.ch.length * 2;
      if (i < this.ch.length + 32)
        i = this.ch.length + 32; 
      char[] arrayOfChar = new char[i];
      System.arraycopy(this.ch, 0, arrayOfChar, 0, this.length);
      this.ch = arrayOfChar;
    } 
    this.ch[this.length] = paramChar;
    this.length++;
  }
  
  public void append(String paramString) {
    int i = paramString.length();
    if (this.length + i > this.ch.length) {
      int j = this.ch.length * 2;
      if (j < this.length + i + 32)
        j = this.ch.length + i + 32; 
      char[] arrayOfChar = new char[j];
      System.arraycopy(this.ch, 0, arrayOfChar, 0, this.length);
      this.ch = arrayOfChar;
    } 
    paramString.getChars(0, i, this.ch, this.length);
    this.length += i;
  }
  
  public void append(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this.length + paramInt2 > this.ch.length) {
      char[] arrayOfChar = new char[this.ch.length + paramInt2 + 32];
      System.arraycopy(this.ch, 0, arrayOfChar, 0, this.length);
      this.ch = arrayOfChar;
    } 
    System.arraycopy(paramArrayOfchar, paramInt1, this.ch, this.length, paramInt2);
    this.length += paramInt2;
  }
  
  public void append(XMLString paramXMLString) {
    append(paramXMLString.ch, paramXMLString.offset, paramXMLString.length);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/util/XMLStringBuffer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */