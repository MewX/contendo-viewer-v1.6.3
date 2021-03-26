package org.apache.commons.collections.primitives;

public interface CharCollection {
  boolean add(char paramChar);
  
  boolean addAll(CharCollection paramCharCollection);
  
  void clear();
  
  boolean contains(char paramChar);
  
  boolean containsAll(CharCollection paramCharCollection);
  
  boolean isEmpty();
  
  CharIterator iterator();
  
  boolean removeAll(CharCollection paramCharCollection);
  
  boolean removeElement(char paramChar);
  
  boolean retainAll(CharCollection paramCharCollection);
  
  int size();
  
  char[] toArray();
  
  char[] toArray(char[] paramArrayOfchar);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/CharCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */