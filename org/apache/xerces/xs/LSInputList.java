package org.apache.xerces.xs;

import java.util.List;
import org.w3c.dom.ls.LSInput;

public interface LSInputList extends List {
  int getLength();
  
  LSInput item(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/LSInputList.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */