package org.apache.xerces.impl.xs.util;

public final class XInt {
  private final int fValue;
  
  XInt(int paramInt) {
    this.fValue = paramInt;
  }
  
  public final int intValue() {
    return this.fValue;
  }
  
  public final short shortValue() {
    return (short)this.fValue;
  }
  
  public final boolean equals(XInt paramXInt) {
    return (this.fValue == paramXInt.fValue);
  }
  
  public String toString() {
    return Integer.toString(this.fValue);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/util/XInt.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */