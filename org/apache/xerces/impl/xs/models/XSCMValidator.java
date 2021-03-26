package org.apache.xerces.impl.xs.models;

import java.util.Vector;
import org.apache.xerces.impl.xs.SubstitutionGroupHandler;
import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.xni.QName;

public interface XSCMValidator {
  public static final short FIRST_ERROR = -1;
  
  public static final short SUBSEQUENT_ERROR = -2;
  
  int[] startContentModel();
  
  Object oneTransition(QName paramQName, int[] paramArrayOfint, SubstitutionGroupHandler paramSubstitutionGroupHandler);
  
  boolean endContentModel(int[] paramArrayOfint);
  
  boolean checkUniqueParticleAttribution(SubstitutionGroupHandler paramSubstitutionGroupHandler) throws XMLSchemaException;
  
  Vector whatCanGoHere(int[] paramArrayOfint);
  
  int[] occurenceInfo(int[] paramArrayOfint);
  
  String getTermName(int paramInt);
  
  boolean isCompactedForUPA();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/models/XSCMValidator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */