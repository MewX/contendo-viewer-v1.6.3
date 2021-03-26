package org.apache.xalan.xsltc.compiler;

import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;

public abstract class Pattern extends Expression {
  public abstract Type typeCheck(SymbolTable paramSymbolTable) throws TypeCheckError;
  
  public abstract void translate(ClassGenerator paramClassGenerator, MethodGenerator paramMethodGenerator);
  
  public abstract double getPriority();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Pattern.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */