package org.apache.xerces.xni.grammars;

import org.apache.xerces.xni.XMLResourceIdentifier;

public interface XMLGrammarDescription extends XMLResourceIdentifier {
  public static final String XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
  
  public static final String XML_DTD = "http://www.w3.org/TR/REC-xml";
  
  String getGrammarType();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xni/grammars/XMLGrammarDescription.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */