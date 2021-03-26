package org.xml.sax.helpers;

import java.io.IOException;
import java.util.Locale;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

public class XMLReaderAdapter implements ContentHandler, Parser {
  XMLReader a;
  
  DocumentHandler b;
  
  AttributesAdapter c;
  
  public XMLReaderAdapter() throws SAXException {
    a(XMLReaderFactory.createXMLReader());
  }
  
  public XMLReaderAdapter(XMLReader paramXMLReader) {
    a(paramXMLReader);
  }
  
  private void a(XMLReader paramXMLReader) {
    if (paramXMLReader == null)
      throw new NullPointerException("XMLReader must not be null"); 
    this.a = paramXMLReader;
    this.c = new AttributesAdapter(this);
  }
  
  public void setLocale(Locale paramLocale) throws SAXException {
    throw new SAXNotSupportedException("setLocale not supported");
  }
  
  public void setEntityResolver(EntityResolver paramEntityResolver) {
    this.a.setEntityResolver(paramEntityResolver);
  }
  
  public void setDTDHandler(DTDHandler paramDTDHandler) {
    this.a.setDTDHandler(paramDTDHandler);
  }
  
  public void setDocumentHandler(DocumentHandler paramDocumentHandler) {
    this.b = paramDocumentHandler;
  }
  
  public void setErrorHandler(ErrorHandler paramErrorHandler) {
    this.a.setErrorHandler(paramErrorHandler);
  }
  
  public void parse(String paramString) throws IOException, SAXException {
    parse(new InputSource(paramString));
  }
  
  public void parse(InputSource paramInputSource) throws IOException, SAXException {
    a();
    this.a.parse(paramInputSource);
  }
  
  private void a() throws SAXException {
    this.a.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
    try {
      this.a.setFeature("http://xml.org/sax/features/namespaces", false);
    } catch (SAXException sAXException) {}
    this.a.setContentHandler(this);
  }
  
  public void setDocumentLocator(Locator paramLocator) {
    if (this.b != null)
      this.b.setDocumentLocator(paramLocator); 
  }
  
  public void startDocument() throws SAXException {
    if (this.b != null)
      this.b.startDocument(); 
  }
  
  public void endDocument() throws SAXException {
    if (this.b != null)
      this.b.endDocument(); 
  }
  
  public void startPrefixMapping(String paramString1, String paramString2) {}
  
  public void endPrefixMapping(String paramString) {}
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes) throws SAXException {
    if (this.b != null) {
      this.c.a(paramAttributes);
      this.b.startElement(paramString3, this.c);
    } 
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3) throws SAXException {
    if (this.b != null)
      this.b.endElement(paramString3); 
  }
  
  public void characters(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {
    if (this.b != null)
      this.b.characters(paramArrayOfchar, paramInt1, paramInt2); 
  }
  
  public void ignorableWhitespace(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {
    if (this.b != null)
      this.b.ignorableWhitespace(paramArrayOfchar, paramInt1, paramInt2); 
  }
  
  public void processingInstruction(String paramString1, String paramString2) throws SAXException {
    if (this.b != null)
      this.b.processingInstruction(paramString1, paramString2); 
  }
  
  public void skippedEntity(String paramString) throws SAXException {}
  
  final class AttributesAdapter implements AttributeList {
    private Attributes a;
    
    private final XMLReaderAdapter b;
    
    AttributesAdapter(XMLReaderAdapter this$0) {
      this.b = this$0;
    }
    
    void a(Attributes param1Attributes) {
      this.a = param1Attributes;
    }
    
    public int getLength() {
      return this.a.getLength();
    }
    
    public String getName(int param1Int) {
      return this.a.getQName(param1Int);
    }
    
    public String getType(int param1Int) {
      return this.a.getType(param1Int);
    }
    
    public String getValue(int param1Int) {
      return this.a.getValue(param1Int);
    }
    
    public String getType(String param1String) {
      return this.a.getType(param1String);
    }
    
    public String getValue(String param1String) {
      return this.a.getValue(param1String);
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/helpers/XMLReaderAdapter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */