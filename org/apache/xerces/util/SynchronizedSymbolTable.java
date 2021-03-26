package org.apache.xerces.util;

public final class SynchronizedSymbolTable extends SymbolTable {
  protected SymbolTable fSymbolTable;
  
  public SynchronizedSymbolTable(SymbolTable paramSymbolTable) {
    this.fSymbolTable = paramSymbolTable;
  }
  
  public SynchronizedSymbolTable() {
    this.fSymbolTable = new SymbolTable();
  }
  
  public SynchronizedSymbolTable(int paramInt) {
    this.fSymbolTable = new SymbolTable(paramInt);
  }
  
  public String addSymbol(String paramString) {
    synchronized (this.fSymbolTable) {
      return this.fSymbolTable.addSymbol(paramString);
    } 
  }
  
  public String addSymbol(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    synchronized (this.fSymbolTable) {
      return this.fSymbolTable.addSymbol(paramArrayOfchar, paramInt1, paramInt2);
    } 
  }
  
  public boolean containsSymbol(String paramString) {
    synchronized (this.fSymbolTable) {
      return this.fSymbolTable.containsSymbol(paramString);
    } 
  }
  
  public boolean containsSymbol(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    synchronized (this.fSymbolTable) {
      return this.fSymbolTable.containsSymbol(paramArrayOfchar, paramInt1, paramInt2);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/util/SynchronizedSymbolTable.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */