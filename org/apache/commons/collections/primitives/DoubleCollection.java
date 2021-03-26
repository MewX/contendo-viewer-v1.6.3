package org.apache.commons.collections.primitives;

public interface DoubleCollection {
  boolean add(double paramDouble);
  
  boolean addAll(DoubleCollection paramDoubleCollection);
  
  void clear();
  
  boolean contains(double paramDouble);
  
  boolean containsAll(DoubleCollection paramDoubleCollection);
  
  boolean isEmpty();
  
  DoubleIterator iterator();
  
  boolean removeAll(DoubleCollection paramDoubleCollection);
  
  boolean removeElement(double paramDouble);
  
  boolean retainAll(DoubleCollection paramDoubleCollection);
  
  int size();
  
  double[] toArray();
  
  double[] toArray(double[] paramArrayOfdouble);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/DoubleCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */