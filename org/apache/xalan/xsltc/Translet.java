package org.apache.xalan.xsltc;

import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.serializer.SerializationHandler;

public interface Translet {
  void transform(DOM paramDOM, SerializationHandler paramSerializationHandler) throws TransletException;
  
  void transform(DOM paramDOM, SerializationHandler[] paramArrayOfSerializationHandler) throws TransletException;
  
  void transform(DOM paramDOM, DTMAxisIterator paramDTMAxisIterator, SerializationHandler paramSerializationHandler) throws TransletException;
  
  Object addParameter(String paramString, Object paramObject);
  
  void buildKeys(DOM paramDOM, DTMAxisIterator paramDTMAxisIterator, SerializationHandler paramSerializationHandler, int paramInt) throws TransletException;
  
  void addAuxiliaryClass(Class paramClass);
  
  Class getAuxiliaryClass(String paramString);
  
  String[] getNamesArray();
  
  String[] getUrisArray();
  
  int[] getTypesArray();
  
  String[] getNamespaceArray();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/Translet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */