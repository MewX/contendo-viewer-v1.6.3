package org.apache.commons.collections.primitives;

public interface FloatList extends FloatCollection {
  boolean add(float paramFloat);
  
  void add(int paramInt, float paramFloat);
  
  boolean addAll(int paramInt, FloatCollection paramFloatCollection);
  
  boolean equals(Object paramObject);
  
  float get(int paramInt);
  
  int hashCode();
  
  int indexOf(float paramFloat);
  
  FloatIterator iterator();
  
  int lastIndexOf(float paramFloat);
  
  FloatListIterator listIterator();
  
  FloatListIterator listIterator(int paramInt);
  
  float removeElementAt(int paramInt);
  
  float set(int paramInt, float paramFloat);
  
  FloatList subList(int paramInt1, int paramInt2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/FloatList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */