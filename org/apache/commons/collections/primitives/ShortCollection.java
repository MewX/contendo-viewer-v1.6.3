package org.apache.commons.collections.primitives;

public interface ShortCollection {
  boolean add(short paramShort);
  
  boolean addAll(ShortCollection paramShortCollection);
  
  void clear();
  
  boolean contains(short paramShort);
  
  boolean containsAll(ShortCollection paramShortCollection);
  
  boolean isEmpty();
  
  ShortIterator iterator();
  
  boolean removeAll(ShortCollection paramShortCollection);
  
  boolean removeElement(short paramShort);
  
  boolean retainAll(ShortCollection paramShortCollection);
  
  int size();
  
  short[] toArray();
  
  short[] toArray(short[] paramArrayOfshort);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/ShortCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */