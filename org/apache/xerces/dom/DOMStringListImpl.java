package org.apache.xerces.dom;

import java.util.ArrayList;
import java.util.Vector;
import org.w3c.dom.DOMStringList;

public class DOMStringListImpl implements DOMStringList {
  private final ArrayList fStrings = new ArrayList();
  
  public DOMStringListImpl() {}
  
  public DOMStringListImpl(ArrayList paramArrayList) {}
  
  public DOMStringListImpl(Vector paramVector) {}
  
  public String item(int paramInt) {
    int i = getLength();
    return (paramInt >= 0 && paramInt < i) ? this.fStrings.get(paramInt) : null;
  }
  
  public int getLength() {
    return this.fStrings.size();
  }
  
  public boolean contains(String paramString) {
    return this.fStrings.contains(paramString);
  }
  
  public void add(String paramString) {
    this.fStrings.add(paramString);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/DOMStringListImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */