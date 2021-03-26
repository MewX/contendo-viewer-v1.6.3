package org.apache.commons.collections.primitives;

public interface ByteList extends ByteCollection {
  boolean add(byte paramByte);
  
  void add(int paramInt, byte paramByte);
  
  boolean addAll(int paramInt, ByteCollection paramByteCollection);
  
  boolean equals(Object paramObject);
  
  byte get(int paramInt);
  
  int hashCode();
  
  int indexOf(byte paramByte);
  
  ByteIterator iterator();
  
  int lastIndexOf(byte paramByte);
  
  ByteListIterator listIterator();
  
  ByteListIterator listIterator(int paramInt);
  
  byte removeElementAt(int paramInt);
  
  byte set(int paramInt, byte paramByte);
  
  ByteList subList(int paramInt1, int paramInt2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/ByteList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */