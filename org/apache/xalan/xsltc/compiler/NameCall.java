/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*    */ import org.apache.bcel.generic.Instruction;
/*    */ import org.apache.bcel.generic.InstructionList;
/*    */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ final class NameCall
/*    */   extends NameBase
/*    */ {
/*    */   public NameCall(QName fname) {
/* 41 */     super(fname);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NameCall(QName fname, Vector arguments) {
/* 48 */     super(fname, arguments);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 55 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 56 */     InstructionList il = methodGen.getInstructionList();
/*    */     
/* 58 */     int getName = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNodeNameX", "(I)Ljava/lang/String;");
/*    */ 
/*    */     
/* 61 */     super.translate(classGen, methodGen);
/* 62 */     il.append((Instruction)new INVOKEINTERFACE(getName, 2));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/NameCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */