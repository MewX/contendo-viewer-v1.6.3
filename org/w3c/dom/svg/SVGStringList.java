package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

public interface SVGStringList {
  int getNumberOfItems();
  
  void clear() throws DOMException;
  
  String initialize(String paramString) throws DOMException, SVGException;
  
  String getItem(int paramInt) throws DOMException;
  
  String insertItemBefore(String paramString, int paramInt) throws DOMException, SVGException;
  
  String replaceItem(String paramString, int paramInt) throws DOMException, SVGException;
  
  String removeItem(int paramInt) throws DOMException;
  
  String appendItem(String paramString) throws DOMException, SVGException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGStringList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */