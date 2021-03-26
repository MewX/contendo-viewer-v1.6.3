package org.apache.xerces.dom;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;

public class DOMImplementationImpl extends CoreDOMImplementationImpl implements DOMImplementation {
  static final DOMImplementationImpl singleton = new DOMImplementationImpl();
  
  public static DOMImplementation getDOMImplementation() {
    return singleton;
  }
  
  public boolean hasFeature(String paramString1, String paramString2) {
    boolean bool = super.hasFeature(paramString1, paramString2);
    if (!bool) {
      boolean bool1 = (paramString2 == null || paramString2.length() == 0) ? true : false;
      if (paramString1.startsWith("+"))
        paramString1 = paramString1.substring(1); 
      return ((paramString1.equalsIgnoreCase("Events") && (bool1 || paramString2.equals("2.0"))) || (paramString1.equalsIgnoreCase("MutationEvents") && (bool1 || paramString2.equals("2.0"))) || (paramString1.equalsIgnoreCase("Traversal") && (bool1 || paramString2.equals("2.0"))) || (paramString1.equalsIgnoreCase("Range") && (bool1 || paramString2.equals("2.0"))) || (paramString1.equalsIgnoreCase("MutationEvents") && (bool1 || paramString2.equals("2.0"))));
    } 
    return bool;
  }
  
  protected CoreDocumentImpl createDocument(DocumentType paramDocumentType) {
    return new DocumentImpl(paramDocumentType);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/DOMImplementationImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */