package org.apache.xerces.impl.dv.dtd;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.util.XML11Char;

public class XML11NMTOKENDatatypeValidator extends NMTOKENDatatypeValidator {
  public void validate(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    if (!XML11Char.isXML11ValidNmtoken(paramString))
      throw new InvalidDatatypeValueException("NMTOKENInvalid", new Object[] { paramString }); 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/dv/dtd/XML11NMTOKENDatatypeValidator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */