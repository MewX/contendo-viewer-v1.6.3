package org.xml.sax;

public interface ContentHandler {
  void setDocumentLocator(Locator paramLocator);
  
  void startDocument() throws SAXException;
  
  void endDocument() throws SAXException;
  
  void startPrefixMapping(String paramString1, String paramString2) throws SAXException;
  
  void endPrefixMapping(String paramString) throws SAXException;
  
  void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes) throws SAXException;
  
  void endElement(String paramString1, String paramString2, String paramString3) throws SAXException;
  
  void characters(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException;
  
  void ignorableWhitespace(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException;
  
  void processingInstruction(String paramString1, String paramString2) throws SAXException;
  
  void skippedEntity(String paramString) throws SAXException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/ContentHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */