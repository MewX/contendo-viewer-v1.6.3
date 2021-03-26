package org.apache.xerces.xni.grammars;

public interface XMLGrammarPool {
  Grammar[] retrieveInitialGrammarSet(String paramString);
  
  void cacheGrammars(String paramString, Grammar[] paramArrayOfGrammar);
  
  Grammar retrieveGrammar(XMLGrammarDescription paramXMLGrammarDescription);
  
  void lockPool();
  
  void unlockPool();
  
  void clear();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xni/grammars/XMLGrammarPool.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */