package org.w3c.dom.ls;

import org.w3c.dom.DOMException;

public interface DOMImplementationLS {
  public static final short MODE_SYNCHRONOUS = 1;
  
  public static final short MODE_ASYNCHRONOUS = 2;
  
  LSParser createLSParser(short paramShort, String paramString) throws DOMException;
  
  LSSerializer createLSSerializer();
  
  LSInput createLSInput();
  
  LSOutput createLSOutput();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/ls/DOMImplementationLS.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */