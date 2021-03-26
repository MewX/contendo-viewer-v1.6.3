package org.apache.commons.collections.primitives;

public interface DoubleListIterator extends DoubleIterator {
  void add(double paramDouble);
  
  boolean hasNext();
  
  boolean hasPrevious();
  
  double next();
  
  int nextIndex();
  
  double previous();
  
  int previousIndex();
  
  void remove();
  
  void set(double paramDouble);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/DoubleListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */