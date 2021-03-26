package org.apache.xerces.stax;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.xml.namespace.NamespaceContext;

public final class DefaultNamespaceContext implements NamespaceContext {
  private static final DefaultNamespaceContext DEFAULT_NAMESPACE_CONTEXT_INSTANCE = new DefaultNamespaceContext();
  
  public static DefaultNamespaceContext getInstance() {
    return DEFAULT_NAMESPACE_CONTEXT_INSTANCE;
  }
  
  public String getNamespaceURI(String paramString) {
    if (paramString == null)
      throw new IllegalArgumentException("Prefix cannot be null."); 
    return "xml".equals(paramString) ? "http://www.w3.org/XML/1998/namespace" : ("xmlns".equals(paramString) ? "http://www.w3.org/2000/xmlns/" : "");
  }
  
  public String getPrefix(String paramString) {
    if (paramString == null)
      throw new IllegalArgumentException("Namespace URI cannot be null."); 
    return "http://www.w3.org/XML/1998/namespace".equals(paramString) ? "xml" : ("http://www.w3.org/2000/xmlns/".equals(paramString) ? "xmlns" : null);
  }
  
  public Iterator getPrefixes(String paramString) {
    if (paramString == null)
      throw new IllegalArgumentException("Namespace URI cannot be null."); 
    return "http://www.w3.org/XML/1998/namespace".equals(paramString) ? new Iterator(this) {
        boolean more;
        
        private final DefaultNamespaceContext this$0;
        
        public boolean hasNext() {
          return this.more;
        }
        
        public Object next() {
          if (!hasNext())
            throw new NoSuchElementException(); 
          this.more = false;
          return "xml";
        }
        
        public void remove() {
          throw new UnsupportedOperationException();
        }
      } : ("http://www.w3.org/2000/xmlns/".equals(paramString) ? new Iterator(this) {
        boolean more;
        
        private final DefaultNamespaceContext this$0;
        
        public boolean hasNext() {
          return this.more;
        }
        
        public Object next() {
          if (!hasNext())
            throw new NoSuchElementException(); 
          this.more = false;
          return "xmlns";
        }
        
        public void remove() {
          throw new UnsupportedOperationException();
        }
      } : Collections.EMPTY_LIST.iterator());
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/DefaultNamespaceContext.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */