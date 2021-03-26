package org.apache.xerces.xs;

import java.util.List;

public interface StringList extends List {
  int getLength();
  
  boolean contains(String paramString);
  
  String item(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/StringList.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */