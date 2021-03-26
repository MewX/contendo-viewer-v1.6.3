package org.apache.xerces.dom3.as;

import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;

public interface DOMASBuilder extends LSParser {
  ASModel getAbstractSchema();
  
  void setAbstractSchema(ASModel paramASModel);
  
  ASModel parseASURI(String paramString) throws DOMASException, Exception;
  
  ASModel parseASInputSource(LSInput paramLSInput) throws DOMASException, Exception;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom3/as/DOMASBuilder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */