package org.apache.commons.collections;

public interface OrderedMap extends IterableMap {
  OrderedMapIterator orderedMapIterator();
  
  Object firstKey();
  
  Object lastKey();
  
  Object nextKey(Object paramObject);
  
  Object previousKey(Object paramObject);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/OrderedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */