package org.apache.xerces.impl.xs;

import java.util.AbstractList;
import org.apache.xerces.xs.StringList;

final class PSVIErrorList extends AbstractList implements StringList {
  private final String[] fArray;
  
  private final int fLength;
  
  private final int fOffset;
  
  public PSVIErrorList(String[] paramArrayOfString, boolean paramBoolean) {
    this.fArray = paramArrayOfString;
    this.fLength = this.fArray.length >> 1;
    this.fOffset = paramBoolean ? 0 : 1;
  }
  
  public boolean contains(String paramString) {
    if (paramString == null) {
      for (byte b = 0; b < this.fLength; b++) {
        if (this.fArray[(b << 1) + this.fOffset] == null)
          return true; 
      } 
    } else {
      for (byte b = 0; b < this.fLength; b++) {
        if (paramString.equals(this.fArray[(b << 1) + this.fOffset]))
          return true; 
      } 
    } 
    return false;
  }
  
  public int getLength() {
    return this.fLength;
  }
  
  public String item(int paramInt) {
    return (paramInt < 0 || paramInt >= this.fLength) ? null : this.fArray[(paramInt << 1) + this.fOffset];
  }
  
  public Object get(int paramInt) {
    if (paramInt >= 0 && paramInt < this.fLength)
      return this.fArray[(paramInt << 1) + this.fOffset]; 
    throw new IndexOutOfBoundsException("Index: " + paramInt);
  }
  
  public int size() {
    return getLength();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/PSVIErrorList.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */