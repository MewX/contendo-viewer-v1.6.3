package org.apache.commons.collections.primitives;

public interface BooleanList extends BooleanCollection {
  boolean add(boolean paramBoolean);
  
  void add(int paramInt, boolean paramBoolean);
  
  boolean addAll(int paramInt, BooleanCollection paramBooleanCollection);
  
  boolean equals(Object paramObject);
  
  boolean get(int paramInt);
  
  int hashCode();
  
  int indexOf(boolean paramBoolean);
  
  BooleanIterator iterator();
  
  int lastIndexOf(boolean paramBoolean);
  
  BooleanListIterator listIterator();
  
  BooleanListIterator listIterator(int paramInt);
  
  boolean removeElementAt(int paramInt);
  
  boolean set(int paramInt, boolean paramBoolean);
  
  BooleanList subList(int paramInt1, int paramInt2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/BooleanList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */