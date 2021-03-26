package org.apache.xml.dtm;

public interface DTMAxisIterator extends Cloneable {
  public static final int END = -1;
  
  int next();
  
  DTMAxisIterator reset();
  
  int getLast();
  
  int getPosition();
  
  void setMark();
  
  void gotoMark();
  
  DTMAxisIterator setStartNode(int paramInt);
  
  int getStartNode();
  
  boolean isReverse();
  
  DTMAxisIterator cloneIterator();
  
  void setRestartable(boolean paramBoolean);
  
  int getNodeByPosition(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/DTMAxisIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */