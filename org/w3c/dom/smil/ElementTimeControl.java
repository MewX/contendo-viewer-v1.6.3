package org.w3c.dom.smil;

import org.w3c.dom.DOMException;

public interface ElementTimeControl {
  boolean beginElement() throws DOMException;
  
  boolean beginElementAt(float paramFloat) throws DOMException;
  
  boolean endElement() throws DOMException;
  
  boolean endElementAt(float paramFloat) throws DOMException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/smil/ElementTimeControl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */