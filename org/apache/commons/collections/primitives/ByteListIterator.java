package org.apache.commons.collections.primitives;

public interface ByteListIterator extends ByteIterator {
  void add(byte paramByte);
  
  boolean hasNext();
  
  boolean hasPrevious();
  
  byte next();
  
  int nextIndex();
  
  byte previous();
  
  int previousIndex();
  
  void remove();
  
  void set(byte paramByte);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/ByteListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */