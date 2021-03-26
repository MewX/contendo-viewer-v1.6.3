package org.w3c.dom.html;

import org.w3c.dom.Node;

public interface HTMLCollection {
  int getLength();
  
  Node item(int paramInt);
  
  Node namedItem(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/html/HTMLCollection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */