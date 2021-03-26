package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.impl.dv.util.ByteListImpl;
import org.apache.xerces.impl.dv.util.HexBin;

public class HexBinaryDV extends TypeValidator {
  public short getAllowedFacets() {
    return 2079;
  }
  
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    byte[] arrayOfByte = HexBin.decode(paramString);
    if (arrayOfByte == null)
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "hexBinary" }); 
    return new XHex(arrayOfByte);
  }
  
  public int getDataLength(Object paramObject) {
    return ((XHex)paramObject).getLength();
  }
  
  private static final class XHex extends ByteListImpl {
    public XHex(byte[] param1ArrayOfbyte) {
      super(param1ArrayOfbyte);
    }
    
    public synchronized String toString() {
      if (this.canonical == null)
        this.canonical = HexBin.encode(this.data); 
      return this.canonical;
    }
    
    public boolean equals(Object param1Object) {
      if (!(param1Object instanceof XHex))
        return false; 
      byte[] arrayOfByte = ((XHex)param1Object).data;
      int i = this.data.length;
      if (i != arrayOfByte.length)
        return false; 
      for (byte b = 0; b < i; b++) {
        if (this.data[b] != arrayOfByte[b])
          return false; 
      } 
      return true;
    }
    
    public int hashCode() {
      int i = 0;
      for (byte b = 0; b < this.data.length; b++)
        i = i * 37 + (this.data[b] & 0xFF); 
      return i;
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/dv/xs/HexBinaryDV.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */