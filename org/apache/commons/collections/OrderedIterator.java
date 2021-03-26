package org.apache.commons.collections;

import java.util.Iterator;

public interface OrderedIterator extends Iterator {
  boolean hasPrevious();
  
  Object previous();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/OrderedIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */