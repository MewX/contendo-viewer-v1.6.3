package org.apache.xmlcommons;

public class Version {
  public static String getVersion() {
    return getProduct() + " " + getVersionNum();
  }
  
  public static String getProduct() {
    return "XmlCommonsExternal";
  }
  
  public static String getVersionNum() {
    return "1.4.01";
  }
  
  public static void main(String[] paramArrayOfString) {
    System.out.println(getVersion());
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlcommons/Version.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */