package org.apache.xerces.impl.dv.dtd;

import java.util.Hashtable;
import java.util.Map;
import org.apache.xerces.impl.dv.DatatypeValidator;

public class XML11DTDDVFactoryImpl extends DTDDVFactoryImpl {
  static final Hashtable fXML11BuiltInTypes = new Hashtable();
  
  public DatatypeValidator getBuiltInDV(String paramString) {
    return (fXML11BuiltInTypes.get(paramString) != null) ? (DatatypeValidator)fXML11BuiltInTypes.get(paramString) : (DatatypeValidator)DTDDVFactoryImpl.fBuiltInTypes.get(paramString);
  }
  
  public Hashtable getBuiltInTypes() {
    Hashtable hashtable = (Hashtable)DTDDVFactoryImpl.fBuiltInTypes.clone();
    for (Map.Entry entry : fXML11BuiltInTypes.entrySet()) {
      Object object1 = entry.getKey();
      Object object2 = entry.getValue();
      hashtable.put(object1, object2);
    } 
    return hashtable;
  }
  
  static {
    fXML11BuiltInTypes.put("XML11ID", new XML11IDDatatypeValidator());
    XML11IDREFDatatypeValidator xML11IDREFDatatypeValidator = new XML11IDREFDatatypeValidator();
    fXML11BuiltInTypes.put("XML11IDREF", xML11IDREFDatatypeValidator);
    fXML11BuiltInTypes.put("XML11IDREFS", new ListDatatypeValidator(xML11IDREFDatatypeValidator));
    XML11NMTOKENDatatypeValidator xML11NMTOKENDatatypeValidator = new XML11NMTOKENDatatypeValidator();
    fXML11BuiltInTypes.put("XML11NMTOKEN", xML11NMTOKENDatatypeValidator);
    fXML11BuiltInTypes.put("XML11NMTOKENS", new ListDatatypeValidator(xML11NMTOKENDatatypeValidator));
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/dv/dtd/XML11DTDDVFactoryImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */