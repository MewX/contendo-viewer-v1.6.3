package org.apache.commons.collections.primitives;

public interface ShortList extends ShortCollection {
  boolean add(short paramShort);
  
  void add(int paramInt, short paramShort);
  
  boolean addAll(int paramInt, ShortCollection paramShortCollection);
  
  boolean equals(Object paramObject);
  
  short get(int paramInt);
  
  int hashCode();
  
  int indexOf(short paramShort);
  
  ShortIterator iterator();
  
  int lastIndexOf(short paramShort);
  
  ShortListIterator listIterator();
  
  ShortListIterator listIterator(int paramInt);
  
  short removeElementAt(int paramInt);
  
  short set(int paramInt, short paramShort);
  
  ShortList subList(int paramInt1, int paramInt2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/ShortList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */