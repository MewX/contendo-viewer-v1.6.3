/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*    */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.Type;
/*    */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*    */ import org.apache.xalan.xsltc.compiler.util.Util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class Otherwise
/*    */   extends Instruction
/*    */ {
/*    */   public void display(int indent) {
/* 35 */     indent(indent);
/* 36 */     Util.println("Otherwise");
/* 37 */     indent(indent + 4);
/* 38 */     displayContents(indent + 4);
/*    */   }
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 42 */     typeCheckContents(stable);
/* 43 */     return Type.Void;
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 47 */     Parser parser = getParser();
/* 48 */     ErrorMsg err = new ErrorMsg("STRAY_OTHERWISE_ERR", this);
/* 49 */     parser.reportError(3, err);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Otherwise.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */