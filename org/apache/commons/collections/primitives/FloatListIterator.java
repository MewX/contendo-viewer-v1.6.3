package org.apache.commons.collections.primitives;

public interface FloatListIterator extends FloatIterator {
  void add(float paramFloat);
  
  boolean hasNext();
  
  boolean hasPrevious();
  
  float next();
  
  int nextIndex();
  
  float previous();
  
  int previousIndex();
  
  void remove();
  
  void set(float paramFloat);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/FloatListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */