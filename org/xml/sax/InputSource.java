package org.xml.sax;

import java.io.InputStream;
import java.io.Reader;

public class InputSource {
  private String a;
  
  private String b;
  
  private InputStream c;
  
  private String d;
  
  private Reader e;
  
  public InputSource() {}
  
  public InputSource(String paramString) {
    setSystemId(paramString);
  }
  
  public InputSource(InputStream paramInputStream) {
    setByteStream(paramInputStream);
  }
  
  public InputSource(Reader paramReader) {
    setCharacterStream(paramReader);
  }
  
  public void setPublicId(String paramString) {
    this.a = paramString;
  }
  
  public String getPublicId() {
    return this.a;
  }
  
  public void setSystemId(String paramString) {
    this.b = paramString;
  }
  
  public String getSystemId() {
    return this.b;
  }
  
  public void setByteStream(InputStream paramInputStream) {
    this.c = paramInputStream;
  }
  
  public InputStream getByteStream() {
    return this.c;
  }
  
  public void setEncoding(String paramString) {
    this.d = paramString;
  }
  
  public String getEncoding() {
    return this.d;
  }
  
  public void setCharacterStream(Reader paramReader) {
    this.e = paramReader;
  }
  
  public Reader getCharacterStream() {
    return this.e;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/InputSource.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */