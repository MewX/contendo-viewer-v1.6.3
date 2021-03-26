package org.apache.xml.serializer;

import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public interface ExtendedLexicalHandler extends LexicalHandler {
  void comment(String paramString) throws SAXException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/ExtendedLexicalHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */