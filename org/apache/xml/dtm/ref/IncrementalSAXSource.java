package org.apache.xml.dtm.ref;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public interface IncrementalSAXSource {
  void setContentHandler(ContentHandler paramContentHandler);
  
  void setLexicalHandler(LexicalHandler paramLexicalHandler);
  
  void setDTDHandler(DTDHandler paramDTDHandler);
  
  Object deliverMoreNodes(boolean paramBoolean);
  
  void startParse(InputSource paramInputSource) throws SAXException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/IncrementalSAXSource.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */