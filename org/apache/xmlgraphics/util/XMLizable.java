package org.apache.xmlgraphics.util;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public interface XMLizable {
  void toSAX(ContentHandler paramContentHandler) throws SAXException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/XMLizable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */