package org.apache.commons.collections.primitives;

public interface ShortListIterator extends ShortIterator {
  void add(short paramShort);
  
  boolean hasNext();
  
  boolean hasPrevious();
  
  short next();
  
  int nextIndex();
  
  short previous();
  
  int previousIndex();
  
  void remove();
  
  void set(short paramShort);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/ShortListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */