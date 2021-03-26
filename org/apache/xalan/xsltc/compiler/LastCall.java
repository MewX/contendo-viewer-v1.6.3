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
/*    */ final class LastCall
/*    */   extends FunctionCall
/*    */ {
/*    */   public LastCall(QName fname) {
/* 38 */     super(fname);
/*    */   }
/*    */   
/*    */   public boolean hasPositionCall() {
/* 42 */     return true;
/*    */   }
/*    */   
/*    */   public boolean hasLastCall() {
/* 46 */     return true;
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 50 */     InstructionList il = methodGen.getInstructionList();
/*    */     
/* 52 */     if (methodGen instanceof CompareGenerator) {
/* 53 */       il.append(((CompareGenerator)methodGen).loadLastNode());
/*    */     }
/* 55 */     else if (methodGen instanceof org.apache.xalan.xsltc.compiler.util.TestGenerator) {
/* 56 */       il.append((Instruction)new ILOAD(3));
/*    */     } else {
/*    */       
/* 59 */       ConstantPoolGen cpg = classGen.getConstantPool();
/* 60 */       int getLast = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "getLast", "()I");
/*    */ 
/*    */       
/* 63 */       il.append(methodGen.loadIterator());
/* 64 */       il.append((Instruction)new INVOKEINTERFACE(getLast, 1));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/LastCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */