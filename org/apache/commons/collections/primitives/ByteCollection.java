package org.apache.commons.collections.primitives;

public interface ByteCollection {
  boolean add(byte paramByte);
  
  boolean addAll(ByteCollection paramByteCollection);
  
  void clear();
  
  boolean contains(byte paramByte);
  
  boolean containsAll(ByteCollection paramByteCollection);
  
  boolean isEmpty();
  
  ByteIterator iterator();
  
  boolean removeAll(ByteCollection paramByteCollection);
  
  boolean removeElement(byte paramByte);
  
  boolean retainAll(ByteCollection paramByteCollection);
  
  int size();
  
  byte[] toArray();
  
  byte[] toArray(byte[] paramArrayOfbyte);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/ByteCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */