package org.apache.xml.serializer;

import java.util.Vector;

public interface XSLOutputAttributes {
  String getDoctypePublic();
  
  String getDoctypeSystem();
  
  String getEncoding();
  
  boolean getIndent();
  
  int getIndentAmount();
  
  String getMediaType();
  
  boolean getOmitXMLDeclaration();
  
  String getStandalone();
  
  String getVersion();
  
  void setCdataSectionElements(Vector paramVector);
  
  void setDoctype(String paramString1, String paramString2);
  
  void setDoctypePublic(String paramString);
  
  void setDoctypeSystem(String paramString);
  
  void setEncoding(String paramString);
  
  void setIndent(boolean paramBoolean);
  
  void setMediaType(String paramString);
  
  void setOmitXMLDeclaration(boolean paramBoolean);
  
  void setStandalone(String paramString);
  
  void setVersion(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/XSLOutputAttributes.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */