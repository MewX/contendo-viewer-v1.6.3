package org.apache.xerces.parsers;

import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xinclude.XIncludeHandler;
import org.apache.xerces.xinclude.XIncludeNamespaceSupport;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLDTDSource;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xpointer.XPointerHandler;

public class XPointerParserConfiguration extends XML11Configuration {
  private XPointerHandler fXPointerHandler;
  
  private XIncludeHandler fXIncludeHandler = new XIncludeHandler();
  
  protected static final String ALLOW_UE_AND_NOTATION_EVENTS = "http://xml.org/sax/features/allow-dtd-events-after-endDTD";
  
  protected static final String XINCLUDE_FIXUP_BASE_URIS = "http://apache.org/xml/features/xinclude/fixup-base-uris";
  
  protected static final String XINCLUDE_FIXUP_LANGUAGE = "http://apache.org/xml/features/xinclude/fixup-language";
  
  protected static final String XPOINTER_HANDLER = "http://apache.org/xml/properties/internal/xpointer-handler";
  
  protected static final String XINCLUDE_HANDLER = "http://apache.org/xml/properties/internal/xinclude-handler";
  
  protected static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
  
  public XPointerParserConfiguration() {
    this((SymbolTable)null, (XMLGrammarPool)null, (XMLComponentManager)null);
  }
  
  public XPointerParserConfiguration(SymbolTable paramSymbolTable) {
    this(paramSymbolTable, (XMLGrammarPool)null, (XMLComponentManager)null);
  }
  
  public XPointerParserConfiguration(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool) {
    this(paramSymbolTable, paramXMLGrammarPool, (XMLComponentManager)null);
  }
  
  public XPointerParserConfiguration(SymbolTable paramSymbolTable, XMLGrammarPool paramXMLGrammarPool, XMLComponentManager paramXMLComponentManager) {
    super(paramSymbolTable, paramXMLGrammarPool, paramXMLComponentManager);
    addCommonComponent((XMLComponent)this.fXIncludeHandler);
    this.fXPointerHandler = new XPointerHandler();
    addCommonComponent((XMLComponent)this.fXPointerHandler);
    String[] arrayOfString1 = { "http://xml.org/sax/features/allow-dtd-events-after-endDTD", "http://apache.org/xml/features/xinclude/fixup-base-uris", "http://apache.org/xml/features/xinclude/fixup-language" };
    addRecognizedFeatures(arrayOfString1);
    String[] arrayOfString2 = { "http://apache.org/xml/properties/internal/xinclude-handler", "http://apache.org/xml/properties/internal/xpointer-handler", "http://apache.org/xml/properties/internal/namespace-context" };
    addRecognizedProperties(arrayOfString2);
    setFeature("http://xml.org/sax/features/allow-dtd-events-after-endDTD", true);
    setFeature("http://apache.org/xml/features/xinclude/fixup-base-uris", true);
    setFeature("http://apache.org/xml/features/xinclude/fixup-language", true);
    setProperty("http://apache.org/xml/properties/internal/xinclude-handler", this.fXIncludeHandler);
    setProperty("http://apache.org/xml/properties/internal/xpointer-handler", this.fXPointerHandler);
    setProperty("http://apache.org/xml/properties/internal/namespace-context", new XIncludeNamespaceSupport());
  }
  
  protected void configurePipeline() {
    super.configurePipeline();
    this.fDTDScanner.setDTDHandler((XMLDTDHandler)this.fDTDProcessor);
    this.fDTDProcessor.setDTDSource((XMLDTDSource)this.fDTDScanner);
    this.fDTDProcessor.setDTDHandler((XMLDTDHandler)this.fXIncludeHandler);
    this.fXIncludeHandler.setDTDSource((XMLDTDSource)this.fDTDProcessor);
    this.fXIncludeHandler.setDTDHandler((XMLDTDHandler)this.fXPointerHandler);
    this.fXPointerHandler.setDTDSource((XMLDTDSource)this.fXIncludeHandler);
    this.fXPointerHandler.setDTDHandler(this.fDTDHandler);
    if (this.fDTDHandler != null)
      this.fDTDHandler.setDTDSource((XMLDTDSource)this.fXPointerHandler); 
    XMLDocumentSource xMLDocumentSource = null;
    if (this.fFeatures.get("http://apache.org/xml/features/validation/schema") == Boolean.TRUE) {
      xMLDocumentSource = this.fSchemaValidator.getDocumentSource();
    } else {
      xMLDocumentSource = this.fLastComponent;
      this.fLastComponent = (XMLDocumentSource)this.fXPointerHandler;
    } 
    XMLDocumentHandler xMLDocumentHandler = xMLDocumentSource.getDocumentHandler();
    xMLDocumentSource.setDocumentHandler((XMLDocumentHandler)this.fXIncludeHandler);
    this.fXIncludeHandler.setDocumentSource(xMLDocumentSource);
    if (xMLDocumentHandler != null) {
      this.fXIncludeHandler.setDocumentHandler(xMLDocumentHandler);
      xMLDocumentHandler.setDocumentSource((XMLDocumentSource)this.fXIncludeHandler);
    } 
    this.fXIncludeHandler.setDocumentHandler((XMLDocumentHandler)this.fXPointerHandler);
    this.fXPointerHandler.setDocumentSource((XMLDocumentSource)this.fXIncludeHandler);
  }
  
  protected void configureXML11Pipeline() {
    super.configureXML11Pipeline();
    this.fXML11DTDScanner.setDTDHandler((XMLDTDHandler)this.fXML11DTDProcessor);
    this.fXML11DTDProcessor.setDTDSource((XMLDTDSource)this.fXML11DTDScanner);
    this.fDTDProcessor.setDTDHandler((XMLDTDHandler)this.fXIncludeHandler);
    this.fXIncludeHandler.setDTDSource((XMLDTDSource)this.fXML11DTDProcessor);
    this.fXIncludeHandler.setDTDHandler((XMLDTDHandler)this.fXPointerHandler);
    this.fXPointerHandler.setDTDSource((XMLDTDSource)this.fXIncludeHandler);
    this.fXPointerHandler.setDTDHandler(this.fDTDHandler);
    if (this.fDTDHandler != null)
      this.fDTDHandler.setDTDSource((XMLDTDSource)this.fXPointerHandler); 
    XMLDocumentSource xMLDocumentSource = null;
    if (this.fFeatures.get("http://apache.org/xml/features/validation/schema") == Boolean.TRUE) {
      xMLDocumentSource = this.fSchemaValidator.getDocumentSource();
    } else {
      xMLDocumentSource = this.fLastComponent;
      this.fLastComponent = (XMLDocumentSource)this.fXPointerHandler;
    } 
    XMLDocumentHandler xMLDocumentHandler = xMLDocumentSource.getDocumentHandler();
    xMLDocumentSource.setDocumentHandler((XMLDocumentHandler)this.fXIncludeHandler);
    this.fXIncludeHandler.setDocumentSource(xMLDocumentSource);
    if (xMLDocumentHandler != null) {
      this.fXIncludeHandler.setDocumentHandler(xMLDocumentHandler);
      xMLDocumentHandler.setDocumentSource((XMLDocumentSource)this.fXIncludeHandler);
    } 
    this.fXIncludeHandler.setDocumentHandler((XMLDocumentHandler)this.fXPointerHandler);
    this.fXPointerHandler.setDocumentSource((XMLDocumentSource)this.fXIncludeHandler);
  }
  
  public void setProperty(String paramString, Object paramObject) throws XMLConfigurationException {
    super.setProperty(paramString, paramObject);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/parsers/XPointerParserConfiguration.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */