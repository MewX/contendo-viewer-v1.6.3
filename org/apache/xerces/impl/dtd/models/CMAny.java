package org.apache.xerces.impl.dtd.models;

public class CMAny extends CMNode {
  private final int fType;
  
  private final String fURI;
  
  private int fPosition = -1;
  
  public CMAny(int paramInt1, String paramString, int paramInt2) {
    super(paramInt1);
    this.fType = paramInt1;
    this.fURI = paramString;
    this.fPosition = paramInt2;
  }
  
  final int getType() {
    return this.fType;
  }
  
  final String getURI() {
    return this.fURI;
  }
  
  final int getPosition() {
    return this.fPosition;
  }
  
  final void setPosition(int paramInt) {
    this.fPosition = paramInt;
  }
  
  public boolean isNullable() {
    return (this.fPosition == -1);
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append('(');
    stringBuffer.append("##any:uri=");
    stringBuffer.append(this.fURI);
    stringBuffer.append(')');
    if (this.fPosition >= 0)
      stringBuffer.append(" (Pos:").append(Integer.toString(this.fPosition)).append(')'); 
    return stringBuffer.toString();
  }
  
  protected void calcFirstPos(CMStateSet paramCMStateSet) {
    if (this.fPosition == -1) {
      paramCMStateSet.zeroBits();
    } else {
      paramCMStateSet.setBit(this.fPosition);
    } 
  }
  
  protected void calcLastPos(CMStateSet paramCMStateSet) {
    if (this.fPosition == -1) {
      paramCMStateSet.zeroBits();
    } else {
      paramCMStateSet.setBit(this.fPosition);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/dtd/models/CMAny.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */