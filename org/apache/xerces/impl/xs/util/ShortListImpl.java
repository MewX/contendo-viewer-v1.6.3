package org.apache.xerces.impl.xs.util;

import java.util.AbstractList;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSException;

public final class ShortListImpl extends AbstractList implements ShortList {
  public static final ShortListImpl EMPTY_LIST = new ShortListImpl(new short[0], 0);
  
  private final short[] fArray;
  
  private final int fLength;
  
  public ShortListImpl(short[] paramArrayOfshort, int paramInt) {
    this.fArray = paramArrayOfshort;
    this.fLength = paramInt;
  }
  
  public int getLength() {
    return this.fLength;
  }
  
  public boolean contains(short paramShort) {
    for (byte b = 0; b < this.fLength; b++) {
      if (this.fArray[b] == paramShort)
        return true; 
    } 
    return false;
  }
  
  public short item(int paramInt) throws XSException {
    if (paramInt < 0 || paramInt >= this.fLength)
      throw new XSException((short)2, null); 
    return this.fArray[paramInt];
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null || !(paramObject instanceof ShortList))
      return false; 
    ShortList shortList = (ShortList)paramObject;
    if (this.fLength != shortList.getLength())
      return false; 
    for (byte b = 0; b < this.fLength; b++) {
      if (this.fArray[b] != shortList.item(b))
        return false; 
    } 
    return true;
  }
  
  public Object get(int paramInt) {
    if (paramInt >= 0 && paramInt < this.fLength)
      return new Short(this.fArray[paramInt]); 
    throw new IndexOutOfBoundsException("Index: " + paramInt);
  }
  
  public int size() {
    return getLength();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/util/ShortListImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */