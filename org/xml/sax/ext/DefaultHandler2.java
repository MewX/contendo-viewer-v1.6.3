package org.xml.sax.ext;

import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DefaultHandler2 extends DefaultHandler implements DeclHandler, EntityResolver2, LexicalHandler {
  public void startCDATA() throws SAXException {}
  
  public void endCDATA() throws SAXException {}
  
  public void startDTD(String paramString1, String paramString2, String paramString3) throws SAXException {}
  
  public void endDTD() throws SAXException {}
  
  public void startEntity(String paramString) throws SAXException {}
  
  public void endEntity(String paramString) throws SAXException {}
  
  public void comment(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {}
  
  public void attributeDecl(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) throws SAXException {}
  
  public void elementDecl(String paramString1, String paramString2) throws SAXException {}
  
  public void externalEntityDecl(String paramString1, String paramString2, String paramString3) throws SAXException {}
  
  public void internalEntityDecl(String paramString1, String paramString2) throws SAXException {}
  
  public InputSource getExternalSubset(String paramString1, String paramString2) throws SAXException, IOException {
    return null;
  }
  
  public InputSource resolveEntity(String paramString1, String paramString2, String paramString3, String paramString4) throws SAXException, IOException {
    return null;
  }
  
  public InputSource resolveEntity(String paramString1, String paramString2) throws SAXException, IOException {
    return resolveEntity(null, paramString1, null, paramString2);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/ext/DefaultHandler2.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */