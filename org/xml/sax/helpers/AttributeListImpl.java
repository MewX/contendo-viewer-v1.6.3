package org.xml.sax.helpers;

import java.util.Vector;
import org.xml.sax.AttributeList;

public class AttributeListImpl implements AttributeList {
  Vector a = new Vector();
  
  Vector b = new Vector();
  
  Vector c = new Vector();
  
  public AttributeListImpl() {}
  
  public AttributeListImpl(AttributeList paramAttributeList) {
    setAttributeList(paramAttributeList);
  }
  
  public void setAttributeList(AttributeList paramAttributeList) {
    int i = paramAttributeList.getLength();
    clear();
    for (byte b = 0; b < i; b++)
      addAttribute(paramAttributeList.getName(b), paramAttributeList.getType(b), paramAttributeList.getValue(b)); 
  }
  
  public void addAttribute(String paramString1, String paramString2, String paramString3) {
    this.a.addElement(paramString1);
    this.b.addElement(paramString2);
    this.c.addElement(paramString3);
  }
  
  public void removeAttribute(String paramString) {
    int i = this.a.indexOf(paramString);
    if (i >= 0) {
      this.a.removeElementAt(i);
      this.b.removeElementAt(i);
      this.c.removeElementAt(i);
    } 
  }
  
  public void clear() {
    this.a.removeAllElements();
    this.b.removeAllElements();
    this.c.removeAllElements();
  }
  
  public int getLength() {
    return this.a.size();
  }
  
  public String getName(int paramInt) {
    if (paramInt < 0)
      return null; 
    try {
      return this.a.elementAt(paramInt);
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return null;
    } 
  }
  
  public String getType(int paramInt) {
    if (paramInt < 0)
      return null; 
    try {
      return this.b.elementAt(paramInt);
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return null;
    } 
  }
  
  public String getValue(int paramInt) {
    if (paramInt < 0)
      return null; 
    try {
      return this.c.elementAt(paramInt);
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return null;
    } 
  }
  
  public String getType(String paramString) {
    return getType(this.a.indexOf(paramString));
  }
  
  public String getValue(String paramString) {
    return getValue(this.a.indexOf(paramString));
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/helpers/AttributeListImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */