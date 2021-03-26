package org.apache.xerces.impl.xs.opti;

import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

final class SchemaDOMImplementation implements DOMImplementation {
  private static final SchemaDOMImplementation singleton = new SchemaDOMImplementation();
  
  public static DOMImplementation getDOMImplementation() {
    return singleton;
  }
  
  public Document createDocument(String paramString1, String paramString2, DocumentType paramDocumentType) throws DOMException {
    throw new DOMException((short)9, "Method not supported");
  }
  
  public DocumentType createDocumentType(String paramString1, String paramString2, String paramString3) throws DOMException {
    throw new DOMException((short)9, "Method not supported");
  }
  
  public Object getFeature(String paramString1, String paramString2) {
    return singleton.hasFeature(paramString1, paramString2) ? singleton : null;
  }
  
  public boolean hasFeature(String paramString1, String paramString2) {
    boolean bool = (paramString2 == null || paramString2.length() == 0) ? true : false;
    return ((paramString1.equalsIgnoreCase("Core") || paramString1.equalsIgnoreCase("XML")) && (bool || paramString2.equals("1.0") || paramString2.equals("2.0") || paramString2.equals("3.0")));
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/opti/SchemaDOMImplementation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */