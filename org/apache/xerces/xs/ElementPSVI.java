package org.apache.xerces.xs;

public interface ElementPSVI extends ItemPSVI {
  XSElementDeclaration getElementDeclaration();
  
  XSNotationDeclaration getNotation();
  
  boolean getNil();
  
  XSModel getSchemaInformation();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/ElementPSVI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */