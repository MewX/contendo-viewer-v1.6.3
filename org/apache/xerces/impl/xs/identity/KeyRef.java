package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.xs.XSIDCDefinition;

public class KeyRef extends IdentityConstraint {
  protected final UniqueOrKey fKey;
  
  public KeyRef(String paramString1, String paramString2, String paramString3, UniqueOrKey paramUniqueOrKey) {
    super(paramString1, paramString2, paramString3);
    this.fKey = paramUniqueOrKey;
    this.type = 2;
  }
  
  public UniqueOrKey getKey() {
    return this.fKey;
  }
  
  public XSIDCDefinition getRefKey() {
    return this.fKey;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/identity/KeyRef.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */