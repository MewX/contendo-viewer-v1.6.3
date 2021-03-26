package org.apache.xerces.jaxp.validation;

import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.xni.grammars.XMLGrammarPool;

final class ReadOnlyGrammarPool implements XMLGrammarPool {
  private final XMLGrammarPool core;
  
  public ReadOnlyGrammarPool(XMLGrammarPool paramXMLGrammarPool) {
    this.core = paramXMLGrammarPool;
  }
  
  public void cacheGrammars(String paramString, Grammar[] paramArrayOfGrammar) {}
  
  public void clear() {}
  
  public void lockPool() {}
  
  public Grammar retrieveGrammar(XMLGrammarDescription paramXMLGrammarDescription) {
    return this.core.retrieveGrammar(paramXMLGrammarDescription);
  }
  
  public Grammar[] retrieveInitialGrammarSet(String paramString) {
    return this.core.retrieveInitialGrammarSet(paramString);
  }
  
  public void unlockPool() {}
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/jaxp/validation/ReadOnlyGrammarPool.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */