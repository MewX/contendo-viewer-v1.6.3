package org.apache.xerces.xni.grammars;

import org.apache.xerces.xs.XSModel;

public interface XSGrammar extends Grammar {
  XSModel toXSModel();
  
  XSModel toXSModel(XSGrammar[] paramArrayOfXSGrammar);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xni/grammars/XSGrammar.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */