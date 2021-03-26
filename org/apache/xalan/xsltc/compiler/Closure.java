package org.apache.xalan.xsltc.compiler;

public interface Closure {
  boolean inInnerClass();
  
  Closure getParentClosure();
  
  String getInnerClassName();
  
  void addVariable(VariableRefBase paramVariableRefBase);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Closure.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */