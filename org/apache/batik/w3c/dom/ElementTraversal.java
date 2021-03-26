package org.apache.batik.w3c.dom;

import org.w3c.dom.Element;

public interface ElementTraversal {
  Element getFirstElementChild();
  
  Element getLastElementChild();
  
  Element getNextElementSibling();
  
  Element getPreviousElementSibling();
  
  int getChildElementCount();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/w3c/dom/ElementTraversal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */