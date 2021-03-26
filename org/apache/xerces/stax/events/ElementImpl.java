package org.apache.xerces.stax.events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.events.Namespace;

abstract class ElementImpl extends XMLEventImpl {
  private final QName fName;
  
  private final List fNamespaces;
  
  ElementImpl(QName paramQName, boolean paramBoolean, Iterator paramIterator, Location paramLocation) {
    super(paramBoolean ? 1 : 2, paramLocation);
    this.fName = paramQName;
    if (paramIterator != null && paramIterator.hasNext()) {
      this.fNamespaces = new ArrayList();
      do {
        Namespace namespace = paramIterator.next();
        this.fNamespaces.add(namespace);
      } while (paramIterator.hasNext());
    } else {
      this.fNamespaces = Collections.EMPTY_LIST;
    } 
  }
  
  public final QName getName() {
    return this.fName;
  }
  
  public final Iterator getNamespaces() {
    return createImmutableIterator(this.fNamespaces.iterator());
  }
  
  static Iterator createImmutableIterator(Iterator paramIterator) {
    return new NoRemoveIterator(paramIterator);
  }
  
  private static final class NoRemoveIterator implements Iterator {
    private final Iterator fWrapped;
    
    public NoRemoveIterator(Iterator param1Iterator) {
      this.fWrapped = param1Iterator;
    }
    
    public boolean hasNext() {
      return this.fWrapped.hasNext();
    }
    
    public Object next() {
      return this.fWrapped.next();
    }
    
    public void remove() {
      throw new UnsupportedOperationException("Attributes iterator is read-only.");
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/events/ElementImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */