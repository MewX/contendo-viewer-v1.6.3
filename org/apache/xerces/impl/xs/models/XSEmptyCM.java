package org.apache.xerces.impl.xs.models;

import java.util.Vector;
import org.apache.xerces.impl.xs.SubstitutionGroupHandler;
import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.xni.QName;

public class XSEmptyCM implements XSCMValidator {
  private static final short STATE_START = 0;
  
  private static final Vector EMPTY = new Vector(0);
  
  public int[] startContentModel() {
    return new int[] { 0 };
  }
  
  public Object oneTransition(QName paramQName, int[] paramArrayOfint, SubstitutionGroupHandler paramSubstitutionGroupHandler) {
    if (paramArrayOfint[0] < 0) {
      paramArrayOfint[0] = -2;
      return null;
    } 
    paramArrayOfint[0] = -1;
    return null;
  }
  
  public boolean endContentModel(int[] paramArrayOfint) {
    boolean bool = false;
    int i = paramArrayOfint[0];
    return !(i < 0);
  }
  
  public boolean checkUniqueParticleAttribution(SubstitutionGroupHandler paramSubstitutionGroupHandler) throws XMLSchemaException {
    return false;
  }
  
  public Vector whatCanGoHere(int[] paramArrayOfint) {
    return EMPTY;
  }
  
  public int[] occurenceInfo(int[] paramArrayOfint) {
    return null;
  }
  
  public String getTermName(int paramInt) {
    return null;
  }
  
  public boolean isCompactedForUPA() {
    return false;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/models/XSEmptyCM.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */