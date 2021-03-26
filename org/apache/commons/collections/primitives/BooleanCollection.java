package org.apache.commons.collections.primitives;

public interface BooleanCollection {
  boolean add(boolean paramBoolean);
  
  boolean addAll(BooleanCollection paramBooleanCollection);
  
  void clear();
  
  boolean contains(boolean paramBoolean);
  
  boolean containsAll(BooleanCollection paramBooleanCollection);
  
  boolean isEmpty();
  
  BooleanIterator iterator();
  
  boolean removeAll(BooleanCollection paramBooleanCollection);
  
  boolean removeElement(boolean paramBoolean);
  
  boolean retainAll(BooleanCollection paramBooleanCollection);
  
  int size();
  
  boolean[] toArray();
  
  boolean[] toArray(boolean[] paramArrayOfboolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/BooleanCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */