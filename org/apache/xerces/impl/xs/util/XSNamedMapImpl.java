package org.apache.xerces.impl.xs.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.xs.XSNamedMap;
import org.apache.xerces.xs.XSObject;

public class XSNamedMapImpl extends AbstractMap implements XSNamedMap {
  public static final XSNamedMapImpl EMPTY_MAP = new XSNamedMapImpl(new XSObject[0], 0);
  
  final String[] fNamespaces;
  
  final int fNSNum;
  
  final SymbolHash[] fMaps;
  
  XSObject[] fArray = null;
  
  int fLength = -1;
  
  private Set fEntrySet = null;
  
  public XSNamedMapImpl(String paramString, SymbolHash paramSymbolHash) {
    this.fNamespaces = new String[] { paramString };
    this.fMaps = new SymbolHash[] { paramSymbolHash };
    this.fNSNum = 1;
  }
  
  public XSNamedMapImpl(String[] paramArrayOfString, SymbolHash[] paramArrayOfSymbolHash, int paramInt) {
    this.fNamespaces = paramArrayOfString;
    this.fMaps = paramArrayOfSymbolHash;
    this.fNSNum = paramInt;
  }
  
  public XSNamedMapImpl(XSObject[] paramArrayOfXSObject, int paramInt) {
    if (paramInt == 0) {
      this.fNamespaces = null;
      this.fMaps = null;
      this.fNSNum = 0;
      this.fArray = paramArrayOfXSObject;
      this.fLength = 0;
      return;
    } 
    this.fNamespaces = new String[] { paramArrayOfXSObject[0].getNamespace() };
    this.fMaps = null;
    this.fNSNum = 1;
    this.fArray = paramArrayOfXSObject;
    this.fLength = paramInt;
  }
  
  public synchronized int getLength() {
    if (this.fLength == -1) {
      this.fLength = 0;
      for (byte b = 0; b < this.fNSNum; b++)
        this.fLength += this.fMaps[b].getLength(); 
    } 
    return this.fLength;
  }
  
  public XSObject itemByName(String paramString1, String paramString2) {
    for (byte b = 0; b < this.fNSNum; b++) {
      if (isEqual(paramString1, this.fNamespaces[b])) {
        if (this.fMaps != null)
          return (XSObject)this.fMaps[b].get(paramString2); 
        for (byte b1 = 0; b1 < this.fLength; b1++) {
          XSObject xSObject = this.fArray[b1];
          if (xSObject.getName().equals(paramString2))
            return xSObject; 
        } 
        return null;
      } 
    } 
    return null;
  }
  
  public synchronized XSObject item(int paramInt) {
    if (this.fArray == null) {
      getLength();
      this.fArray = new XSObject[this.fLength];
      int i = 0;
      for (byte b = 0; b < this.fNSNum; b++)
        i += this.fMaps[b].getValues((Object[])this.fArray, i); 
    } 
    return (paramInt < 0 || paramInt >= this.fLength) ? null : this.fArray[paramInt];
  }
  
  static boolean isEqual(String paramString1, String paramString2) {
    return (paramString1 != null) ? paramString1.equals(paramString2) : ((paramString2 == null));
  }
  
  public boolean containsKey(Object paramObject) {
    return (get(paramObject) != null);
  }
  
  public Object get(Object paramObject) {
    if (paramObject instanceof QName) {
      QName qName = (QName)paramObject;
      String str1 = qName.getNamespaceURI();
      if ("".equals(str1))
        str1 = null; 
      String str2 = qName.getLocalPart();
      return itemByName(str1, str2);
    } 
    return null;
  }
  
  public int size() {
    return getLength();
  }
  
  public synchronized Set entrySet() {
    if (this.fEntrySet == null) {
      int i = getLength();
      XSNamedMapEntry[] arrayOfXSNamedMapEntry = new XSNamedMapEntry[i];
      for (byte b = 0; b < i; b++) {
        XSObject xSObject = item(b);
        arrayOfXSNamedMapEntry[b] = new XSNamedMapEntry(new QName(xSObject.getNamespace(), xSObject.getName()), xSObject);
      } 
      this.fEntrySet = new AbstractSet(this, i, arrayOfXSNamedMapEntry) {
          private final int val$length;
          
          private final XSNamedMapImpl.XSNamedMapEntry[] val$entries;
          
          private final XSNamedMapImpl this$0;
          
          public Iterator iterator() {
            return (Iterator)new Object(this);
          }
          
          public int size() {
            return this.val$length;
          }
        };
    } 
    return this.fEntrySet;
  }
  
  private static final class XSNamedMapEntry implements Map.Entry {
    private final QName key;
    
    private final XSObject value;
    
    public XSNamedMapEntry(QName param1QName, XSObject param1XSObject) {
      this.key = param1QName;
      this.value = param1XSObject;
    }
    
    public Object getKey() {
      return this.key;
    }
    
    public Object getValue() {
      return this.value;
    }
    
    public Object setValue(Object param1Object) {
      throw new UnsupportedOperationException();
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object instanceof Map.Entry) {
        Map.Entry entry = (Map.Entry)param1Object;
        Object object1 = entry.getKey();
        Object object2 = entry.getValue();
        if ((this.key == null) ? ((object1 == null)) : this.key.equals(object1))
          if ((this.value == null) ? ((object2 == null)) : this.value.equals(object2)); 
        return false;
      } 
      return false;
    }
    
    public int hashCode() {
      return ((this.key == null) ? 0 : this.key.hashCode()) ^ ((this.value == null) ? 0 : this.value.hashCode());
    }
    
    public String toString() {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append(String.valueOf(this.key));
      stringBuffer.append('=');
      stringBuffer.append(String.valueOf(this.value));
      return stringBuffer.toString();
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/util/XSNamedMapImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */