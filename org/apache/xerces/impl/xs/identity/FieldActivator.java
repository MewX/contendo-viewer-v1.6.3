package org.apache.xerces.impl.xs.identity;

public interface FieldActivator {
  void startValueScopeFor(IdentityConstraint paramIdentityConstraint, int paramInt);
  
  XPathMatcher activateField(Field paramField, int paramInt);
  
  void endValueScopeFor(IdentityConstraint paramIdentityConstraint, int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/identity/FieldActivator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */