package org.apache.xerces.dom3.as;

import org.w3c.dom.DOMException;

public interface ASNamedObjectMap {
  int getLength();
  
  ASObject getNamedItem(String paramString);
  
  ASObject getNamedItemNS(String paramString1, String paramString2);
  
  ASObject item(int paramInt);
  
  ASObject removeNamedItem(String paramString) throws DOMException;
  
  ASObject removeNamedItemNS(String paramString1, String paramString2) throws DOMException;
  
  ASObject setNamedItem(ASObject paramASObject) throws DOMException;
  
  ASObject setNamedItemNS(ASObject paramASObject) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom3/as/ASNamedObjectMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */