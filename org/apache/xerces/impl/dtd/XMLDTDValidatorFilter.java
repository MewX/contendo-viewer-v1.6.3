package org.apache.xerces.impl.dtd;

import org.apache.xerces.xni.parser.XMLDocumentFilter;

public interface XMLDTDValidatorFilter extends XMLDocumentFilter {
  boolean hasGrammar();
  
  boolean validate();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/dtd/XMLDTDValidatorFilter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */