/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.ILOAD;
/*    */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*    */ import org.apache.bcel.generic.Instruction;
/*    */ import org.apache.bcel.generic.InstructionList;
/*    */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.CompareGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
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
/*    */ 
/*    */ 
/*    */ final class PositionCall
/*    */   extends FunctionCall
/*    */ {
/*    */   public PositionCall(QName fname) {
/* 39 */     super(fname);
/*    */   }
/*    */   
/*    */   public boolean hasPositionCall() {
/* 43 */     return true;
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 47 */     InstructionList il = methodGen.getInstructionList();
/*    */     
/* 49 */     if (methodGen instanceof CompareGenerator) {
/* 50 */       il.append(((CompareGenerator)methodGen).loadCurrentNode());
/*    */     }
/* 52 */     else if (methodGen instanceof org.apache.xalan.xsltc.compiler.util.TestGenerator) {
/* 53 */       il.append((Instruction)new ILOAD(2));
/*    */     } else {
/*    */       
/* 56 */       ConstantPoolGen cpg = classGen.getConstantPool();
/* 57 */       int index = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "getPosition", "()I");
/*    */ 
/*    */ 
/*    */       
/* 61 */       il.append(methodGen.loadIterator());
/* 62 */       il.append((Instruction)new INVOKEINTERFACE(index, 1));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/PositionCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */