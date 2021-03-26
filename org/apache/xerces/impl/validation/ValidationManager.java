package org.apache.xerces.impl.validation;

import java.util.ArrayList;

public class ValidationManager {
  protected final ArrayList fVSs = new ArrayList();
  
  protected boolean fGrammarFound = false;
  
  protected boolean fCachedDTD = false;
  
  public final void addValidationState(ValidationState paramValidationState) {
    this.fVSs.add(paramValidationState);
  }
  
  public final void setEntityState(EntityState paramEntityState) {
    for (int i = this.fVSs.size() - 1; i >= 0; i--)
      ((ValidationState)this.fVSs.get(i)).setEntityState(paramEntityState); 
  }
  
  public final void setGrammarFound(boolean paramBoolean) {
    this.fGrammarFound = paramBoolean;
  }
  
  public final boolean isGrammarFound() {
    return this.fGrammarFound;
  }
  
  public final void setCachedDTD(boolean paramBoolean) {
    this.fCachedDTD = paramBoolean;
  }
  
  public final boolean isCachedDTD() {
    return this.fCachedDTD;
  }
  
  public final void reset() {
    this.fVSs.clear();
    this.fGrammarFound = false;
    this.fCachedDTD = false;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/validation/ValidationManager.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */