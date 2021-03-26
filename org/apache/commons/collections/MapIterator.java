package org.apache.commons.collections;

import java.util.Iterator;

public interface MapIterator extends Iterator {
  boolean hasNext();
  
  Object next();
  
  Object getKey();
  
  Object getValue();
  
  void remove();
  
  Object setValue(Object paramObject);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/MapIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */