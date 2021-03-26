package org.apache.xerces.stax.events;

import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.events.Namespace;

public final class NamespaceImpl extends AttributeImpl implements Namespace {
  private final String fPrefix;
  
  private final String fNamespaceURI;
  
  public NamespaceImpl(String paramString1, String paramString2, Location paramLocation) {
    super(13, makeAttributeQName(paramString1), paramString2, null, true, paramLocation);
    this.fPrefix = (paramString1 == null) ? "" : paramString1;
    this.fNamespaceURI = paramString2;
  }
  
  private static QName makeAttributeQName(String paramString) {
    return (paramString == null || paramString.equals("")) ? new QName("http://www.w3.org/2000/xmlns/", "xmlns", "") : new QName("http://www.w3.org/2000/xmlns/", paramString, "xmlns");
  }
  
  public String getPrefix() {
    return this.fPrefix;
  }
  
  public String getNamespaceURI() {
    return this.fNamespaceURI;
  }
  
  public boolean isDefaultNamespaceDeclaration() {
    return (this.fPrefix.length() == 0);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/events/NamespaceImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */