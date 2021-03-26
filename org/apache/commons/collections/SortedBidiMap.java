package org.apache.commons.collections;

import java.util.SortedMap;

public interface SortedBidiMap extends SortedMap, OrderedBidiMap {
  BidiMap inverseBidiMap();
  
  SortedBidiMap inverseSortedBidiMap();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/SortedBidiMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */