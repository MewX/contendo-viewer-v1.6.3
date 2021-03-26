package org.apache.xerces.xni;

public class QName implements Cloneable {
  public String prefix;
  
  public String localpart;
  
  public String rawname;
  
  public String uri;
  
  public QName() {
    clear();
  }
  
  public QName(String paramString1, String paramString2, String paramString3, String paramString4) {
    setValues(paramString1, paramString2, paramString3, paramString4);
  }
  
  public QName(QName paramQName) {
    setValues(paramQName);
  }
  
  public void setValues(QName paramQName) {
    this.prefix = paramQName.prefix;
    this.localpart = paramQName.localpart;
    this.rawname = paramQName.rawname;
    this.uri = paramQName.uri;
  }
  
  public void setValues(String paramString1, String paramString2, String paramString3, String paramString4) {
    this.prefix = paramString1;
    this.localpart = paramString2;
    this.rawname = paramString3;
    this.uri = paramString4;
  }
  
  public void clear() {
    this.prefix = null;
    this.localpart = null;
    this.rawname = null;
    this.uri = null;
  }
  
  public Object clone() {
    return new QName(this);
  }
  
  public int hashCode() {
    return (this.uri != null) ? (this.uri.hashCode() + ((this.localpart != null) ? this.localpart.hashCode() : 0)) : ((this.rawname != null) ? this.rawname.hashCode() : 0);
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject instanceof QName) {
      QName qName = (QName)paramObject;
      if (qName.uri != null)
        return (this.uri == qName.uri && this.localpart == qName.localpart); 
      if (this.uri == null)
        return (this.rawname == qName.rawname); 
    } 
    return false;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    boolean bool = false;
    if (this.prefix != null) {
      stringBuffer.append("prefix=\"").append(this.prefix).append('"');
      bool = true;
    } 
    if (this.localpart != null) {
      if (bool)
        stringBuffer.append(','); 
      stringBuffer.append("localpart=\"").append(this.localpart).append('"');
      bool = true;
    } 
    if (this.rawname != null) {
      if (bool)
        stringBuffer.append(','); 
      stringBuffer.append("rawname=\"").append(this.rawname).append('"');
      bool = true;
    } 
    if (this.uri != null) {
      if (bool)
        stringBuffer.append(','); 
      stringBuffer.append("uri=\"").append(this.uri).append('"');
    } 
    return stringBuffer.toString();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xni/QName.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */