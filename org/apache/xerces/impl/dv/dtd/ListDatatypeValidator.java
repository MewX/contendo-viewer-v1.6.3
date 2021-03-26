package org.apache.xerces.impl.dv.dtd;

import java.util.StringTokenizer;
import org.apache.xerces.impl.dv.DatatypeValidator;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class ListDatatypeValidator implements DatatypeValidator {
  final DatatypeValidator fItemValidator;
  
  public ListDatatypeValidator(DatatypeValidator paramDatatypeValidator) {
    this.fItemValidator = paramDatatypeValidator;
  }
  
  public void validate(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    StringTokenizer stringTokenizer = new StringTokenizer(paramString, " ");
    int i = stringTokenizer.countTokens();
    if (i == 0)
      throw new InvalidDatatypeValueException("EmptyList", null); 
    while (stringTokenizer.hasMoreTokens())
      this.fItemValidator.validate(stringTokenizer.nextToken(), paramValidationContext); 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/dv/dtd/ListDatatypeValidator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */