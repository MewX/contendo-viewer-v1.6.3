package org.apache.commons.collections;

import java.util.Map;

public interface BoundedMap extends Map {
  boolean isFull();
  
  int maxSize();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/BoundedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */