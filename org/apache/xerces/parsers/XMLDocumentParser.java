package org.apache.xerces.parsers;

import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLParserConfiguration;

public class XMLDocumentParser extends AbstractXMLDocumentParser {
  public XMLDocumentParser() {
    super((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
  }
  
  public XMLDocumentParser(XMLParserConfiguration paramXMLParserConfiguration) {
    super(paramXMLParserConfiguration);
  }
  
  public XMLDocumentParser(SymbolTable paramSymbolTable) {
    super((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
    this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", paramSymbolTable);
  }
  
  public XMLDocumentParser(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool) {
    super((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
    this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", paramSymbolTable);
    this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", paramXMLGrammarPool);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/parsers/XMLDocumentParser.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */