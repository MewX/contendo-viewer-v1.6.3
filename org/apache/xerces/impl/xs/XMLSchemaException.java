package org.apache.xerces.impl.xs;

public class XMLSchemaException extends Exception {
  static final long serialVersionUID = -9096984648537046218L;
  
  String key;
  
  Object[] args;
  
  public XMLSchemaException(String paramString, Object[] paramArrayOfObject) {
    this.key = paramString;
    this.args = paramArrayOfObject;
  }
  
  public String getKey() {
    return this.key;
  }
  
  public Object[] getArgs() {
    return this.args;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/XMLSchemaException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */