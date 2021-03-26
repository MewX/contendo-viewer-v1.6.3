package org.apache.xerces.impl.xs.traversers;

class OneAttr {
  public String name;
  
  public int dvIndex;
  
  public int valueIndex;
  
  public Object dfltValue;
  
  public OneAttr(String paramString, int paramInt1, int paramInt2, Object paramObject) {
    this.name = paramString;
    this.dvIndex = paramInt1;
    this.valueIndex = paramInt2;
    this.dfltValue = paramObject;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/traversers/OneAttr.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */