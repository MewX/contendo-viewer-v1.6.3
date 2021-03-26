package org.apache.xerces.impl.xs.util;

public final class XIntPool {
  private static final short POOL_SIZE = 10;
  
  private static final XInt[] fXIntPool = new XInt[10];
  
  public final XInt getXInt(int paramInt) {
    return (paramInt >= 0 && paramInt < fXIntPool.length) ? fXIntPool[paramInt] : new XInt(paramInt);
  }
  
  static {
    for (byte b = 0; b < 10; b++)
      fXIntPool[b] = new XInt(b); 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/util/XIntPool.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */