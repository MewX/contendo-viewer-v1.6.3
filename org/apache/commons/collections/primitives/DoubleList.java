package org.apache.commons.collections.primitives;

public interface DoubleList extends DoubleCollection {
  boolean add(double paramDouble);
  
  void add(int paramInt, double paramDouble);
  
  boolean addAll(int paramInt, DoubleCollection paramDoubleCollection);
  
  boolean equals(Object paramObject);
  
  double get(int paramInt);
  
  int hashCode();
  
  int indexOf(double paramDouble);
  
  DoubleIterator iterator();
  
  int lastIndexOf(double paramDouble);
  
  DoubleListIterator listIterator();
  
  DoubleListIterator listIterator(int paramInt);
  
  double removeElementAt(int paramInt);
  
  double set(int paramInt, double paramDouble);
  
  DoubleList subList(int paramInt1, int paramInt2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/DoubleList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */