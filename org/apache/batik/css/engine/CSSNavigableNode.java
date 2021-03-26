package org.apache.batik.css.engine;

import org.w3c.dom.Node;

public interface CSSNavigableNode {
  Node getCSSParentNode();
  
  Node getCSSPreviousSibling();
  
  Node getCSSNextSibling();
  
  Node getCSSFirstChild();
  
  Node getCSSLastChild();
  
  boolean isHiddenFromSelectors();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/CSSNavigableNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */