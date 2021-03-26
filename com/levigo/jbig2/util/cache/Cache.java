package com.levigo.jbig2.util.cache;

public interface Cache {
  Object put(Object paramObject1, Object paramObject2, int paramInt);
  
  Object get(Object paramObject);
  
  void clear();
  
  Object remove(Object paramObject);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/util/cache/Cache.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */