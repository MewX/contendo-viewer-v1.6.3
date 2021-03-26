package org.w3c.dom.ls;

import java.io.OutputStream;
import java.io.Writer;

public interface LSOutput {
  Writer getCharacterStream();
  
  void setCharacterStream(Writer paramWriter);
  
  OutputStream getByteStream();
  
  void setByteStream(OutputStream paramOutputStream);
  
  String getSystemId();
  
  void setSystemId(String paramString);
  
  String getEncoding();
  
  void setEncoding(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/ls/LSOutput.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */