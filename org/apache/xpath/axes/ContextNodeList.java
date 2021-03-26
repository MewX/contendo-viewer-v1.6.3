package org.apache.xpath.axes;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

public interface ContextNodeList {
  Node getCurrentNode();
  
  int getCurrentPos();
  
  void reset();
  
  void setShouldCacheNodes(boolean paramBoolean);
  
  void runTo(int paramInt);
  
  void setCurrentPos(int paramInt);
  
  int size();
  
  boolean isFresh();
  
  NodeIterator cloneWithReset() throws CloneNotSupportedException;
  
  Object clone() throws CloneNotSupportedException;
  
  int getLast();
  
  void setLast(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/ContextNodeList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */