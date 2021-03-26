package org.apache.xerces.parsers;

import org.apache.xerces.util.SoftReferenceSymbolTable;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLComponentManager;

public class SoftReferenceSymbolTableConfiguration extends XIncludeAwareParserConfiguration {
  public SoftReferenceSymbolTableConfiguration() {
    this((SymbolTable)new SoftReferenceSymbolTable(), null, null);
  }
  
  public SoftReferenceSymbolTableConfiguration(SymbolTable paramSymbolTable) {
    this(paramSymbolTable, null, null);
  }
  
  public SoftReferenceSymbolTableConfiguration(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool) {
    this(paramSymbolTable, paramXMLGrammarPool, null);
  }
  
  public SoftReferenceSymbolTableConfiguration(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool, XMLComponentManager paramXMLComponentManager) {
    super(paramSymbolTable, paramXMLGrammarPool, paramXMLComponentManager);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/parsers/SoftReferenceSymbolTableConfiguration.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */