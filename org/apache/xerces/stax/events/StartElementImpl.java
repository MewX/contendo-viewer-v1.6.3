package org.apache.xerces.stax.events;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.StartElement;
import org.apache.xerces.stax.DefaultNamespaceContext;

public final class StartElementImpl extends ElementImpl implements StartElement {
  private static final Comparator QNAME_COMPARATOR = new Comparator() {
      public int compare(Object param1Object1, Object param1Object2) {
        if (param1Object1.equals(param1Object2))
          return 0; 
        QName qName1 = (QName)param1Object1;
        QName qName2 = (QName)param1Object2;
        return qName1.toString().compareTo(qName2.toString());
      }
    };
  
  private final Map fAttributes;
  
  private final NamespaceContext fNamespaceContext;
  
  public StartElementImpl(QName paramQName, Iterator paramIterator1, Iterator paramIterator2, NamespaceContext paramNamespaceContext, Location paramLocation) {
    super(paramQName, true, paramIterator2, paramLocation);
    if (paramIterator1 != null && paramIterator1.hasNext()) {
      this.fAttributes = new TreeMap(QNAME_COMPARATOR);
      do {
        Attribute attribute = paramIterator1.next();
        this.fAttributes.put(attribute.getName(), attribute);
      } while (paramIterator1.hasNext());
    } else {
      this.fAttributes = Collections.EMPTY_MAP;
    } 
    this.fNamespaceContext = (paramNamespaceContext != null) ? paramNamespaceContext : (NamespaceContext)DefaultNamespaceContext.getInstance();
  }
  
  public Iterator getAttributes() {
    return ElementImpl.createImmutableIterator(this.fAttributes.values().iterator());
  }
  
  public Attribute getAttributeByName(QName paramQName) {
    return (Attribute)this.fAttributes.get(paramQName);
  }
  
  public NamespaceContext getNamespaceContext() {
    return this.fNamespaceContext;
  }
  
  public String getNamespaceURI(String paramString) {
    return this.fNamespaceContext.getNamespaceURI(paramString);
  }
  
  public void writeAsEncodedUnicode(Writer paramWriter) throws XMLStreamException {
    try {
      paramWriter.write(60);
      QName qName = getName();
      String str = qName.getPrefix();
      if (str != null && str.length() > 0) {
        paramWriter.write(str);
        paramWriter.write(58);
      } 
      paramWriter.write(qName.getLocalPart());
      Iterator iterator = getNamespaces();
      while (iterator.hasNext()) {
        Namespace namespace = iterator.next();
        paramWriter.write(32);
        namespace.writeAsEncodedUnicode(paramWriter);
      } 
      Iterator iterator1 = getAttributes();
      while (iterator1.hasNext()) {
        Attribute attribute = iterator1.next();
        paramWriter.write(32);
        attribute.writeAsEncodedUnicode(paramWriter);
      } 
      paramWriter.write(62);
    } catch (IOException iOException) {
      throw new XMLStreamException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/events/StartElementImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */