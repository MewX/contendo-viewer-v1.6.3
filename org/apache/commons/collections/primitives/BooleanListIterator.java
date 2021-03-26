package org.apache.commons.collections.primitives;

public interface BooleanListIterator extends BooleanIterator {
  void add(boolean paramBoolean);
  
  boolean hasNext();
  
  boolean hasPrevious();
  
  boolean next();
  
  int nextIndex();
  
  boolean previous();
  
  int previousIndex();
  
  void remove();
  
  void set(boolean paramBoolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/BooleanListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */