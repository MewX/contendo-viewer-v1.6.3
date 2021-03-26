package org.apache.xerces.jaxp.validation;

import org.apache.xerces.xni.grammars.XMLGrammarPool;

public interface XSGrammarPoolContainer {
  XMLGrammarPool getGrammarPool();
  
  boolean isFullyComposed();
  
  Boolean getFeature(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/jaxp/validation/XSGrammarPoolContainer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */