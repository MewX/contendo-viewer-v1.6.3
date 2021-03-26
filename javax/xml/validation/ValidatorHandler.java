package javax.xml.validation;

import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public abstract class ValidatorHandler implements ContentHandler {
  public abstract void setContentHandler(ContentHandler paramContentHandler);
  
  public abstract ContentHandler getContentHandler();
  
  public abstract void setErrorHandler(ErrorHandler paramErrorHandler);
  
  public abstract ErrorHandler getErrorHandler();
  
  public abstract void setResourceResolver(LSResourceResolver paramLSResourceResolver);
  
  public abstract LSResourceResolver getResourceResolver();
  
  public abstract TypeInfoProvider getTypeInfoProvider();
  
  public boolean getFeature(String paramString) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString == null)
      throw new NullPointerException(); 
    throw new SAXNotRecognizedException(paramString);
  }
  
  public void setFeature(String paramString, boolean paramBoolean) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString == null)
      throw new NullPointerException(); 
    throw new SAXNotRecognizedException(paramString);
  }
  
  public void setProperty(String paramString, Object paramObject) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString == null)
      throw new NullPointerException(); 
    throw new SAXNotRecognizedException(paramString);
  }
  
  public Object getProperty(String paramString) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (paramString == null)
      throw new NullPointerException(); 
    throw new SAXNotRecognizedException(paramString);
  }
  
  public abstract void skippedEntity(String paramString) throws SAXException;
  
  public abstract void processingInstruction(String paramString1, String paramString2) throws SAXException;
  
  public abstract void ignorableWhitespace(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException;
  
  public abstract void characters(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException;
  
  public abstract void endElement(String paramString1, String paramString2, String paramString3) throws SAXException;
  
  public abstract void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes) throws SAXException;
  
  public abstract void endPrefixMapping(String paramString) throws SAXException;
  
  public abstract void startPrefixMapping(String paramString1, String paramString2) throws SAXException;
  
  public abstract void endDocument() throws SAXException;
  
  public abstract void startDocument() throws SAXException;
  
  public abstract void setDocumentLocator(Locator paramLocator);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/validation/ValidatorHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */