package org.apache.commons.collections.primitives;

public interface FloatCollection {
  boolean add(float paramFloat);
  
  boolean addAll(FloatCollection paramFloatCollection);
  
  void clear();
  
  boolean contains(float paramFloat);
  
  boolean containsAll(FloatCollection paramFloatCollection);
  
  boolean isEmpty();
  
  FloatIterator iterator();
  
  boolean removeAll(FloatCollection paramFloatCollection);
  
  boolean removeElement(float paramFloat);
  
  boolean retainAll(FloatCollection paramFloatCollection);
  
  int size();
  
  float[] toArray();
  
  float[] toArray(float[] paramArrayOffloat);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/FloatCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */