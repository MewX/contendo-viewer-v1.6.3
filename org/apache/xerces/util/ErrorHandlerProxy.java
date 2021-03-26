package org.apache.xerces.util;

import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public abstract class ErrorHandlerProxy implements ErrorHandler {
  public void error(SAXParseException paramSAXParseException) throws SAXException {
    XMLErrorHandler xMLErrorHandler = getErrorHandler();
    if (xMLErrorHandler instanceof ErrorHandlerWrapper) {
      ((ErrorHandlerWrapper)xMLErrorHandler).fErrorHandler.error(paramSAXParseException);
    } else {
      xMLErrorHandler.error("", "", ErrorHandlerWrapper.createXMLParseException(paramSAXParseException));
    } 
  }
  
  public void fatalError(SAXParseException paramSAXParseException) throws SAXException {
    XMLErrorHandler xMLErrorHandler = getErrorHandler();
    if (xMLErrorHandler instanceof ErrorHandlerWrapper) {
      ((ErrorHandlerWrapper)xMLErrorHandler).fErrorHandler.fatalError(paramSAXParseException);
    } else {
      xMLErrorHandler.fatalError("", "", ErrorHandlerWrapper.createXMLParseException(paramSAXParseException));
    } 
  }
  
  public void warning(SAXParseException paramSAXParseException) throws SAXException {
    XMLErrorHandler xMLErrorHandler = getErrorHandler();
    if (xMLErrorHandler instanceof ErrorHandlerWrapper) {
      ((ErrorHandlerWrapper)xMLErrorHandler).fErrorHandler.warning(paramSAXParseException);
    } else {
      xMLErrorHandler.warning("", "", ErrorHandlerWrapper.createXMLParseException(paramSAXParseException));
    } 
  }
  
  protected abstract XMLErrorHandler getErrorHandler();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/util/ErrorHandlerProxy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */