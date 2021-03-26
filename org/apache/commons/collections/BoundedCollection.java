package org.apache.commons.collections;

import java.util.Collection;

public interface BoundedCollection extends Collection {
  boolean isFull();
  
  int maxSize();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/BoundedCollection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */