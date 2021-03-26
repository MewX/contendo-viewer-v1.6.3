package org.apache.commons.collections;

public interface OrderedBidiMap extends BidiMap, OrderedMap {
  BidiMap inverseBidiMap();
  
  OrderedBidiMap inverseOrderedBidiMap();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/OrderedBidiMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */