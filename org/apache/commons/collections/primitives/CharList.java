package org.apache.commons.collections.primitives;

public interface CharList extends CharCollection {
  boolean add(char paramChar);
  
  void add(int paramInt, char paramChar);
  
  boolean addAll(int paramInt, CharCollection paramCharCollection);
  
  boolean equals(Object paramObject);
  
  char get(int paramInt);
  
  int hashCode();
  
  int indexOf(char paramChar);
  
  CharIterator iterator();
  
  int lastIndexOf(char paramChar);
  
  CharListIterator listIterator();
  
  CharListIterator listIterator(int paramInt);
  
  char removeElementAt(int paramInt);
  
  char set(int paramInt, char paramChar);
  
  CharList subList(int paramInt1, int paramInt2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/CharList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */