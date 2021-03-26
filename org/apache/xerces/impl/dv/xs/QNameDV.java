package org.apache.xerces.impl.dv.xs;

import javax.xml.namespace.QName;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xs.datatypes.XSQName;

public class QNameDV extends TypeValidator {
  private static final String EMPTY_STRING = "".intern();
  
  public short getAllowedFacets() {
    return 2079;
  }
  
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    String str1;
    String str2;
    int i = paramString.indexOf(":");
    if (i > 0) {
      str1 = paramValidationContext.getSymbol(paramString.substring(0, i));
      str2 = paramString.substring(i + 1);
    } else {
      str1 = EMPTY_STRING;
      str2 = paramString;
    } 
    if (str1.length() > 0 && !XMLChar.isValidNCName(str1))
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "QName" }); 
    if (!XMLChar.isValidNCName(str2))
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "QName" }); 
    String str3 = paramValidationContext.getURI(str1);
    if (str1.length() > 0 && str3 == null)
      throw new InvalidDatatypeValueException("UndeclaredPrefix", new Object[] { paramString, str1 }); 
    return new XQName(str1, paramValidationContext.getSymbol(str2), paramValidationContext.getSymbol(paramString), str3);
  }
  
  public int getDataLength(Object paramObject) {
    return ((XQName)paramObject).rawname.length();
  }
  
  private static final class XQName extends QName implements XSQName {
    public XQName(String param1String1, String param1String2, String param1String3, String param1String4) {
      setValues(param1String1, param1String2, param1String3, param1String4);
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object instanceof QName) {
        QName qName = (QName)param1Object;
        return (this.uri == qName.uri && this.localpart == qName.localpart);
      } 
      return false;
    }
    
    public String toString() {
      return this.rawname;
    }
    
    public QName getJAXPQName() {
      return new QName(this.uri, this.localpart, this.prefix);
    }
    
    public QName getXNIQName() {
      return this;
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/dv/xs/QNameDV.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */