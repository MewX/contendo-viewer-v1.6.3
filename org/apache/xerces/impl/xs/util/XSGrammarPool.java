package org.apache.xerces.impl.xs.util;

import java.util.ArrayList;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.XSModelImpl;
import org.apache.xerces.util.XMLGrammarPoolImpl;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xs.XSModel;

public class XSGrammarPool extends XMLGrammarPoolImpl {
  public XSModel toXSModel() {
    return toXSModel((short)1);
  }
  
  public XSModel toXSModel(short paramShort) {
    ArrayList arrayList = new ArrayList();
    for (byte b = 0; b < this.fGrammars.length; b++) {
      for (XMLGrammarPoolImpl.Entry entry = this.fGrammars[b]; entry != null; entry = entry.next) {
        if (entry.desc.getGrammarType().equals("http://www.w3.org/2001/XMLSchema"))
          arrayList.add(entry.grammar); 
      } 
    } 
    int i = arrayList.size();
    if (i == 0)
      return toXSModel(new SchemaGrammar[0], paramShort); 
    SchemaGrammar[] arrayOfSchemaGrammar = arrayList.<SchemaGrammar>toArray(new SchemaGrammar[i]);
    return toXSModel(arrayOfSchemaGrammar, paramShort);
  }
  
  protected XSModel toXSModel(SchemaGrammar[] paramArrayOfSchemaGrammar, short paramShort) {
    return (XSModel)new XSModelImpl(paramArrayOfSchemaGrammar, paramShort);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/util/XSGrammarPool.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */