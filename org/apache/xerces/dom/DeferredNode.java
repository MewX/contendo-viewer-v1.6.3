package org.apache.xerces.dom;

import org.w3c.dom.Node;

public interface DeferredNode extends Node {
  public static final short TYPE_NODE = 20;
  
  int getNodeIndex();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/DeferredNode.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */