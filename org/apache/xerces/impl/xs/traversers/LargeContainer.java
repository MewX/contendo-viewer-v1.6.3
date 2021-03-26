package org.apache.xerces.impl.xs.traversers;

import java.util.Hashtable;

class LargeContainer extends Container {
  Hashtable items;
  
  LargeContainer(int paramInt) {
    this.items = new Hashtable(paramInt * 2 + 1);
    this.values = new OneAttr[paramInt];
  }
  
  void put(String paramString, OneAttr paramOneAttr) {
    this.items.put(paramString, paramOneAttr);
    this.values[this.pos++] = paramOneAttr;
  }
  
  OneAttr get(String paramString) {
    return (OneAttr)this.items.get(paramString);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/traversers/LargeContainer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */