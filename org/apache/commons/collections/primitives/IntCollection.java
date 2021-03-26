package org.apache.commons.collections.primitives;

public interface IntCollection {
  boolean add(int paramInt);
  
  boolean addAll(IntCollection paramIntCollection);
  
  void clear();
  
  boolean contains(int paramInt);
  
  boolean containsAll(IntCollection paramIntCollection);
  
  boolean isEmpty();
  
  IntIterator iterator();
  
  boolean removeAll(IntCollection paramIntCollection);
  
  boolean removeElement(int paramInt);
  
  boolean retainAll(IntCollection paramIntCollection);
  
  int size();
  
  int[] toArray();
  
  int[] toArray(int[] paramArrayOfint);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/IntCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */