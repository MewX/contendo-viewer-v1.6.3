package org.apache.xerces.impl.xs.util;

import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xs.XSObject;

public final class XSInputSource extends XMLInputSource {
  private SchemaGrammar[] fGrammars;
  
  private XSObject[] fComponents;
  
  public XSInputSource(SchemaGrammar[] paramArrayOfSchemaGrammar) {
    super(null, null, null);
    this.fGrammars = paramArrayOfSchemaGrammar;
    this.fComponents = null;
  }
  
  public XSInputSource(XSObject[] paramArrayOfXSObject) {
    super(null, null, null);
    this.fGrammars = null;
    this.fComponents = paramArrayOfXSObject;
  }
  
  public SchemaGrammar[] getGrammars() {
    return this.fGrammars;
  }
  
  public void setGrammars(SchemaGrammar[] paramArrayOfSchemaGrammar) {
    this.fGrammars = paramArrayOfSchemaGrammar;
  }
  
  public XSObject[] getComponents() {
    return this.fComponents;
  }
  
  public void setComponents(XSObject[] paramArrayOfXSObject) {
    this.fComponents = paramArrayOfXSObject;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/util/XSInputSource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */