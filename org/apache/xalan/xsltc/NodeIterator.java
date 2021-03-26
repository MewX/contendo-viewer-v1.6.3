package org.apache.xalan.xsltc;

public interface NodeIterator extends Cloneable {
  public static final int END = -1;
  
  int next();
  
  NodeIterator reset();
  
  int getLast();
  
  int getPosition();
  
  void setMark();
  
  void gotoMark();
  
  NodeIterator setStartNode(int paramInt);
  
  boolean isReverse();
  
  NodeIterator cloneIterator();
  
  void setRestartable(boolean paramBoolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/NodeIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */