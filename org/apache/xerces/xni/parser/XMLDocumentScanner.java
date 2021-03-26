package org.apache.xerces.xni.parser;

import java.io.IOException;
import org.apache.xerces.xni.XNIException;

public interface XMLDocumentScanner extends XMLDocumentSource {
  void setInputSource(XMLInputSource paramXMLInputSource) throws IOException;
  
  boolean scanDocument(boolean paramBoolean) throws IOException, XNIException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xni/parser/XMLDocumentScanner.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */