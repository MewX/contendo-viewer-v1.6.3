package org.apache.xml.serializer;

import java.io.IOException;
import javax.xml.transform.Transformer;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;

public interface SerializationHandler extends DOMSerializer, ExtendedContentHandler, ExtendedLexicalHandler, Serializer, XSLOutputAttributes, ErrorHandler, DeclHandler {
  void setContentHandler(ContentHandler paramContentHandler);
  
  void close();
  
  void serialize(Node paramNode) throws IOException;
  
  boolean setEscaping(boolean paramBoolean) throws SAXException;
  
  void setIndentAmount(int paramInt);
  
  void setTransformer(Transformer paramTransformer);
  
  Transformer getTransformer();
  
  void setNamespaceMappings(NamespaceMappings paramNamespaceMappings);
  
  void flushPending() throws SAXException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/SerializationHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */