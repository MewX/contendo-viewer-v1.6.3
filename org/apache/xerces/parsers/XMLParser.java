package org.apache.xerces.parsers;

import java.io.IOException;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLParserConfiguration;

public abstract class XMLParser {
  protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
  
  protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
  
  private static final String[] RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/error-handler" };
  
  protected final XMLParserConfiguration fConfiguration;
  
  protected XMLParser(XMLParserConfiguration paramXMLParserConfiguration) {
    this.fConfiguration = paramXMLParserConfiguration;
    this.fConfiguration.addRecognizedProperties(RECOGNIZED_PROPERTIES);
  }
  
  public void parse(XMLInputSource paramXMLInputSource) throws XNIException, IOException {
    reset();
    this.fConfiguration.parse(paramXMLInputSource);
  }
  
  protected void reset() throws XNIException {}
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/parsers/XMLParser.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */