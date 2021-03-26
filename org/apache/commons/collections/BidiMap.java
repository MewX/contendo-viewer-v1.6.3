package org.apache.commons.collections;

public interface BidiMap extends IterableMap {
  MapIterator mapIterator();
  
  Object put(Object paramObject1, Object paramObject2);
  
  Object getKey(Object paramObject);
  
  Object removeValue(Object paramObject);
  
  BidiMap inverseBidiMap();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/BidiMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */