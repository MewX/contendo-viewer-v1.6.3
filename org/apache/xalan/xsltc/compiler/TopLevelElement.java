/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import org.apache.bcel.generic.InstructionList;
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
/*    */ class TopLevelElement
/*    */   extends SyntaxTreeNode
/*    */ {
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 36 */     return typeCheckContents(stable);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 43 */     ErrorMsg msg = new ErrorMsg("NOT_IMPLEMENTED_ERR", getClass(), this);
/*    */     
/* 45 */     getParser().reportError(2, msg);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InstructionList compile(ClassGenerator classGen, MethodGenerator methodGen) {
/* 54 */     InstructionList save = methodGen.getInstructionList(); InstructionList result;
/* 55 */     methodGen.setInstructionList(result = new InstructionList());
/* 56 */     translate(classGen, methodGen);
/* 57 */     methodGen.setInstructionList(save);
/* 58 */     return result;
/*    */   }
/*    */   
/*    */   public void display(int indent) {
/* 62 */     indent(indent);
/* 63 */     Util.println("TopLevelElement");
/* 64 */     displayContents(indent + 4);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/TopLevelElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */