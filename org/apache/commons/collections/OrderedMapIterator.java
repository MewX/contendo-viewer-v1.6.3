package org.apache.commons.collections;

public interface OrderedMapIterator extends MapIterator, OrderedIterator {
  boolean hasPrevious();
  
  Object previous();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/OrderedMapIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */