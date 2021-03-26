package org.apache.xerces.dom;

import java.util.ArrayList;
import java.util.Vector;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DOMImplementationList;

public class DOMImplementationListImpl implements DOMImplementationList {
  private final ArrayList fImplementations = new ArrayList();
  
  public DOMImplementationListImpl() {}
  
  public DOMImplementationListImpl(ArrayList paramArrayList) {}
  
  public DOMImplementationListImpl(Vector paramVector) {}
  
  public DOMImplementation item(int paramInt) {
    int i = getLength();
    return (paramInt >= 0 && paramInt < i) ? this.fImplementations.get(paramInt) : null;
  }
  
  public int getLength() {
    return this.fImplementations.size();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/DOMImplementationListImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */