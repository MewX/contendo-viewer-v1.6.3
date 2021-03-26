package org.apache.xerces.jaxp.validation;

import org.apache.xerces.xni.grammars.XMLGrammarPool;

final class XMLSchema extends AbstractXMLSchema {
  private final XMLGrammarPool fGrammarPool;
  
  private final boolean fFullyComposed;
  
  public XMLSchema(XMLGrammarPool paramXMLGrammarPool) {
    this(paramXMLGrammarPool, true);
  }
  
  public XMLSchema(XMLGrammarPool paramXMLGrammarPool, boolean paramBoolean) {
    this.fGrammarPool = paramXMLGrammarPool;
    this.fFullyComposed = paramBoolean;
  }
  
  public XMLGrammarPool getGrammarPool() {
    return this.fGrammarPool;
  }
  
  public boolean isFullyComposed() {
    return this.fFullyComposed;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/jaxp/validation/XMLSchema.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */