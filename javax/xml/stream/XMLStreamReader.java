package javax.xml.stream;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;

public interface XMLStreamReader extends XMLStreamConstants {
  void close() throws XMLStreamException;
  
  int getAttributeCount();
  
  String getAttributeLocalName(int paramInt);
  
  QName getAttributeName(int paramInt);
  
  String getAttributeNamespace(int paramInt);
  
  String getAttributePrefix(int paramInt);
  
  String getAttributeType(int paramInt);
  
  String getAttributeValue(int paramInt);
  
  String getAttributeValue(String paramString1, String paramString2);
  
  String getCharacterEncodingScheme();
  
  String getElementText() throws XMLStreamException;
  
  String getEncoding();
  
  int getEventType();
  
  String getLocalName();
  
  Location getLocation();
  
  QName getName();
  
  NamespaceContext getNamespaceContext();
  
  int getNamespaceCount();
  
  String getNamespacePrefix(int paramInt);
  
  String getNamespaceURI();
  
  String getNamespaceURI(int paramInt);
  
  String getNamespaceURI(String paramString);
  
  String getPIData();
  
  String getPITarget();
  
  String getPrefix();
  
  Object getProperty(String paramString) throws IllegalArgumentException;
  
  String getText();
  
  char[] getTextCharacters();
  
  int getTextCharacters(int paramInt1, char[] paramArrayOfchar, int paramInt2, int paramInt3) throws XMLStreamException;
  
  int getTextLength();
  
  int getTextStart();
  
  String getVersion();
  
  boolean hasName();
  
  boolean hasNext() throws XMLStreamException;
  
  boolean hasText();
  
  boolean isAttributeSpecified(int paramInt);
  
  boolean isCharacters();
  
  boolean isEndElement();
  
  boolean isStandalone();
  
  boolean isStartElement();
  
  boolean isWhiteSpace();
  
  int next() throws XMLStreamException;
  
  int nextTag() throws XMLStreamException;
  
  void require(int paramInt, String paramString1, String paramString2) throws XMLStreamException;
  
  boolean standaloneSet();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/XMLStreamReader.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */