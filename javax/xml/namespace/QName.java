package javax.xml.namespace;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class QName implements Serializable {
  private static final long a;
  
  private static final long b = -9120448754896609940L;
  
  private static final long c = 4418622981026545151L;
  
  private final String d;
  
  private final String e;
  
  private String f;
  
  private transient String g;
  
  public QName(String paramString1, String paramString2) {
    this(paramString1, paramString2, "");
  }
  
  public QName(String paramString1, String paramString2, String paramString3) {
    if (paramString1 == null) {
      this.d = "";
    } else {
      this.d = paramString1;
    } 
    if (paramString2 == null)
      throw new IllegalArgumentException("local part cannot be \"null\" when creating a QName"); 
    this.e = paramString2;
    if (paramString3 == null)
      throw new IllegalArgumentException("prefix cannot be \"null\" when creating a QName"); 
    this.f = paramString3;
  }
  
  public QName(String paramString) {
    this("", paramString, "");
  }
  
  public String getNamespaceURI() {
    return this.d;
  }
  
  public String getLocalPart() {
    return this.e;
  }
  
  public String getPrefix() {
    return this.f;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (paramObject instanceof QName) {
      QName qName = (QName)paramObject;
      return (this.e.equals(qName.e) && this.d.equals(qName.d));
    } 
    return false;
  }
  
  public final int hashCode() {
    return this.d.hashCode() ^ this.e.hashCode();
  }
  
  public String toString() {
    String str = this.g;
    if (str == null) {
      int i = this.d.length();
      if (i == 0) {
        str = this.e;
      } else {
        StringBuffer stringBuffer = new StringBuffer(i + this.e.length() + 2);
        stringBuffer.append('{');
        stringBuffer.append(this.d);
        stringBuffer.append('}');
        stringBuffer.append(this.e);
        str = stringBuffer.toString();
      } 
      this.g = str;
    } 
    return str;
  }
  
  public static QName valueOf(String paramString) {
    if (paramString == null)
      throw new IllegalArgumentException("cannot create QName from \"null\" or \"\" String"); 
    if (paramString.length() == 0)
      return new QName("", paramString, ""); 
    if (paramString.charAt(0) != '{')
      return new QName("", paramString, ""); 
    if (paramString.startsWith("{}"))
      throw new IllegalArgumentException("Namespace URI .equals(XMLConstants.NULL_NS_URI), .equals(\"\"), only the local part, \"" + paramString.substring(2 + "".length()) + "\", " + "should be provided."); 
    int i = paramString.indexOf('}');
    if (i == -1)
      throw new IllegalArgumentException("cannot create QName from \"" + paramString + "\", missing closing \"}\""); 
    return new QName(paramString.substring(1, i), paramString.substring(i + 1), "");
  }
  
  private void a(ObjectInputStream paramObjectInputStream) throws IOException, ClassNotFoundException {
    paramObjectInputStream.defaultReadObject();
    if (this.f == null)
      this.f = ""; 
  }
  
  static {
    String str = null;
    try {
      str = AccessController.<String>doPrivileged(new PrivilegedAction() {
            public Object run() {
              return System.getProperty("org.apache.xml.namespace.QName.useCompatibleSerialVersionUID");
            }
          });
    } catch (Exception exception) {}
    a = !"1.0".equals(str) ? -9120448754896609940L : 4418622981026545151L;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/namespace/QName.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */