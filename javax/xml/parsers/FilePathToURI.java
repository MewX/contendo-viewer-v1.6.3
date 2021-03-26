package javax.xml.parsers;

import java.io.File;
import java.io.UnsupportedEncodingException;

class FilePathToURI {
  private static boolean[] a = new boolean[128];
  
  private static char[] b = new char[128];
  
  private static char[] c = new char[128];
  
  private static char[] d = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'A', 'B', 'C', 'D', 'E', 'F' };
  
  public static String filepath2URI(String paramString) {
    if (paramString == null)
      return null; 
    char c = File.separatorChar;
    paramString = paramString.replace(c, '/');
    int i = paramString.length();
    StringBuffer stringBuffer = new StringBuffer(i * 3);
    stringBuffer.append("file://");
    if (i >= 2 && paramString.charAt(1) == ':') {
      char c1 = Character.toUpperCase(paramString.charAt(0));
      if (c1 >= 'A' && c1 <= 'Z')
        stringBuffer.append('/'); 
    } 
    byte b;
    for (b = 0; b < i; b++) {
      char c1 = paramString.charAt(b);
      if (c1 >= '')
        break; 
      if (a[c1]) {
        stringBuffer.append('%');
        stringBuffer.append(b[c1]);
        stringBuffer.append(c[c1]);
      } else {
        stringBuffer.append((char)c1);
      } 
    } 
    if (b < i) {
      byte[] arrayOfByte = null;
      try {
        arrayOfByte = paramString.substring(b).getBytes("UTF-8");
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        return paramString;
      } 
      i = arrayOfByte.length;
      for (b = 0; b < i; b++) {
        byte b1 = arrayOfByte[b];
        if (b1 < 0) {
          int j = b1 + 256;
          stringBuffer.append('%');
          stringBuffer.append(d[j >> 4]);
          stringBuffer.append(d[j & 0xF]);
        } else if (a[b1]) {
          stringBuffer.append('%');
          stringBuffer.append(b[b1]);
          stringBuffer.append(c[b1]);
        } else {
          stringBuffer.append((char)b1);
        } 
      } 
    } 
    return stringBuffer.toString();
  }
  
  static {
    for (byte b = 0; b <= 31; b++) {
      a[b] = true;
      b[b] = d[b >> 4];
      c[b] = d[b & 0xF];
    } 
    a[127] = true;
    b[127] = '7';
    c[127] = 'F';
    for (char c : new char[] { 
        ' ', '<', '>', '#', '%', '"', '{', '}', '|', '\\', 
        '^', '~', '[', ']', '`' }) {
      a[c] = true;
      b[c] = d[c >> 4];
      c[c] = d[c & 0xF];
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/parsers/FilePathToURI.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */