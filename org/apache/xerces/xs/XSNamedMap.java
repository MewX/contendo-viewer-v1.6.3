package org.apache.xerces.xs;

import java.util.Map;

public interface XSNamedMap extends Map {
  int getLength();
  
  XSObject item(int paramInt);
  
  XSObject itemByName(String paramString1, String paramString2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/XSNamedMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */