package org.apache.commons.collections;

import java.util.Collection;
import java.util.Map;

public interface MultiMap extends Map {
  Object remove(Object paramObject1, Object paramObject2);
  
  int size();
  
  Object get(Object paramObject);
  
  boolean containsValue(Object paramObject);
  
  Object put(Object paramObject1, Object paramObject2);
  
  Object remove(Object paramObject);
  
  Collection values();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/MultiMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */