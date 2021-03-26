package org.apache.xerces.xs;

import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.ls.LSInput;

public interface XSLoader {
  DOMConfiguration getConfig();
  
  XSModel loadURIList(StringList paramStringList);
  
  XSModel loadInputList(LSInputList paramLSInputList);
  
  XSModel loadURI(String paramString);
  
  XSModel load(LSInput paramLSInput);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/XSLoader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */