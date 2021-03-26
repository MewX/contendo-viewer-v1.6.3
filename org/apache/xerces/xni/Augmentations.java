package org.apache.xerces.xni;

import java.util.Enumeration;

public interface Augmentations {
  Object putItem(String paramString, Object paramObject);
  
  Object getItem(String paramString);
  
  Object removeItem(String paramString);
  
  Enumeration keys();
  
  void removeAllItems();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xni/Augmentations.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */