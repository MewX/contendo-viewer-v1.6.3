package org.apache.xerces.xs;

public interface XSNamespaceItem {
  String getSchemaNamespace();
  
  XSNamedMap getComponents(short paramShort);
  
  XSObjectList getAnnotations();
  
  XSElementDeclaration getElementDeclaration(String paramString);
  
  XSAttributeDeclaration getAttributeDeclaration(String paramString);
  
  XSTypeDefinition getTypeDefinition(String paramString);
  
  XSAttributeGroupDefinition getAttributeGroup(String paramString);
  
  XSModelGroupDefinition getModelGroupDefinition(String paramString);
  
  XSNotationDeclaration getNotationDeclaration(String paramString);
  
  XSIDCDefinition getIDCDefinition(String paramString);
  
  StringList getDocumentLocations();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/XSNamespaceItem.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */