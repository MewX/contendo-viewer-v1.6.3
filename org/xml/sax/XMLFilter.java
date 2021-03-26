package org.xml.sax;

public interface XMLFilter extends XMLReader {
  void setParent(XMLReader paramXMLReader);
  
  XMLReader getParent();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/XMLFilter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */