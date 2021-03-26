package javax.xml.stream.util;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StreamReaderDelegate implements XMLStreamReader {
  private XMLStreamReader a;
  
  public StreamReaderDelegate() {}
  
  public StreamReaderDelegate(XMLStreamReader paramXMLStreamReader) {
    this.a = paramXMLStreamReader;
  }
  
  public void setParent(XMLStreamReader paramXMLStreamReader) {
    this.a = paramXMLStreamReader;
  }
  
  public XMLStreamReader getParent() {
    return this.a;
  }
  
  public int next() throws XMLStreamException {
    return this.a.next();
  }
  
  public int nextTag() throws XMLStreamException {
    return this.a.nextTag();
  }
  
  public String getElementText() throws XMLStreamException {
    return this.a.getElementText();
  }
  
  public void require(int paramInt, String paramString1, String paramString2) throws XMLStreamException {
    this.a.require(paramInt, paramString1, paramString2);
  }
  
  public boolean hasNext() throws XMLStreamException {
    return this.a.hasNext();
  }
  
  public void close() throws XMLStreamException {
    this.a.close();
  }
  
  public String getNamespaceURI(String paramString) {
    return this.a.getNamespaceURI(paramString);
  }
  
  public NamespaceContext getNamespaceContext() {
    return this.a.getNamespaceContext();
  }
  
  public boolean isStartElement() {
    return this.a.isStartElement();
  }
  
  public boolean isEndElement() {
    return this.a.isEndElement();
  }
  
  public boolean isCharacters() {
    return this.a.isCharacters();
  }
  
  public boolean isWhiteSpace() {
    return this.a.isWhiteSpace();
  }
  
  public String getAttributeValue(String paramString1, String paramString2) {
    return this.a.getAttributeValue(paramString1, paramString2);
  }
  
  public int getAttributeCount() {
    return this.a.getAttributeCount();
  }
  
  public QName getAttributeName(int paramInt) {
    return this.a.getAttributeName(paramInt);
  }
  
  public String getAttributePrefix(int paramInt) {
    return this.a.getAttributePrefix(paramInt);
  }
  
  public String getAttributeNamespace(int paramInt) {
    return this.a.getAttributeNamespace(paramInt);
  }
  
  public String getAttributeLocalName(int paramInt) {
    return this.a.getAttributeLocalName(paramInt);
  }
  
  public String getAttributeType(int paramInt) {
    return this.a.getAttributeType(paramInt);
  }
  
  public String getAttributeValue(int paramInt) {
    return this.a.getAttributeValue(paramInt);
  }
  
  public boolean isAttributeSpecified(int paramInt) {
    return this.a.isAttributeSpecified(paramInt);
  }
  
  public int getNamespaceCount() {
    return this.a.getNamespaceCount();
  }
  
  public String getNamespacePrefix(int paramInt) {
    return this.a.getNamespacePrefix(paramInt);
  }
  
  public String getNamespaceURI(int paramInt) {
    return this.a.getNamespaceURI(paramInt);
  }
  
  public int getEventType() {
    return this.a.getEventType();
  }
  
  public String getText() {
    return this.a.getText();
  }
  
  public int getTextCharacters(int paramInt1, char[] paramArrayOfchar, int paramInt2, int paramInt3) throws XMLStreamException {
    return this.a.getTextCharacters(paramInt1, paramArrayOfchar, paramInt2, paramInt3);
  }
  
  public char[] getTextCharacters() {
    return this.a.getTextCharacters();
  }
  
  public int getTextStart() {
    return this.a.getTextStart();
  }
  
  public int getTextLength() {
    return this.a.getTextLength();
  }
  
  public String getEncoding() {
    return this.a.getEncoding();
  }
  
  public boolean hasText() {
    return this.a.hasText();
  }
  
  public Location getLocation() {
    return this.a.getLocation();
  }
  
  public QName getName() {
    return this.a.getName();
  }
  
  public String getLocalName() {
    return this.a.getLocalName();
  }
  
  public boolean hasName() {
    return this.a.hasName();
  }
  
  public String getNamespaceURI() {
    return this.a.getNamespaceURI();
  }
  
  public String getPrefix() {
    return this.a.getPrefix();
  }
  
  public String getVersion() {
    return this.a.getVersion();
  }
  
  public boolean isStandalone() {
    return this.a.isStandalone();
  }
  
  public boolean standaloneSet() {
    return this.a.standaloneSet();
  }
  
  public String getCharacterEncodingScheme() {
    return this.a.getCharacterEncodingScheme();
  }
  
  public String getPITarget() {
    return this.a.getPITarget();
  }
  
  public String getPIData() {
    return this.a.getPIData();
  }
  
  public Object getProperty(String paramString) throws IllegalArgumentException {
    return this.a.getProperty(paramString);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/stream/util/StreamReaderDelegate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */