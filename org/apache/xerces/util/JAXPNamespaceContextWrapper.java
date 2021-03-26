package org.apache.xerces.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import javax.xml.namespace.NamespaceContext;
import org.apache.xerces.xni.NamespaceContext;

public final class JAXPNamespaceContextWrapper implements NamespaceContext {
  private NamespaceContext fNamespaceContext;
  
  private SymbolTable fSymbolTable;
  
  private List fPrefixes;
  
  private final Vector fAllPrefixes = new Vector();
  
  private int[] fContext = new int[8];
  
  private int fCurrentContext;
  
  public JAXPNamespaceContextWrapper(SymbolTable paramSymbolTable) {
    setSymbolTable(paramSymbolTable);
  }
  
  public void setNamespaceContext(NamespaceContext paramNamespaceContext) {
    this.fNamespaceContext = paramNamespaceContext;
  }
  
  public NamespaceContext getNamespaceContext() {
    return this.fNamespaceContext;
  }
  
  public void setSymbolTable(SymbolTable paramSymbolTable) {
    this.fSymbolTable = paramSymbolTable;
  }
  
  public SymbolTable getSymbolTable() {
    return this.fSymbolTable;
  }
  
  public void setDeclaredPrefixes(List paramList) {
    this.fPrefixes = paramList;
  }
  
  public List getDeclaredPrefixes() {
    return this.fPrefixes;
  }
  
  public String getURI(String paramString) {
    if (this.fNamespaceContext != null) {
      String str = this.fNamespaceContext.getNamespaceURI(paramString);
      if (str != null && !"".equals(str))
        return (this.fSymbolTable != null) ? this.fSymbolTable.addSymbol(str) : str.intern(); 
    } 
    return null;
  }
  
  public String getPrefix(String paramString) {
    if (this.fNamespaceContext != null) {
      if (paramString == null)
        paramString = ""; 
      String str = this.fNamespaceContext.getPrefix(paramString);
      if (str == null)
        str = ""; 
      return (this.fSymbolTable != null) ? this.fSymbolTable.addSymbol(str) : str.intern();
    } 
    return null;
  }
  
  public Enumeration getAllPrefixes() {
    return Collections.enumeration(new TreeSet(this.fAllPrefixes));
  }
  
  public void pushContext() {
    if (this.fCurrentContext + 1 == this.fContext.length) {
      int[] arrayOfInt = new int[this.fContext.length * 2];
      System.arraycopy(this.fContext, 0, arrayOfInt, 0, this.fContext.length);
      this.fContext = arrayOfInt;
    } 
    this.fContext[++this.fCurrentContext] = this.fAllPrefixes.size();
    if (this.fPrefixes != null)
      this.fAllPrefixes.addAll(this.fPrefixes); 
  }
  
  public void popContext() {
    this.fAllPrefixes.setSize(this.fContext[this.fCurrentContext--]);
  }
  
  public boolean declarePrefix(String paramString1, String paramString2) {
    return true;
  }
  
  public int getDeclaredPrefixCount() {
    return (this.fPrefixes != null) ? this.fPrefixes.size() : 0;
  }
  
  public String getDeclaredPrefixAt(int paramInt) {
    return this.fPrefixes.get(paramInt);
  }
  
  public void reset() {
    this.fCurrentContext = 0;
    this.fContext[this.fCurrentContext] = 0;
    this.fAllPrefixes.clear();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/util/JAXPNamespaceContextWrapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */