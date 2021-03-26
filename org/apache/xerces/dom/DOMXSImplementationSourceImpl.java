package org.apache.xerces.dom;

import java.util.ArrayList;
import org.apache.xerces.impl.xs.XSImplementationImpl;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DOMImplementationList;

public class DOMXSImplementationSourceImpl extends DOMImplementationSourceImpl {
  public DOMImplementation getDOMImplementation(String paramString) {
    DOMImplementation dOMImplementation = super.getDOMImplementation(paramString);
    if (dOMImplementation != null)
      return dOMImplementation; 
    dOMImplementation = PSVIDOMImplementationImpl.getDOMImplementation();
    if (testImpl(dOMImplementation, paramString))
      return dOMImplementation; 
    dOMImplementation = XSImplementationImpl.getDOMImplementation();
    return testImpl(dOMImplementation, paramString) ? dOMImplementation : null;
  }
  
  public DOMImplementationList getDOMImplementationList(String paramString) {
    ArrayList arrayList = new ArrayList();
    DOMImplementationList dOMImplementationList = super.getDOMImplementationList(paramString);
    for (byte b = 0; b < dOMImplementationList.getLength(); b++)
      arrayList.add(dOMImplementationList.item(b)); 
    DOMImplementation dOMImplementation = PSVIDOMImplementationImpl.getDOMImplementation();
    if (testImpl(dOMImplementation, paramString))
      arrayList.add(dOMImplementation); 
    dOMImplementation = XSImplementationImpl.getDOMImplementation();
    if (testImpl(dOMImplementation, paramString))
      arrayList.add(dOMImplementation); 
    return new DOMImplementationListImpl(arrayList);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/DOMXSImplementationSourceImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */