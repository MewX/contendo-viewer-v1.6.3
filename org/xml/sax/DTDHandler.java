package org.xml.sax;

public interface DTDHandler {
  void notationDecl(String paramString1, String paramString2, String paramString3) throws SAXException;
  
  void unparsedEntityDecl(String paramString1, String paramString2, String paramString3, String paramString4) throws SAXException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/DTDHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */