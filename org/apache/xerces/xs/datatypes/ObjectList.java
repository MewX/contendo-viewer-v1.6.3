package org.apache.xerces.xs.datatypes;

import java.util.List;

public interface ObjectList extends List {
  int getLength();
  
  boolean contains(Object paramObject);
  
  Object item(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/datatypes/ObjectList.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */