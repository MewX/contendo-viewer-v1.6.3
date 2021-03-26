package org.apache.xerces.xs;

import java.util.List;

public interface ShortList extends List {
  int getLength();
  
  boolean contains(short paramShort);
  
  short item(int paramInt) throws XSException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/ShortList.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */