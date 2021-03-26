package org.apache.xerces.xs.datatypes;

import java.util.List;
import org.apache.xerces.xs.XSException;

public interface ByteList extends List {
  int getLength();
  
  boolean contains(byte paramByte);
  
  byte item(int paramInt) throws XSException;
  
  byte[] toByteArray();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/datatypes/ByteList.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */