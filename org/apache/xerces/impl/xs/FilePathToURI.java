package org.apache.xerces.impl.xs;

import java.io.File;
import java.io.UnsupportedEncodingException;

final class FilePathToURI {
  private static boolean[] gNeedEscaping = new boolean[128];
  
  private static char[] gAfterEscaping1 = new char[128];
  
  private static char[] gAfterEscaping2 = new char[128];
  
  private static char[] gHexChs = new char[] { 
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
      if (gNeedEscaping[c1]) {
        stringBuffer.append('%');
        stringBuffer.append(gAfterEscaping1[c1]);
        stringBuffer.append(gAfterEscaping2[c1]);
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
          stringBuffer.append(gHexChs[j >> 4]);
          stringBuffer.append(gHexChs[j & 0xF]);
        } else if (gNeedEscaping[b1]) {
          stringBuffer.append('%');
          stringBuffer.append(gAfterEscaping1[b1]);
          stringBuffer.append(gAfterEscaping2[b1]);
        } else {
          stringBuffer.append((char)b1);
        } 
      } 
    } 
    return stringBuffer.toString();
  }
  
  static {
    for (byte b = 0; b <= 31; b++) {
      gNeedEscaping[b] = true;
      gAfterEscaping1[b] = gHexChs[b >> 4];
      gAfterEscaping2[b] = gHexChs[b & 0xF];
    } 
    gNeedEscaping[127] = true;
    gAfterEscaping1[127] = '7';
    gAfterEscaping2[127] = 'F';
    for (char c : new char[] { 
        ' ', '<', '>', '#', '%', '"', '{', '}', '|', '\\', 
        '^', '~', '[', ']', '`' }) {
      gNeedEscaping[c] = true;
      gAfterEscaping1[c] = gHexChs[c >> 4];
      gAfterEscaping2[c] = gHexChs[c & 0xF];
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/FilePathToURI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */