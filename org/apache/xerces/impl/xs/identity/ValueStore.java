package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.xs.ShortList;

public interface ValueStore {
  void addValue(Field paramField, boolean paramBoolean, Object paramObject, short paramShort, ShortList paramShortList);
  
  void reportError(String paramString, Object[] paramArrayOfObject);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/identity/ValueStore.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */