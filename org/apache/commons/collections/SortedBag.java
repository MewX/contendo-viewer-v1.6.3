package org.apache.commons.collections;

import java.util.Comparator;

public interface SortedBag extends Bag {
  Comparator comparator();
  
  Object first();
  
  Object last();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/SortedBag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */