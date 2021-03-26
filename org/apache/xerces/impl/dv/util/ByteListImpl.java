package org.apache.xerces.impl.dv.util;

import java.util.AbstractList;
import org.apache.xerces.xs.XSException;
import org.apache.xerces.xs.datatypes.ByteList;

public class ByteListImpl extends AbstractList implements ByteList {
  protected final byte[] data;
  
  protected String canonical;
  
  public ByteListImpl(byte[] paramArrayOfbyte) {
    this.data = paramArrayOfbyte;
  }
  
  public int getLength() {
    return this.data.length;
  }
  
  public boolean contains(byte paramByte) {
    for (byte b = 0; b < this.data.length; b++) {
      if (this.data[b] == paramByte)
        return true; 
    } 
    return false;
  }
  
  public byte item(int paramInt) throws XSException {
    if (paramInt < 0 || paramInt > this.data.length - 1)
      throw new XSException((short)2, null); 
    return this.data[paramInt];
  }
  
  public Object get(int paramInt) {
    if (paramInt >= 0 && paramInt < this.data.length)
      return new Byte(this.data[paramInt]); 
    throw new IndexOutOfBoundsException("Index: " + paramInt);
  }
  
  public int size() {
    return getLength();
  }
  
  public byte[] toByteArray() {
    byte[] arrayOfByte = new byte[this.data.length];
    System.arraycopy(this.data, 0, arrayOfByte, 0, this.data.length);
    return arrayOfByte;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/dv/util/ByteListImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */