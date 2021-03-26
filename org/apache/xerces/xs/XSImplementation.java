package org.apache.xerces.xs;

import org.w3c.dom.ls.LSInput;

public interface XSImplementation {
  StringList getRecognizedVersions();
  
  XSLoader createXSLoader(StringList paramStringList) throws XSException;
  
  StringList createStringList(String[] paramArrayOfString);
  
  LSInputList createLSInputList(LSInput[] paramArrayOfLSInput);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/XSImplementation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */